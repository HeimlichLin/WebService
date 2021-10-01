package com.restful.vo.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class LogResponseVo extends BaseResponseVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty( "CONTROL_NO" )
	private String controlNo;
	
	@JsonProperty( "VERSION" )
	private String version;
	
	@JsonProperty( "SEQ_NO" )
	private String seqNo;
	
	@JsonProperty( "BF_NO" )
	private String bfNo;
	
	@JsonProperty( "REF_BILL_NO" )
	private String refbillNo;
	
	@JsonProperty( "ITEM" )
	private String item;
	
	@JsonProperty( "LOG_STATUS" )
	private String logStatus;
	
	@JsonProperty( "LOG_RMK" )
	private String logRmk;

	public String getControlNo() {
		return controlNo;
	}

	public void setControlNo(String controlNo) {
		this.controlNo = controlNo;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getBfNo() {
		return bfNo;
	}

	public void setBfNo(String bfNo) {
		this.bfNo = bfNo;
	}

	public String getRefbillNo() {
		return refbillNo;
	}

	public void setRefbillNo(String refbillNo) {
		this.refbillNo = refbillNo;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getLogStatus() {
		return logStatus;
	}

	public void setLogStatus(String logStatus) {
		this.logStatus = logStatus;
	}

	public String getLogRmk() {
		return logRmk;
	}

	public void setLogRmk(String logRmk) {
		this.logRmk = logRmk;
	}

}
