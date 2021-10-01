package com.restful.controller;

import java.sql.Timestamp;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.restful.enums.ReturnCode;
import com.restful.service.AccessCodeService;
import com.restful.utils.DESHelper;
import com.restful.utils.ResponseHelper.BuildResponse;
import com.restful.vo.common.AccessCodeVo;
import com.restful.vo.request.SysGetAccessCodeRequestVo;
import com.restful.vo.request.SysGetDesKeyRequestVo;
import com.restful.vo.response.SysGetAccessCodeResponseVo;
import com.restful.vo.response.SysGetDesKeyResponseVo;
import com.restful.constant.Constant.SESSION_KEY;
import com.restful.constant.RestResourcePath;

@Path( RestResourcePath.SECURE)
@Consumes(MediaType.APPLICATION_JSON)
@Controller
public class SecurityController {
	
	@Autowired
	private Logger logger;
	
	@Autowired( required = true )
	private HttpServletRequest request;
	
	@Autowired
	private AccessCodeService accessCodeService;
	
	private static final String ENCODE = "UTF-8";
		
	@POST
	@Path( RestResourcePath.SECURE_PATH.GET_DES_KEY )	
	@Produces(MediaType.APPLICATION_JSON)
	public Response get_des_key( SysGetDesKeyRequestVo reqVo ) throws Exception {
		SysGetDesKeyResponseVo respVo = new SysGetDesKeyResponseVo();
		try {
			byte[] hexkey = DESHelper.generateKey();
			String keyString = new String( Hex.encodeHex(hexkey) );
			respVo.setDes(keyString);
			
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute( SESSION_KEY.GET_ACCESS_CODE_DES_KEY, hexkey );
			return BuildResponse.OK(respVo, ReturnCode.SUCCESS, "SUCCESS" );
			
		} catch ( Exception e ) {
			e.printStackTrace();
			logger.error( "SecurityController.get_des_key exception e -> " + e );
			throw e;
		}		
	}

	@POST
	@Path( RestResourcePath.SECURE_PATH.GET_ACCESS_CODE )	
	@Produces(MediaType.APPLICATION_JSON)
	public Response get_access_code( SysGetAccessCodeRequestVo reqVo ) throws Exception {
		SysGetAccessCodeResponseVo respVo = new SysGetAccessCodeResponseVo();
		try {		
			
			if ( StringUtils.isBlank( reqVo.getEnAcc() ) ||
				StringUtils.isBlank( reqVo.getEnPass() ) ) {
				return BuildResponse.OK(respVo, ReturnCode.ILLEGAL, ReturnCode.ILLEGAL.getMessage() );
			}
			
			HttpSession httpSession = request.getSession();
			Object desKeyObj = httpSession.getAttribute( SESSION_KEY.GET_ACCESS_CODE_DES_KEY );
			if ( null == desKeyObj || !(desKeyObj instanceof byte[]) ) {
				return BuildResponse.OK(respVo, ReturnCode.ILLEGAL, "DES Key Not Found." );
			}
			byte[] desKey = (byte[]) desKeyObj;
			
			
			// 解密帳號密碼
			String userId = null;
			String userPwd = null;
			try {
				userId  = new String( DESHelper.decode( desKey, Hex.decodeHex( reqVo.getEnAcc().toCharArray() ) ), ENCODE );
				userPwd = new String( DESHelper.decode( desKey, Hex.decodeHex( reqVo.getEnPass().toCharArray() ) ), ENCODE );
			} catch ( Exception e ) {
				return BuildResponse.OK(respVo, ReturnCode.FAILURE, "DES Decode Failure" );
			}
			
			// 登入saab			
//			int loginStatus = SaabHelper.getInstance().getServiceManager().getLoginService().login( userId, userPwd );
//			if ( LoginService.OK != loginStatus ) { // 登入失敗
//				String rtnMsg = String.format("SAAB_USER_ID[%s] Login Failure, Status[%d]", userId, loginStatus );
//				respVo.getRtnMsg().add( rtnMsg );
//				return BuildResponse.OK(respVo, ReturnCode.ILLEGAL, ReturnCode.ILLEGAL.getMessage() );
//			}
			
			// 先找有沒有已激活的ACCESS_CODE
			AccessCodeVo accessCode = accessCodeService.getActiveAccessCodeVoByUserId(userId);
			if ( null != accessCode ) { // 有，直接回應
				String code = accessCode.getCode();
				respVo.setAccessCode(code);				
				return BuildResponse.OK(respVo, ReturnCode.SUCCESS, ReturnCode.SUCCESS.getMessage() );				
			}
			
			// 建立新的ACCESS_CODE
			accessCode = new AccessCodeVo();
			long currentTimeMillis = System.currentTimeMillis();
			long lastTimeMillis = currentTimeMillis + ( 24 * 60 * 60 * 1000L );
			String code = UUID.randomUUID().toString();
			accessCode.setCode( code );
			accessCode.setCreateDate(  new Timestamp( currentTimeMillis ) );
			accessCode.setExpiredDate( new Timestamp( lastTimeMillis ) );
			accessCode.setLastAccessDate( null );
			accessCode.setLastAccessRemoteAddr( null );
			accessCode.setRegAccessRemoteAddr( request.getRemoteAddr() );
			accessCode.setStatus( "A" ); // 狀態 A = Active, D = Disable, F = Frezze
			accessCode.setUserId( userId );
			
			// 寫資料庫
			try {
				accessCodeService.insertAccessCode(accessCode);			
			} catch ( Exception e ) {
				respVo.getRtnMsg().add( "Generat AccessCode Failure. We has Database problem." );
				return BuildResponse.OK(respVo, ReturnCode.FAILURE, ReturnCode.FAILURE.getMessage() );
			}
			 
			respVo.setAccessCode(code);				
			return BuildResponse.OK(respVo, ReturnCode.SUCCESS, ReturnCode.SUCCESS.getMessage() );			
		} catch ( Exception e ) {
			e.printStackTrace();
			logger.error( "SecurityController.get_access_code exception e -> " + e );
			throw e;
		}		
	}


}

