package com.dao;

import java.util.List;

import com.bean.BMaterialIngredient;

public interface BMaterialIngredientDao {

	public abstract void save(BMaterialIngredient transientInstance);

	public abstract void delete(BMaterialIngredient persistentInstance);

	public abstract BMaterialIngredient findById(java.lang.Integer id);

	public abstract List findByExample(BMaterialIngredient instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByIngredientName(Object ingredientName);

	public abstract List findByPrecent(Object precent);

	public abstract List findAll();

	public abstract BMaterialIngredient merge(BMaterialIngredient detachedInstance);
	
	/**
	 * 查询所有面料
	 */
	public abstract List<BMaterialIngredient> findAllBMIngred(Integer mtlId);
	
	
	/**
	 * 删除面料
	 */
	public void  delBMIngred(Integer id);
	
}