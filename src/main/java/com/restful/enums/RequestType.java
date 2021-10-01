package com.restful.enums;

public enum RequestType {
	
	/**
	 * 網頁來的請求
	 */
	AP_WEB("AP_WEB"),
	
	/**
	 * API請求
	 */
	API("API");
	
	private String message;	
	private RequestType( String msg ) {
		this.message = msg;
	}
	
	public String getMessage() {
		return message;
	}

}
