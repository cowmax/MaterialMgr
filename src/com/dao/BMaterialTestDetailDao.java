package com.dao;

import java.util.List;

import com.bean.BMaterialTestDetail;

public interface BMaterialTestDetailDao {

	public abstract void save(BMaterialTestDetail transientInstance);

	public abstract void delete(BMaterialTestDetail persistentInstance);

	public abstract BMaterialTestDetail findById(java.lang.Integer id);

	public abstract List findByExample(BMaterialTestDetail instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findAll();

	public abstract BMaterialTestDetail merge(
			BMaterialTestDetail detachedInstance);

}