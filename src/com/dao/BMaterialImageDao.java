package com.dao;

import java.util.List;

import com.bean.BMaterialImage;

public interface BMaterialImageDao {

	public abstract void save(BMaterialImage transientInstance);

	public abstract void delete(BMaterialImage persistentInstance);

	public abstract BMaterialImage findById(java.lang.Integer id);

	public abstract List findByExample(BMaterialImage instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByImgTitle(Object imgTitle);

	public abstract List findByImgDesciption(Object imgDesciption);

	public abstract List findByImgUrl(Object imgUrl);

	public abstract List findByImgSizeW(Object imgSizeW);

	public abstract List findByImgSizeH(Object imgSizeH);

	public abstract List findByImgColor(Object imgColor);

	public abstract List findAll();

	public abstract BMaterialImage merge(BMaterialImage detachedInstance);
	
	/**
	 * 查找所有的照片颜色dao
	 */
	public List  getAllImageColor();
	
	/**
	 * 查找添加的照片dao
	 */
	public List<BMaterialImage> getallPhoto(Integer mtlId);
		
}