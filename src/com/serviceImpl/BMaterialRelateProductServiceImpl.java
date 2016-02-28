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
	 * ��ȡ������ز�Ʒ��Ϣ����
	 * @param mtlId
	 * @return
	 */
	public List<BMaterialRelateProduct> getRelateProducts(Integer mtlId){
		return bmaterialRelateProductDao.findAll(mtlId);
	}
	
	/**
	 * ����������ز�Ʒ��Ϣ
	 * @param relateProduct
	 */
	public void saveRelateProduct(BMaterialRelateProduct relateProduct){
		bmaterialRelateProductDao.save(relateProduct);
	}
	
	/**
	 * ɾ�����������Ϣ
	 * @param relateProduct
	 */
	public void delRelateProduct(Integer mrpId){
		BMaterialRelateProduct relateProduct = bmaterialRelateProductDao.findById(mrpId);
		bmaterialRelateProductDao.delete(relateProduct);
	}
}
