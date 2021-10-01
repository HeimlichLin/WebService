package com.restful.vo.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.restful.enums.RequestType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseRequestVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private RequestType reqType;

	private String sessionKey;
	
	private String accessCode;

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public RequestType getReqType() {
		return reqType;
	}

	public void setReqType(RequestType reqType) {
		this.reqType = reqType;
	}
}
