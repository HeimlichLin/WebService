package com.restful.service;

import com.restful.model.AccessCodeModel;
import com.restful.vo.common.AccessCodeVo;

public class AccessCodeService {
	
	private static AccessCodeService instance;
	public static AccessCodeService getInstance() {
		if ( null == instance ) {
			instance = new AccessCodeService();
		}
		return instance;
	}
	
	private AccessCodeModel accessCodeModel = AccessCodeModel.getInstance();	

	public void insertAccessCode( AccessCodeVo vo ) throws Exception {
		accessCodeModel.insertAccessCode(vo);
	}
	
	public AccessCodeVo getAccessCodeVoByCode( String code ) throws Exception {
		return accessCodeModel.getAccessCodeVoByCode(code);
	}
	
	public AccessCodeVo getActiveAccessCodeVoByUserId( String userId ) throws Exception {
		return accessCodeModel.getActiveAccessCodeVoByUserId(userId);
	}
	
}
