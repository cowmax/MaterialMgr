package com.dao;

import java.util.List;

import com.bean.BMaterialSupplier;

public interface BMaterialSupplierDao {

	public abstract void save(BMaterialSupplier transientInstance);

	public abstract void delete(BMaterialSupplier persistentInstance);

	public abstract BMaterialSupplier findById(java.lang.Integer id);

	public abstract List findByExample(BMaterialSupplier instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByMtsCode(Object mtsCode);

	public abstract List findByMtsName(Object mtsName);

	public abstract List findByMtsColorCount(Object mtsColorCount);

	public abstract List<BMaterialSupplier> findAll(Integer mtlId);

	public abstract BMaterialSupplier merge(BMaterialSupplier detachedInstance);

}