package com.serviceImpl;

import java.util.List;

import com.bean.Product;
import com.dao.ProductDao;

public class ProductServiceImpl {

	private ProductDao productDao;

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public List<Product> findAllProducts(String sql){
		return productDao.findAll(sql);
	}
	
	public int getTotalCount(String sql) {
		return productDao.getTotalCount(sql);
	}
}
