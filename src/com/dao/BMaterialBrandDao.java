package com.dao;

import java.util.List;

import com.bean.BMaterialBrand;
import com.bean.ExtraBrand;

public interface BMaterialBrandDao {

	public abstract void save(BMaterialBrand transientInstance);

	public abstract void delete(BMaterialBrand persistentInstance);

	public abstract BMaterialBrand findById(java.lang.Integer id);

	public abstract List findByExample(BMaterialBrand instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByBrandName(Object brandName);

	public abstract List findByBrand(Object brand);

	public abstract List<ExtraBrand> findAll(Integer mtlId);

	public abstract BMaterialBrand merge(BMaterialBrand detachedInstance);
	
	public abstract List<ExtraBrand> findBrandsByMtlId(int mtlId);
	
	public abstract void saveBrand(Integer mtlId,String brandNames);

}