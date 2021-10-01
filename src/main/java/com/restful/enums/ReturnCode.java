package com.restful.enums;

public enum ReturnCode {
	
	/**
	 * 完成業務邏輯，查詢類不論成功與否皆為SUCCESS
	 */
	SUCCESS("成功"),
	
	/**
	 * 無法完成業務邏輯
	 */
	FAILURE("失敗"),
	
	/**
	 * 錯誤的請求
	 */
	INVAILD("錯誤的請求"),
	
	/**
	 * 參數錯誤、身分認證錯誤、金鑰錯誤
	 */
	ILLEGAL("非法的請求"), 
	
	/**
	 * 超過額定時間
	 */
	TIMEOUT("逾時"),
	
	/**
	 * 無具有執行特定業務權限
	 */
	NO_PERMISSION("沒有權限"),
	
	/**
	 * 未知的錯誤
	 */
	UNKNOW_ERROR("未知錯誤請聯絡管理員");	
	
	private String message;	
	private ReturnCode( String msg ) {
		this.message = msg;
	}
	
	public String getMessage() {
		return message;
	}

}
