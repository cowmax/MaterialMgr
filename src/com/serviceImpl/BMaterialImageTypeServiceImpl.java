package com.serviceImpl;


import java.util.List;

import org.hibernate.SessionFactory;

import com.bean.BMaterialImageType;
import com.dao.BMaterialImageDao;
import com.dao.BMaterialImageTypeDao;

/**
 * 照片类型service类
 */

public class BMaterialImageTypeServiceImpl {

	private BMaterialImageTypeDao bmaterialImageTypeDao;
	
	
	//get、set
	public BMaterialImageTypeDao getBmaterialImageTypeDao() {
		return bmaterialImageTypeDao;
	}


	public void setBmaterialImageTypeDao(BMaterialImageTypeDao bmaterialImageTypeDao) {
		this.bmaterialImageTypeDao = bmaterialImageTypeDao;
	}
	
	/**
	 * 获取所有照片信息
	 */
	public List<BMaterialImageType>  allImageType(){
		List<BMaterialImageType> list=bmaterialImageTypeDao.findAll();
		return list;
	}


	/**
	 * 添加照片类型
	 */
	public Boolean addImage(BMaterialImageType bmaterialImageType){
		bmaterialImageTypeDao.save(bmaterialImageType);
		return true;
	}
	
	/**
	 * 删除照片类型
	 */
	public boolean  delImage(BMaterialImageType imgType){
		bmaterialImageTypeDao.delete(imgType);
		return true;
		
	}
}
