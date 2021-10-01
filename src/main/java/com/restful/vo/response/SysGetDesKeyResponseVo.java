package com.restful.vo.response;

import java.io.Serializable;

public class SysGetDesKeyResponseVo extends BaseResponseVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String des;

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	
}
