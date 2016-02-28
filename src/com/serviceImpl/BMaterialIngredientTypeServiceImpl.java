package com.serviceImpl;

import java.util.List;

import com.bean.BMaterialIngredientType;
import com.dao.BMaterialIngredientTypeDao;

public class BMaterialIngredientTypeServiceImpl {
	private BMaterialIngredientTypeDao bmaterialIngredientTypeDao;
	private List<BMaterialIngredientType> allIingType;

	public BMaterialIngredientTypeDao getBmaterialIngredientTypeDao() {
		return bmaterialIngredientTypeDao;
	}

	public void setBmaterialIngredientTypeDao(
			BMaterialIngredientTypeDao bmaterialIngredientTypeDao) {
		this.bmaterialIngredientTypeDao = bmaterialIngredientTypeDao;
	}

	public List<BMaterialIngredientType> getAllIingType() {
		return allIingType;
	}

	public void setAllIingType(List<BMaterialIngredientType> allIingType) {
		this.allIingType = allIingType;
	}
	
	/**
	 * 获取所有成分集合
	 * @return
	 */
	public List<BMaterialIngredientType> findAllIngredientType(){
		return bmaterialIngredientTypeDao.findAll();
	}
}
