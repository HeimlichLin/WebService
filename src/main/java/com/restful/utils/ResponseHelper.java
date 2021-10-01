package com.restful.utils;

import javax.ws.rs.core.Response;

import com.restful.enums.ReturnCode;
import com.restful.vo.response.BaseResponseVo;

public class ResponseHelper {

	public static class BuildResponse {

		public static Response OK(BaseResponseVo respVo, ReturnCode rtnCode,
				String msg) {
			if (respVo == null) {
				return null;
			}
			respVo.setRtnCode(rtnCode);
			// respVo.getRtnMsg().add( rtnCode.getMessage() );
			if (null != msg) {
				respVo.getRtnMsg().add(msg);
			}
			return Response.ok().entity(respVo).build();
		}

	}

}
