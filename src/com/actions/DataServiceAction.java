package com.actions;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.impl.SessionImpl;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionSupport;

public class DataServiceAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	public int state = 0;

	class Supplier{
		public String name;
		public String code;
	}
	
	class Product{
		public String product_code;
		public String tyna;
		public String colo;
	}
	
	class DaoBroker extends HibernateDaoSupport
	{
		// 调用存储过程，添加指定面料的适用品牌
		/**
		 * @param mtl_id ： 面料ID
		 * @param brands ： 面料适用品牌，字符串，品牌以逗号分隔
		 * @return ： 添加的记录数量
		 */
		public int addMtlBrand(int mtl_id, String brands)
		{
			int aff_count = 0;
			String brandList = brands; //StringUtils.join(brands, ","); // 把字符串数组连接成一个字符串
			
			Session session = null;
			CallableStatement callStmt = null;
			ResultSet resultSet = null;
			
			// 获取 Hibernate Session
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            session = sessionFactory.openSession();
            // 获取 Hibernate 里的 sql Connection 
			SessionImpl sessionImpl = (SessionImpl) session;
			Connection conn = sessionImpl.connection();
			
			//【注】由于存储过程中使用了事务，Java 代码必须使用事务控制，否则存储过程执行失败，且数据表出现“事务锁”
			Transaction tx = session.beginTransaction();  //在默认情况下，开启一个JDBC 事务
	        try{
	            callStmt = conn.prepareCall("exec sp_add_mtl_brand ?, ?");
	            callStmt.setInt(1, mtl_id);
	            callStmt.setString(2, brandList);
	            
	            callStmt.setEscapeProcessing(true);
	            aff_count = callStmt.executeUpdate();
	            
	            tx.commit();
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	            tx.rollback();
	        }
	        finally{
	            session.flush();
	            session.close();
	        }
	        
	        return aff_count;
		}
	
		public List<Product> getProduct(String product_code){
			List<Product> list=null;
			Query query=null;
			
			Session session = this.getSessionFactory().getCurrentSession();
			
			String sql="select * from product where product_code = :brands";
			
			query=session.createSQLQuery(sql);
			
			query.setString("product_code", product_code);
			
			list = query.list();
			
			return list;
		}
	}
	
	public List<Product> productList = null;
	public List<Supplier> supplierList = null;
	
	public String addMaterialBrand(){
		String s_mtl_id;
		String s_brands;
		
		HttpServletRequest request = ServletActionContext.getRequest();
		s_mtl_id = request.getParameter("mtlid");
		s_brands = request.getParameter("brns");
		
		int aff_count = 0;
		try{
			int mtl_id = Integer.parseInt(s_mtl_id);
			
			DaoBroker brk = new DaoBroker();
			aff_count = brk.addMtlBrand(mtl_id, s_brands);
			
			this.state = aff_count;
			
		}
		catch(NumberFormatException ex){
			ex.printStackTrace();
		}
		
		return "addMtlBrandResult";
	}
	
	// Action 方法
	public String getSupplier(){
		supplierList = new ArrayList<Supplier>();
		

		
		return "suppliers";
	}
	
	// Action 方法
	public String getProduct(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String product_code = request.getParameter("pdcode");
		
		productList = new ArrayList<Product>();
		
		DaoBroker brk = new DaoBroker();
		productList = brk.getProduct(product_code);
		
		return "products";
	}

}
