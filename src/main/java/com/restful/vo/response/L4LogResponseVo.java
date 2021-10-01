package com.restful.vo.response;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class L4LogResponseVo extends LogResponseVo implements Serializable {
	private static final long serialVersionUID = 1L;
		
	@JsonProperty( "L4_TIME" )
	private String l4Time;
		
	@JsonProperty( "MSG_FUN" )
	private String msgFun;
		
	@JsonProperty( "DECL_NO" )
	private String declNo;
		
	@JsonProperty( "ITEM_NO" )
	private String itemNo;
		
	@JsonProperty( "DECL_SEQ_NO" )
	private String declSeqNo;
	
	@JsonProperty( "L4LOG" )
	private List<L4LogResponseVo> l4LogList;

	public String getL4Time() {
		return l4Time;
	}

	public void setL4Time(String l4Time) {
		this.l4Time = l4Time;
	}

	public String getMsgFun() {
		return msgFun;
	}

	public void setMsgFun(String msgFun) {
		this.msgFun = msgFun;
	}

	public String getDeclNo() {
		return declNo;
	}

	public void setDeclNo(String declNo) {
		this.declNo = declNo;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getDeclSeqNo() {
		return declSeqNo;
	}

	public void setDeclSeqNo(String declSeqNo) {
		this.declSeqNo = declSeqNo;
	}

	public List<L4LogResponseVo> getL4LogList() {
		return l4LogList;
	}

	public void setL4LogList(List<L4LogResponseVo> l4LogList) {
		this.l4LogList = l4LogList;
	}

}
