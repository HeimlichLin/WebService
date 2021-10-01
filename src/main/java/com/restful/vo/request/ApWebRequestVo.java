package com.restful.vo.request;

import java.io.Serializable;

public class ApWebRequestVo extends BaseRequestVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String bfNo;

	private String userId;

	public String getBfNo() {
		return bfNo;
	}

	public void setBfNo(String bfNo) {
		this.bfNo = bfNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
