package com.dao;

import java.util.List;

import com.bean.BMaterialPropType;

public interface BMaterialPropTypeDao {

	public abstract void save(BMaterialPropType transientInstance);

	public abstract void delete(BMaterialPropType persistentInstance);

	public abstract BMaterialPropType findById(java.lang.Integer id);

	public abstract List findByExample(BMaterialPropType instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByMptName(Object mptName);

	public abstract List findByMtlTypeId(Object mtlTypeId);

	public abstract List findBySysUser(Object sysUser);

	public abstract List findAll();

	public abstract BMaterialPropType merge(BMaterialPropType detachedInstance);

}