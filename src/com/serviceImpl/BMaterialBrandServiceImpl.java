package com.serviceImpl;

import java.util.List;

import com.bean.BMaterialBrand;
import com.bean.ExtraBrand;
import com.dao.BMaterialBrandDao;

public class BMaterialBrandServiceImpl {
	private BMaterialBrandDao bmaterialBrandDao;

	public BMaterialBrandDao getBmaterialBrandDao() {
		return bmaterialBrandDao;
	}


	public void setBmaterialBrandDao(BMaterialBrandDao bmaterialBrandDao) {
		this.bmaterialBrandDao = bmaterialBrandDao;
	}


	/**
	 * 获取面料所对应所有品牌信息
	 * @return
	 */
	public List<ExtraBrand> findBrandsByMtlId(int mtlId){
		return bmaterialBrandDao.findBrandsByMtlId(mtlId);
	}
	
	/**
	 * 保存品牌信息
	 * @param bmBrand
	 */
	public void saveBrand(Integer mtlId,String brandNames){
		bmaterialBrandDao.saveBrand(mtlId,brandNames);
	}
	
	/**
	 * 获取产品所有品牌信息
	 * @return
	 */
	public List<ExtraBrand> findAllBrands(Integer mtlId){
		return bmaterialBrandDao.findAll(mtlId);
	}
}
