package com.serviceImpl;

import com.bean.BMaterialType;
import com.dao.BMaterialTypeDao;

public class BMaterialTypeServiceImpl{

	private BMaterialTypeDao bmaterialTypeDao;
	
	public BMaterialTypeDao getBmaterialTypeDao() {
		return bmaterialTypeDao;
	}

	public void setBmaterialTypeDao(BMaterialTypeDao bmaterialTypeDao) {
		this.bmaterialTypeDao = bmaterialTypeDao;
	}

	public void saveType(BMaterialType btype){
		bmaterialTypeDao.save(btype);
	}
}
