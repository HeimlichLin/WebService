package com.restful.vo.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.restful.enums.ReturnCode;

public class BaseResponseVo implements Serializable{

	private static final long serialVersionUID = 1L;

	private ReturnCode rtnCode;
	
	private List<String> rtnMsg = new ArrayList<String>();

	public ReturnCode getRtnCode() {
		return rtnCode;
	}

	public void setRtnCode(ReturnCode rtnCode) {
		this.rtnCode = rtnCode;
	}

	public List<String> getRtnMsg() {
		return rtnMsg;
	}

	public void setRtnMsg(List<String> rtnMsg) {
		this.rtnMsg = rtnMsg;
	}
	
}
