package com.dao;

import java.util.List;

import com.bean.BMaterialFavorDetail;

public interface BMaterialFavorDetailDao {

	public abstract void save(BMaterialFavorDetail transientInstance);

	public abstract void delete(BMaterialFavorDetail persistentInstance);

	public abstract BMaterialFavorDetail findById(java.lang.Integer id);

	public abstract List findByExample(BMaterialFavorDetail instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByDtlMemo(Object dtlMemo);

	public abstract List findByPlaceType(Object placeType);

	public abstract List findByStatus(Object status);

	public abstract List findAll();

	public abstract BMaterialFavorDetail merge(
			BMaterialFavorDetail detachedInstance);

}