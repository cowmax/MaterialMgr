package com.dao;

import java.util.List;

import com.bean.BMaterialProp;

public interface BMaterialPropDao {

	public abstract void save(BMaterialProp transientInstance);

	public abstract void delete(BMaterialProp persistentInstance);

	public abstract BMaterialProp findById(java.lang.Integer id);

	public abstract List findByExample(BMaterialProp instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByPropValue(Object propValue);

	public abstract List findBySysUser(Object sysUser);

	public abstract List findByMtlCode(Object mtlCode);

	public abstract List findAll();

	public abstract BMaterialProp merge(BMaterialProp detachedInstance);

}