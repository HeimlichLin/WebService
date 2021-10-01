package com.restful.aspect;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.restful.constant.Constant;
import com.restful.enums.RequestType;
import com.restful.enums.ReturnCode;
import com.restful.service.AccessCodeService;
import com.restful.vo.common.AccessCodeVo;
import com.restful.vo.request.ApWebRequestVo;
import com.restful.vo.request.BaseRequestVo;
import com.restful.vo.response.BaseResponseVo;
import com.tradevan.saab.bean.SaabUser;

@Aspect
@SuppressWarnings("rawtypes")
public class AccessPermissionAspect {
	
	@Autowired
	private Logger logger;
	
	@Autowired
	private AccessCodeService accessCodeService;	
	
	@Autowired( required = false )
	private HttpServletRequest request;
	
	@Autowired
	private ObjectMapper mapper;
	
	
	private List<Class> ignoreTargetType;
	
	public List<Class> getIgnoreTargetType() {
		return ignoreTargetType;
	}
	
	public void setIgnoreTargetType(List<Class> ignoreTargetType) {
		this.ignoreTargetType = ignoreTargetType;
	}


	@Around( "execution(* com.tradevan.ftzc.restful..*(..))" )
	public Object before( ProceedingJoinPoint joinPoint ) throws Throwable {	
		
		logger.info( "*** AccessPermissionAspect Start." );
		
		// 檢查是否為跳脫項目，如果視則跳脫不檢查
		if ( ignore( joinPoint ) ) {
			return chain( joinPoint );
		}
						
		// 基本訊息log	
		logger.info( "切入點類別 -> " + joinPoint.getTarget().getClass() );
		logger.info( "切入點方法 -> " + joinPoint.getSignature().getName() );		
		logger.info( "遠端位址 -> " + request.getRemoteAddr() );
				
		HttpSession session = request.getSession();		
		BaseRequestVo reqVo = ( BaseRequestVo ) joinPoint.getArgs()[0];
		RequestType reqType = reqVo.getReqType();
		
		if ( null == reqType ) {
			return broke( "錯誤或空的ReqType。" );
		}
		
		if (  RequestType.AP_WEB.equals( reqType ) ) {
			
			Object saabUserObj 	= session.getAttribute( Constant.SESSION_KEY.SAAB_USER );
			Object bfNoObj 		= session.getAttribute( Constant.SESSION_KEY.BF_NO );
			SaabUser saabUser 	= null;
			if ( saabUserObj instanceof SaabUser ) {
				saabUser = (SaabUser) saabUserObj;
			}
			if ( null == saabUser ) {
				return broke( "請由自貿港帳冊平台登入。" );
			}
			if ( null == bfNoObj ) {
				return broke( "尚未選擇監管編號。" );
			}
			
			// 如果請求的ReqVo屬於ApWebRequestVo則自動注入UserId、bfNo
			ApWebRequestVo apWebReqVo = null;
			if ( reqVo instanceof ApWebRequestVo ) {
				apWebReqVo = ( ApWebRequestVo ) reqVo;
				apWebReqVo.setUserId( saabUser.getId() );
				apWebReqVo.setBfNo( (String) bfNoObj );
			}			
			return chain( joinPoint );
			
		} else if (  RequestType.API.equals( reqType ) ) { // API請求檢查機制			
			String accessCodeStr = reqVo.getAccessCode();			
			if ( StringUtils.isBlank( accessCodeStr ) ) {
				return broke( "錯誤或空的AccessCode。" );			
			}			
			AccessCodeVo accessCodeVo = accessCodeService.getAccessCodeVoByCode( accessCodeStr );
			if ( null == accessCodeVo ) {
				return broke( "錯誤的AccessCode。" );	
			}			
			// 期限檢查
			Timestamp expiredDate = accessCodeVo.getExpiredDate();
			if ( null != expiredDate && expiredDate.getTime() < System.currentTimeMillis() ) {
				return broke( "過期的AccessCode。" );	
			}			
			// 狀態檢查
			if ( !"A".equals( accessCodeVo.getStatus() ) ) {
				return broke( "非激活狀態的AccessCode。" );	
			}			
			
			return chain( joinPoint );
		} else {
			return broke( "不支援且未知的類型。");
		}		
	}
	
	
	/**
	 * 檢查該切入點是否為跳脫
	 * 1. ClassType 在ignoreTargetType清單內
	 * 2. 無參數的Method
	 * 3. 第一個參數非BaseRequestVo型態的參數
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	private boolean ignore( ProceedingJoinPoint joinPoint ) throws Throwable {
		// 如果在忽略清單內的類別 不執行檢查
		Class targetClass = joinPoint.getTarget().getClass();
		for ( Class type : ignoreTargetType ) {
			if ( !type.equals(targetClass) ) {
				continue;
			}
			logger.info( String.format( "TargetType[%s] is ignore Type Will Be do joinPoint.proceed()", targetClass.toString() ) );
			return true;
		}
		
		// Printer Input parameter
		Object[] args = joinPoint.getArgs();
		if ( args.length < 1 ) { // 無參數的Method不檢查直接執行
			return true;
		}
		for (int i = 0; i < args.length; i++) {
			String argJson = mapper.writeValueAsString( args[i] );
			logger.info( String.format( "Parameter[%d] -> %s", i, argJson ) );
		}
		
		if ( !(args[0] instanceof BaseRequestVo) ) { // 非BaseRequestVo直接執行不檢查
			return true;
		}
		
		return false;
	}
	
	/**
	 * 檢查結束，鍊接切入點
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	private Object chain( ProceedingJoinPoint joinPoint ) throws Throwable {
		logger.info( "*** AccessPermissionAspect End." );
		return joinPoint.proceed();		
	}

	/**
	 * 返回一個BaseResponseVo關於非法存取的訊息
	 * @param msg
	 * @return
	 */
	private Response broke( String msg ) {
		logger.info( "*** AccessPermissionAspect End With Broke." );
		BaseResponseVo respVo = new BaseResponseVo();
		respVo.setRtnCode( ReturnCode.ILLEGAL );
		respVo.getRtnMsg().add( ReturnCode.ILLEGAL.getMessage() );
		respVo.getRtnMsg().add( msg );
		
		return Response.status( 200 ).entity( respVo ).build();
	}

}
