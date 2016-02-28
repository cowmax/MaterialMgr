package com.daoImpl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.BMaterial;
import com.bean.BMaterialRelateProduct;
import com.bean.ExtraType;
import com.dao.BMaterialRelateProductDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * BMaterialRelateProduct entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.BMaterialRelateProduct
 * @author MyEclipse Persistence Tools
 */
public class BMaterialRelateProductDaoImpl extends HibernateDaoSupport implements BMaterialRelateProductDao {
	private static final Logger log = LoggerFactory
			.getLogger(BMaterialRelateProductDaoImpl.class);
	// property constants
	public static final String MEMO = "memo";
	public static final String RELATE_STATUS = "relateStatus";
	public static final String PRODUCT_CODE = "productCode";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialRelateProductDao#save(com.bean.BMaterialRelateProduct)
	 */
	@Override
	public void save(BMaterialRelateProduct transientInstance) {
		Session session = getSession();  
		SQLQuery query=null;
		
		String sql="insert into b_Material_Relate_Product (mtl_id,product_code,memo) values (?,?,?)";
		query=session.createSQLQuery(sql);
		query.setInteger(0, transientInstance.getBMaterial().getMtlId());
		query.setString(1, transientInstance.getProductCode());
		query.setString(2, transientInstance.getMemo());
		
		query.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialRelateProductDao#delete(com.bean.BMaterialRelateProduct)
	 */
	@Override
	public void delete(BMaterialRelateProduct persistentInstance) {
		log.debug("deleting BMaterialRelateProduct instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialRelateProductDao#findById(java.lang.Integer)
	 */
	@Override
	public BMaterialRelateProduct findById(java.lang.Integer id) {
		log.debug("getting BMaterialRelateProduct instance with id: " + id);
		try {
			BMaterialRelateProduct instance = (BMaterialRelateProduct) getHibernateTemplate()
					.get("com.bean.BMaterialRelateProduct", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialRelateProductDao#findByExample(com.bean.BMaterialRelateProduct)
	 */
	@Override
	public List findByExample(BMaterialRelateProduct instance) {
		log.debug("finding BMaterialRelateProduct instance by example");
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
	 * @see com.bean.BMaterialRelateProductDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BMaterialRelateProduct instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BMaterialRelateProduct as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialRelateProductDao#findByMemo(java.lang.Object)
	 */
	@Override
	public List findByMemo(Object memo) {
		return findByProperty(MEMO, memo);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialRelateProductDao#findByRelateStatus(java.lang.Object)
	 */
	@Override
	public List findByRelateStatus(Object relateStatus) {
		return findByProperty(RELATE_STATUS, relateStatus);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialRelateProductDao#findByProductCode(java.lang.Object)
	 */
	@Override
	public List findByProductCode(Object productCode) {
		return findByProperty(PRODUCT_CODE, productCode);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialRelateProductDao#findAll()
	 */
	@Override
	public List<BMaterialRelateProduct> findAll(Integer mtlId) {
		List<BMaterialRelateProduct> bmRelateProducts = new ArrayList<BMaterialRelateProduct>();
		Session session = getSession();  
		SQLQuery query=null;
		String sql="select * from b_Material_Relate_Product rp " +
				"inner join b_Material m on m.mtl_id=rp.mtl_id where rp.mtl_id = ?";
		query=session.createSQLQuery(sql);
		query.setInteger(0, mtlId);
		query.addEntity(BMaterialRelateProduct.class);
		query.addEntity(BMaterial.class);
		List<Object[]> resultSet = query.list();
		for (Object[] r : resultSet) {
			BMaterialRelateProduct bmrp = (BMaterialRelateProduct)r[0];
			BMaterial material = (BMaterial)r[1];
			bmrp.setBMaterial(material);
			bmRelateProducts.add(bmrp);
		}
		return bmRelateProducts;
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialRelateProductDao#merge(com.bean.BMaterialRelateProduct)
	 */
	@Override
	public BMaterialRelateProduct merge(BMaterialRelateProduct detachedInstance) {
		log.debug("merging BMaterialRelateProduct instance");
		try {
			BMaterialRelateProduct result = (BMaterialRelateProduct) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BMaterialRelateProduct instance) {
		log.debug("attaching dirty BMaterialRelateProduct instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BMaterialRelateProduct instance) {
		log.debug("attaching clean BMaterialRelateProduct instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BMaterialRelateProductDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (BMaterialRelateProductDao) ctx
				.getBean("bmaterialRelateProductDao");
	}
}