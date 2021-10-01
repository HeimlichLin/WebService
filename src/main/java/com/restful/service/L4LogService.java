package com.restful.service;

import java.util.ArrayList;
import java.util.List;





import com.restful.bean.L4LogDo;
//import com.restful.dao.impl.L4LogDAOImpl;
import com.restful.vo.request.L4LogRequestVo;
import com.restful.vo.response.L4LogResponseVo;
import com.tradevan.common.db.DoXdaoSession;
import com.tradevan.common.db.EasySqlWhere;
import com.tradevan.taurus.xdao.SqlPredicate;

public class L4LogService {
//	private XdaoSessionManager xdaoSessionManager = new XdaoSessionManager();
//	private L4LogDAOImpl dao = L4LogDAOImpl.INSTANCE;
	
	public L4LogResponseVo query() {
//		final DoXdaoSession doXdaoSession = xdaoSessionManager.getDoXdaoSession();		
//		EasySqlWhere where = new EasySqlWhere();		
//        where.add(new SqlPredicate(L4LogDo.L4_TIME, ">=", "Trunc(SYSDATE-1)", false, false));
//        where.add(new SqlPredicate(L4LogDo.L4_TIME, "<=", "Trunc(SYSDATE)", false, false));
//        where.add(new SqlPredicate("substr(" + L4LogDo.LOG_STATUS + ",3,2)", "not in", "('10','07')", false, false));
//        where.add(new SqlPredicate(L4LogDo.LOG_RMK, "not like", "('%表頭已存在%')", false, false));
//        List<L4LogResponseVo> result = dao.select(doXdaoSession, where);
		L4LogResponseVo resVo = new L4LogResponseVo();
//		resVo.setL4LogList(result);
		return resVo;
	}
	
	public L4LogResponseVo query(L4LogRequestVo reqVo) {
//		final DoXdaoSession doXdaoSession = xdaoSessionManager.getDoXdaoSession();		
//		EasySqlWhere where = new EasySqlWhere();		
//        where.add(new SqlPredicate("to_char(" + L4LogDo.L4_TIME + ", 'YYYYMMDD')", "=", reqVo.getQueryDate(), false, false));
//        where.add(new SqlPredicate("substr(" + L4LogDo.LOG_STATUS + ",3,2)", "not in", "('10','07')", false, false));
//        where.add(new SqlPredicate(L4LogDo.LOG_RMK, "not like", "('%表頭已存在%')", false, false));
//        List<L4LogResponseVo> result = dao.select(doXdaoSession, where);
		L4LogResponseVo resVo = new L4LogResponseVo();
//		resVo.setL4LogList(result);
		return resVo;
	}

}
