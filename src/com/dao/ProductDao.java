package com.dao;

import java.util.List;

import com.bean.Product;

public interface ProductDao {

	public abstract void save(Product transientInstance);

	public abstract void delete(Product persistentInstance);

	public abstract Product findById(java.lang.Integer id);

	public abstract List findByExample(Product instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByProductCode(Object productCode);

	public abstract List findByStid(Object stid);

	public abstract List findByStno(Object stno);

	public abstract List findByInty(Object inty);

	public abstract List findByTyna(Object tyna);

	public abstract List findBySyea(Object syea);

	public abstract List findByColoId(Object coloId);

	public abstract List findByColo(Object colo);

	public abstract List findByCona(Object cona);

	public abstract List findBySzid(Object szid);

	public abstract List findBySzco(Object szco);

	public abstract List findByCpco(Object cpco);

	public abstract List findBySts(Object sts);

	public abstract List findBySourceBiid(Object sourceBiid);

	public abstract List findByProductDesc(Object productDesc);

	public abstract List findByBrad(Object brad);

	public abstract List findBySpno(Object spno);

	public abstract List findByRema(Object rema);

	public abstract List findByEdus(Object edus);

	public abstract List findByEdna(Object edna);

	public abstract List<Product> findAll(String sql);

	public abstract Product merge(Product detachedInstance);
	
	public abstract int getTotalCount(String sql);

}