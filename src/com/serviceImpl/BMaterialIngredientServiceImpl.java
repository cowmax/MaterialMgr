package com.serviceImpl;

import java.util.List;

import com.bean.BMaterialIngredient;
import com.dao.BMaterialIngredientDao;
/**
 * 面料成分
 */
import com.opensymphony.xwork2.ActionSupport;

public class BMaterialIngredientServiceImpl {

	private BMaterialIngredientDao ingredientDao;//成分表

	
	//get set
	public BMaterialIngredientDao getIngredientDao() {
		return ingredientDao;
	}

	public void setIngredientDao(BMaterialIngredientDao ingredientDao) {
		this.ingredientDao = ingredientDao;
	}

	
	/**
	 * 添加面料成分
	 */
	public Boolean  addIngredient(BMaterialIngredient bmaterialIngredient){
		ingredientDao.save(bmaterialIngredient);
		return true;
	}
	


	/**
	 * 删除面料成分
	 */
	public Boolean delIngredient(Integer id){
		ingredientDao.delBMIngred(id);
		return true;
	}
	
	/**
	 * 查询所有成分
	 */
	public List<BMaterialIngredient> getIngredient(Integer id){
		
		List<BMaterialIngredient> list=ingredientDao.findAllBMIngred(id);
		
		return list;
	}
}
