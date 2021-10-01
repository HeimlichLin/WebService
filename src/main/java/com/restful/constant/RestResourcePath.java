package com.restful.constant;

public class RestResourcePath {

	public static final String SECURE 		= "/secure";
	public static final String LOG 		    = "/LOG";
	
	public static final class LOG_PATH {
		public static final String L4_LASTDAY_LOG 	= "/l4_lastday_log";
		
		public static final String GET_L4_LOG 	= "/get_l4_log";
	}
	
	/** 安全性API */
	public static final class SECURE_PATH {
		/** 取得所有類型資料的筆數 */
		public static final String GET_DES_KEY 	= "/get_des_key";	
		/** 取得報單資料 */
		public static final String GET_ACCESS_CODE	= "/get_access_code";		
	}
	
}
