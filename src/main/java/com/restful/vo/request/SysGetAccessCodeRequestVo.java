package com.restful.vo.request;

import java.io.Serializable;

public class SysGetAccessCodeRequestVo extends BaseRequestVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String enAcc;	
	private String enPass;
	
	public String getEnAcc() {
		return enAcc;
	}
	public void setEnAcc(String enAcc) {
		this.enAcc = enAcc;
	}
	public String getEnPass() {
		return enPass;
	}
	public void setEnPass(String enPass) {
		this.enPass = enPass;
	}	
	
}
