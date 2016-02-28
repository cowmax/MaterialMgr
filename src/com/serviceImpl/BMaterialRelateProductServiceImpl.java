package com.serviceImpl;

import java.util.List;

import com.bean.BMaterialRelateProduct;
import com.dao.BMaterialRelateProductDao;

public class BMaterialRelateProductServiceImpl {

	private BMaterialRelateProductDao bmaterialRelateProductDao;

	public BMaterialRelateProductDao getBmaterialRelateProductDao() {
		return bmaterialRelateProductDao;
	}

	public void setBmaterialRelateProductDao(
			BMaterialRelateProductDao bmaterialRelateProductDao) {
		this.bmaterialRelateProductDao = bmaterialRelateProductDao;
	}
	
	/**
	 * 获取面料相关产品信息集合
	 * @param mtlId
	 * @return
	 */
	public List<BMaterialRelateProduct> getRelateProducts(Integer mtlId){
		return bmaterialRelateProductDao.findAll(mtlId);
	}
	
	/**
	 * 保存面料相关产品信息
	 * @param relateProduct
	 */
	public void saveRelateProduct(BMaterialRelateProduct relateProduct){
		bmaterialRelateProductDao.save(relateProduct);
	}
	
	/**
	 * 删除面料相关信息
	 * @param relateProduct
	 */
	public void delRelateProduct(Integer mrpId){
		BMaterialRelateProduct relateProduct = bmaterialRelateProductDao.findById(mrpId);
		bmaterialRelateProductDao.delete(relateProduct);
	}
}
