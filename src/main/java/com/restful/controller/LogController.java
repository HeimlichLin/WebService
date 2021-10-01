package com.restful.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.restful.constant.RestResourcePath;
import com.restful.enums.ReturnCode;
import com.restful.service.L4LogService;
import com.restful.utils.ResponseHelper.BuildResponse;
import com.restful.vo.request.L4LogRequestVo;
import com.restful.vo.response.L4LogResponseVo;

@Path( RestResourcePath.LOG )
@Consumes(MediaType.APPLICATION_JSON)
@Controller
public class LogController {
	
	@Autowired
	private Logger logger;
	
	@Autowired
	private L4LogService l4LogService;
	
	@POST
	@Path( RestResourcePath.LOG_PATH.L4_LASTDAY_LOG )
	@Produces(MediaType.APPLICATION_JSON)
	public Response queryLastDayL4Log(L4LogRequestVo reqVo) throws Exception {
		logger.info("L4LogController.queryL4Log start");
		L4LogResponseVo resVo = new L4LogResponseVo();
		try {
			resVo = l4LogService.query(reqVo);			
		} catch ( Exception e ) {
			e.printStackTrace();
			logger.error( "L4LogController.queryL4Log Throws Exception e -> " + e );
			throw e;
		}		
		return BuildResponse.OK( resVo, ReturnCode.SUCCESS, ReturnCode.SUCCESS.getMessage() );
	}
	
	@POST
	@Path( RestResourcePath.LOG_PATH.GET_L4_LOG )
	@Produces(MediaType.APPLICATION_JSON)
	public Response queryL4Log(L4LogRequestVo reqVo) throws Exception {
		logger.info("L4LogController.queryL4Log start");
		L4LogResponseVo resVo = new L4LogResponseVo();
		try {
			resVo = l4LogService.query(reqVo);			
		} catch ( Exception e ) {
			e.printStackTrace();
			logger.error( "L4LogController.queryL4Log Throws Exception e -> " + e );
			throw e;
		}		
		return BuildResponse.OK( resVo, ReturnCode.SUCCESS, ReturnCode.SUCCESS.getMessage() );
	}
	
}
