package com.restful.vo.response;

import java.io.Serializable;

public class SysGetAccessCodeResponseVo extends BaseResponseVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String accessCode;

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	
}
