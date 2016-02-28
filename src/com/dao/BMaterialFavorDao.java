package com.dao;

import java.util.List;

import com.bean.BMaterialFavor;

public interface BMaterialFavorDao {

	public abstract void save(BMaterialFavor transientInstance);

	public abstract void delete(BMaterialFavor persistentInstance);

	public abstract BMaterialFavor findById(java.lang.Integer id);

	public abstract List findByExample(BMaterialFavor instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByMemo(Object memo);

	public abstract List findBySysUser(Object sysUser);

	public abstract List findByRate(Object rate);

	public abstract List findByProductCode(Object productCode);

	public abstract List findByStatus(Object status);

	public abstract List findByFvrDesc(Object fvrDesc);

	public abstract List findAll();

	public abstract BMaterialFavor merge(BMaterialFavor detachedInstance);

}