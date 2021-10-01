package com.restful.constant;

public class Constant {
    
    public static final class SESSION_KEY {
    	
    	/**
    	 * SAAB USER
    	 * val = {CUSTOM_ID=A0001, LOGIN_ERR_NUM=0, USER_ID=N_97162640_A0001, EXT_ID=97162640, USER_NAME=自貿港系統管理者, USER_TYPE=N, LOGIN_LOCK_NUM=0, STATUS=U, USER_PASSWORD=2B348918419ECC2D4602C8475EDE8C187EDFF5C11F8FCBFBD874D94FC0C8E6A7, PWD_CHG_INTERVAL=0, BUILD_DATE=20120827150336}
    	 */
    	public static final String SAAB_USER 		= "__saab_user"; 
    	
    	/**
    	 * 監管編號
    	 * val = C0001
    	 */
    	public static final String BF_NO 			= "bfNo"; 
    	
    	/**
    	 * LOGIN DES KEY
    	 * Val = byte[] <-- 64bits
    	 */
    	public static final String GET_ACCESS_CODE_DES_KEY = "get_access_code_des_key";
    }
    
}
