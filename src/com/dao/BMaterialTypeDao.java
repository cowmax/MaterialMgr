package com.dao;

import java.util.List;

import com.bean.BMaterialType;
import com.bean.ExtraType;

public interface BMaterialTypeDao {

	public abstract void save(BMaterialType transientInstance);

	public abstract void delete(BMaterialType persistentInstance);

	public abstract BMaterialType findById(Integer id);

	public abstract List findByExample(BMaterialType instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findById(Object id);

	public abstract List findByPid(Object pid);

	public abstract List findByMtlTypeName(Object mtlTypeName);

	public abstract List findBySysUser(Object sysUser);

	public abstract List findAll();

	public abstract BMaterialType merge(BMaterialType detachedInstance);
	
	/**
	 * 获取面料父类型
	 * @return
	 */
	public abstract List<BMaterialType> loadParentTypeList();
	
	/**
	 * 根据父面料类型获取子类型集合
	 * @return
	 */
	public abstract List<BMaterialType> getMtlTypeListByPid(int pid);
	
	/**
	 * 获取所有面料子类型集合
	 * @return
	 */
	public abstract List<ExtraType> getAllChildTypeList();
	
	/**
	 * 获取所有的面料信息
	 * @return
	 */
	public abstract List<ExtraType> getAllTypeList();
	
	/**
	 * sql保存类型信息
	 */
	public abstract void saveType(BMaterialType mtype);

}