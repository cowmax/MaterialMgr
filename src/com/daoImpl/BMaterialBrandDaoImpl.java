package com.daoImpl;

import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.BMaterial;
import com.bean.BMaterialBrand;
import com.bean.BMaterialSupplier;
import com.bean.BMaterialType;
import com.bean.ExtraBrand;
import com.bean.Suin;
import com.dao.BMaterialBrandDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * BMaterialBrand entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.BMaterialBrand
 * @author MyEclipse Persistence Tools
 */
public class BMaterialBrandDaoImpl extends HibernateDaoSupport implements BMaterialBrandDao {
	private static final Logger log = LoggerFactory
			.getLogger(BMaterialBrandDaoImpl.class);
	// property constants
	public static final String BRAND_NAME = "brandName";
	public static final String BRAND = "brand";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialBrandDao#save(com.bean.BMaterialBrand)
	 */
	@Override
	public void save(BMaterialBrand transientInstance) {
		log.debug("saving BMaterialBrand instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialBrandDao#delete(com.bean.BMaterialBrand)
	 */
	@Override
	public void delete(BMaterialBrand persistentInstance) {
		log.debug("deleting BMaterialBrand instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialBrandDao#findById(java.lang.Integer)
	 */
	@Override
	public BMaterialBrand findById(java.lang.Integer id) {
		log.debug("getting BMaterialBrand instance with id: " + id);
		try {
			BMaterialBrand instance = (BMaterialBrand) getHibernateTemplate()
					.get("com.bean.BMaterialBrand", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialBrandDao#findByExample(com.bean.BMaterialBrand)
	 */
	@Override
	public List findByExample(BMaterialBrand instance) {
		log.debug("finding BMaterialBrand instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialBrandDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BMaterialBrand instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BMaterialBrand as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialBrandDao#findByBrandName(java.lang.Object)
	 */
	@Override
	public List findByBrandName(Object brandName) {
		return findByProperty(BRAND_NAME, brandName);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialBrandDao#findByBrand(java.lang.Object)
	 */
	@Override
	public List findByBrand(Object brand) {
		return findByProperty(BRAND, brand);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialBrandDao#findAll()
	 */
	@Override
	public List<ExtraBrand> findAll(Integer mtlId) {
		Session session = getSession();  
		SQLQuery query=null;
		String sql="execute [dbo].[sp_get_brands] "+mtlId;
		List<ExtraBrand> ebrandList = new ArrayList<ExtraBrand>();
		query=session.createSQLQuery(sql);
		List<Object[]> resultSet = query.list();
		for (Object[] r : resultSet) {
			ExtraBrand ebrand = new ExtraBrand();
			ebrand.setBrand((String)r[0]);
			ebrand.setBrandName((String)r[1]);
			String brand = (String)r[2];
			boolean choose=false;
			if(brand!=null){
				choose=true;
			}
			ebrand.setChoose(choose);
			ebrandList.add(ebrand);
		}
		return ebrandList;
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialBrandDao#merge(com.bean.BMaterialBrand)
	 */
	@Override
	public BMaterialBrand merge(BMaterialBrand detachedInstance) {
		log.debug("merging BMaterialBrand instance");
		try {
			BMaterialBrand result = (BMaterialBrand) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BMaterialBrand instance) {
		log.debug("attaching dirty BMaterialBrand instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BMaterialBrand instance) {
		log.debug("attaching clean BMaterialBrand instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BMaterialBrandDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (BMaterialBrandDao) ctx.getBean("bmaterialBrandDao");
	}

	/**
	 * 根据mtl_id获取品牌信息
	 */
	@Override
	public List<ExtraBrand> findBrandsByMtlId(int mtlId) {
		List<ExtraBrand> list = new ArrayList<ExtraBrand>();
		Session session = getSession();  
		SQLQuery query=null;
		String sql="select brand_name,brand from b_Material_Brand where mtl_id = ? ";
		query=session.createSQLQuery(sql);
		query.setInteger(0, mtlId);
		List<Object[]> resultSet = query.list();
		for (Object[] r : resultSet) {
			ExtraBrand ebrand = new ExtraBrand();
			ebrand.setBrandName((String)r[0]);
			ebrand.setBrand((String)r[1]);
			list.add(ebrand);
		}
		return list;
		
	}
	
	public void saveBrand(Integer mtlId,String brandNames){
		Session session = getSession();  
		Transaction trans = session.beginTransaction();
		try {
			trans.begin();
			//调用存储过程转存到para_dt
			String procdure = "{Call sp_add_mtl_brand (?,?)}";  
			CallableStatement cs;
			cs = session.connection().prepareCall(procdure);
			cs.setInt(1, mtlId);
			cs.setString(2, brandNames);
			cs.execute();
			
			//提交事物
			trans.commit();
			
		} catch (Exception e) {
			trans.rollback();
		}
		session.flush(); 
	}
}