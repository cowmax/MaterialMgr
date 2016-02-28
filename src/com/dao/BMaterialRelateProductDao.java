package com.dao;

import java.util.List;

import com.bean.BMaterialRelateProduct;

public interface BMaterialRelateProductDao {

	public abstract void save(BMaterialRelateProduct transientInstance);

	public abstract void delete(BMaterialRelateProduct persistentInstance);

	public abstract BMaterialRelateProduct findById(java.lang.Integer id);

	public abstract List findByExample(BMaterialRelateProduct instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByMemo(Object memo);

	public abstract List findByRelateStatus(Object relateStatus);

	public abstract List findByProductCode(Object productCode);

	public abstract List<BMaterialRelateProduct> findAll(Integer mtlId);

	public abstract BMaterialRelateProduct merge(
			BMaterialRelateProduct detachedInstance);

}