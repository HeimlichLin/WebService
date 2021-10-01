package com.restful.vo.common;

import java.io.Serializable;

public class AccessCodeVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/** CODE VARCHAR2(64) 存取碼 UUID */
	private String code;	
	
	/** CREATE DATE TIMESTAMP(6) 建立時間 */
	private java.sql.Timestamp createDate;
	
	/** EXPIRED_DATE TIMESTAMP(6) 失效時間 */
	private java.sql.Timestamp expiredDate;
	
	/** STATUS VARCHAR2(2) 狀態 A = Active, D = Disable, F = Frezze */
	private String status;
	
	/** USER_ID	VARCHAR2(64) SAAB USER ID */
	private String userId;
	
	/** REG_REMOTE_ADDR VARCHAR2(64) 註冊IP */
	private String regAccessRemoteAddr;
	
	/** LAST_ACC_REMOTE_ADDR VARCHAR2(64) 最後一次存取的IP */
	private String lastAccessRemoteAddr;
	
	/** LAST_ACC_DATE TIMESTAMP(6) 最後一次存取時間 */
	private java.sql.Timestamp lastAccessDate;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public java.sql.Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.sql.Timestamp createDate) {
		this.createDate = createDate;
	}

	public java.sql.Timestamp getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(java.sql.Timestamp expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRegAccessRemoteAddr() {
		return regAccessRemoteAddr;
	}

	public void setRegAccessRemoteAddr(String regAccessRemoteAddr) {
		this.regAccessRemoteAddr = regAccessRemoteAddr;
	}

	public String getLastAccessRemoteAddr() {
		return lastAccessRemoteAddr;
	}

	public void setLastAccessRemoteAddr(String lastAccessRemoteAddr) {
		this.lastAccessRemoteAddr = lastAccessRemoteAddr;
	}

	public java.sql.Timestamp getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(java.sql.Timestamp lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}
	
}
