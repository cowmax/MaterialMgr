package com.dao;

import java.util.List;

import com.bean.BMaterialImageType;

public interface BMaterialImageTypeDao {

	public abstract void save(BMaterialImageType transientInstance);

	public abstract void delete(BMaterialImageType imgType);

	public abstract BMaterialImageType findById(java.lang.String id);

	public abstract List findByExample(BMaterialImageType instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByImgTypeName(Object imgTypeName);

	public abstract List findByMemo(Object memo);

	public abstract List findAll();

	public abstract BMaterialImageType merge(BMaterialImageType detachedInstance);



}