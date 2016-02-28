package com.dao;

import java.util.List;

import com.bean.BMaterialIngredientType;

public interface BMaterialIngredientTypeDao {

	public abstract void save(BMaterialIngredientType transientInstance);

	public abstract void delete(BMaterialIngredientType persistentInstance);

	public abstract BMaterialIngredientType findById(java.lang.Integer id);

	public abstract List findByExample(BMaterialIngredientType instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByIngredientType(Object ingredientType);

	public abstract List findAll();

	public abstract BMaterialIngredientType merge(
			BMaterialIngredientType detachedInstance);

}