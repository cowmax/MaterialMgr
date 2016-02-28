package com.serviceImpl;

import java.util.List;

import com.bean.BMaterialSupplier;
import com.dao.BMaterialSupplierDao;

public class BMaterialSupplierSerivceImpl {

	private BMaterialSupplierDao bmaterialSupplierDao;

	public BMaterialSupplierDao getBmaterialSupplierDao() {
		return bmaterialSupplierDao;
	}

	public void setBmaterialSupplierDao(BMaterialSupplierDao bmaterialSupplierDao) {
		this.bmaterialSupplierDao = bmaterialSupplierDao;
	}
	
//	/**
//	 * 获取所有供应商信息
//	 * @return
//	 */
//	public List getAllSupplierList(){
//		return bmaterialSupplierDao.findAll();
//	}
//	
	/**
	 * 删除用户信息
	 * @param supplier
	 * @return
	 */
	public boolean delSupplier(BMaterialSupplier supplier){
		boolean isSuccess = true;
		try {
			bmaterialSupplierDao.delete(supplier);
		} catch (Exception e) {
			isSuccess = false;
		}
		return isSuccess;
	}
	
	/**
	 * 根据ID查找当相对应的供应商
	 * @param id
	 * @return
	 */
	public BMaterialSupplier findSupplierById(int id){
		return bmaterialSupplierDao.findById(id);
	}
	
	/**
	 * 添加供应商信息
	 * @param supplier
	 * @return
	 */
	public boolean saveSupplier(BMaterialSupplier supplier){
		boolean isSuccess = true;
		try {
			bmaterialSupplierDao.save(supplier);
		} catch (Exception e) {
			isSuccess = false;
		}
		return isSuccess;
	}
	
	/**
	 * 修改供应商信息
	 * @param supplier
	 * @return
	 */
	public BMaterialSupplier mergerSupplier(BMaterialSupplier supplier){
		return bmaterialSupplierDao.merge(supplier);
	}
	
	/**
	 * 获取面料供应商
	 * @param mtlId
	 * @return
	 */
	public List<BMaterialSupplier> getSuppliersByMtlId(Integer mtlId){
		return bmaterialSupplierDao.findAll(mtlId);
	}
}
