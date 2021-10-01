package com.restful.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.restful.vo.common.AccessCodeVo;

public class AccessCodeModel {
	
	private static AccessCodeModel instance;
	public static AccessCodeModel getInstance() {
		if ( null == instance ) {
			instance = new AccessCodeModel();
		}
		return instance;
	}
	
	private static final String TABLE_NAME = "ACCESS_CODE";
	private static final String CONN_ID = "apFtzcConn";
	
	/**
	 * 新增AccessCode
	 */
	public void insertAccessCode( AccessCodeVo vo ) throws Exception {
//		String sql = " INSERT INTO ACCESS_CODE( CODE, CREATE_DATE, EXPIRED_DATE, STATUS, USER_ID, REG_REMOTE_ADDR, LAST_ACC_REMOTE_ADDR, LAST_ACC_DATE) VALUES( ?, ?, ?, ?, ?, ?, ?, ? ) ";
//		XdaoConnection xdaoConn = null;
//		try {
//			xdaoConn = this.factory.getXdaoConnection(CONN_ID);
//			xdaoConn.open();
//			PreparedStatement pstmt = xdaoConn.getPreparedStatement(sql);
//			pstmt.setObject(1, vo.getCode());
//			pstmt.setObject(2, vo.getCreateDate());
//			pstmt.setObject(3, vo.getExpiredDate());
//			pstmt.setObject(4, vo.getStatus());
//			pstmt.setObject(5, vo.getUserId());
//			pstmt.setObject(6, vo.getRegAccessRemoteAddr());
//			pstmt.setObject(7, vo.getLastAccessRemoteAddr());
//			pstmt.setObject(8, vo.getLastAccessDate());
//			
//			int insertCnt = pstmt.executeUpdate();
//						
//		} catch ( Exception e ) {
//			e.printStackTrace();
//			if ( null != xdaoConn ) {
//				xdaoConn.rollback();
//			}
//			throw e;
//		} finally {
//			if ( null != xdaoConn ) {
//				xdaoConn.close();
//			}
//		}
//		
	}
	
	/**
	 * 查詢 AccessCode 使用 Code
	 * pkey
	 */
	public AccessCodeVo getAccessCodeVoByCode( String code ) throws Exception {
		List<AccessCodeVo> result = getAccessCode( code, null, null, false, true );
		if ( null == result || result.size() < 1 ) {
			return null;
		}
		return result.get(0);
	}
	
	/**
	 * 依 userId 取得 狀態 "A" 且未過期 的AccessCode
	 * 只取一筆
	 */
	public AccessCodeVo getActiveAccessCodeVoByUserId( String userId ) throws Exception {
		List<AccessCodeVo> result = getAccessCode( null, userId, "A", true, true );
		if ( null == result || result.size() < 1 ) {
			return null;
		}
		return result.get(0);
	}
	
	private List<AccessCodeVo> getAccessCode( String code, String userId, String status, boolean unexpired, boolean single ) throws Exception {		
//		XdaoConnection xdaoConn = null;
		List<AccessCodeVo> result = null;
//		try {
//			// SQL
//			StringBuffer baseSql = new StringBuffer( " SELECT * FROM ACCESS_CODE WHERE  1 = 1 " );
//			Map<Integer, Object> params = new HashMap<Integer, Object>();
//			int paramIndex = 1;
//			if ( null != code ) {
//				baseSql.append( " AND CODE = ? " );
//				params.put( paramIndex++, code ); 
//			}
//			if ( null != userId ) {
//				baseSql.append( " AND USER_ID = ? " );
//				params.put( paramIndex++, userId ); 
//			}
//			if ( null != status ) {
//				baseSql.append( " AND STATUS = ? " );
//				params.put( paramIndex++, status ); 
//			}
//			if ( unexpired ) {
//				baseSql.append( " AND EXPIRED_DATE > SYSDATE " );
//			}		
//			if ( single ) {
//				baseSql.append( " AND ROWNUM = 1 " );
//			}
//			baseSql.append( " ORDER BY USER_ID, CREATE_DATE, EXPIRED_DATE " );
//			String finalSql = baseSql.toString();
//			
//			// Do Query
//			xdaoConn = this.factory.getXdaoConnection( CONN_ID );
//			xdaoConn.open();
//			PreparedStatement pstmt = xdaoConn.getPreparedStatement(finalSql);			
//			for ( Integer key : params.keySet() ) {
//				Object val = params.get(key);
//				pstmt.setObject( key, val );
//			}			
//			ResultSet rs = pstmt.executeQuery();
//			result = new ArrayList<AccessCodeVo>();
//			while ( rs.next() ) {
//				AccessCodeVo vo = new AccessCodeVo();
//				vo.setCode( 				rs.getString( 	 "CODE" ) );
//				vo.setCreateDate( 			rs.getTimestamp( "CREATE_DATE" ) );
//				vo.setExpiredDate( 			rs.getTimestamp( "EXPIRED_DATE" ) );
//				vo.setLastAccessDate( 		rs.getTimestamp( "LAST_ACC_DATE" ) );
//				vo.setLastAccessRemoteAddr( rs.getString( 	 "LAST_ACC_REMOTE_ADDR" ) );
//				vo.setRegAccessRemoteAddr( 	rs.getString( 	 "REG_REMOTE_ADDR" ) );
//				vo.setStatus( 				rs.getString( 	 "STATUS" ) );
//				vo.setUserId( 				rs.getString( 	 "USER_ID" ) );
//				result.add( vo );
//			}
//			
//		} catch ( Exception e ) {
//			e.printStackTrace();
//			throw e;
//		} finally {
//			if ( null != xdaoConn ) {
//				xdaoConn.close();
//			}
//		}
		return result;		
	}
}
