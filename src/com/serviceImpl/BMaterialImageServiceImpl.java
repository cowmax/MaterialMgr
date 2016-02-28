package com.serviceImpl;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import com.bean.BMaterialImage;
import com.dao.BMaterialImageDao;

/**
 * 照片service类
 */

public class BMaterialImageServiceImpl {

	private BMaterialImageDao bmaterialImageDao;
	private SessionFactory sessionFactory;
	
	
	public BMaterialImageDao getBmaterialImageDao() {
		return bmaterialImageDao;
	}

	public void setBmaterialImageDao(BMaterialImageDao bmaterialImageDao) {
		this.bmaterialImageDao = bmaterialImageDao;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 获取所以不相同的颜色
	 */
	public List getImagecColor(){
		List list=bmaterialImageDao.getAllImageColor();
		return list;
	}
	
	
	
	/**
	 * 保存照片
	 */
	public Boolean saveBMaterialImage(BMaterialImage bmaterialImage){
		bmaterialImageDao.save(bmaterialImage);
	
		return true;
	}
	
	
	/**
	 * 查找添加的照片service
	 */
	public List<BMaterialImage> getImageUlr(Integer mtlId){
		List<BMaterialImage> list=bmaterialImageDao.getallPhoto(mtlId);
		
		return list;
	}
	
	//删除面料照片
	public void deleteImage(Integer imgId){
		BMaterialImage image = bmaterialImageDao.findById(imgId);
		bmaterialImageDao.delete(image);
	}
	
}
