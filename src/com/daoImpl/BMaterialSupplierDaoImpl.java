package com.daoImpl;

import java.sql.ResultSet;
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
import com.bean.BMaterialImage;
import com.bean.BMaterialRelateProduct;
import com.bean.BMaterialSupplier;
import com.bean.BMaterialType;
import com.bean.ExtraBMaterial;
import com.bean.Suin;
import com.dao.BMaterialSupplierDao;
import com.opensymphony.xwork2.Result;

/**
 * A data access object (DAO) providing persistence and search support for
 * BMaterialSupplier entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.BMaterialSupplier
 * @author MyEclipse Persistence Tools
 */
public class BMaterialSupplierDaoImpl extends HibernateDaoSupport implements BMaterialSupplierDao {
	private static final Logger log = LoggerFactory
			.getLogger(BMaterialSupplierDaoImpl.class);
	// property constants
	public static final String MTS_CODE = "mtsCode";
	public static final String MTS_NAME = "mtsName";
	public static final String MTS_COLOR_COUNT = "mtsColorCount";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialSupplierDao#save(com.bean.BMaterialSupplier)
	 */
	@Override
	public void save(BMaterialSupplier transientInstance) {
		Session session = getSession();  
		SQLQuery query=null;
		
		String sql="insert into b_Material_Supplier (mtl_id,mts_code,mts_name,mts_color_count,suid) values (?,?,?,?,?)";
		query=session.createSQLQuery(sql);
		query.setInteger(0, transientInstance.getBMaterial().getMtlId());
		query.setString(1, transientInstance.getMtsCode());
		query.setString(2, transientInstance.getMtsName());
		Integer colorCount = transientInstance.getMtsColorCount();
		if(colorCount!=null){
			query.setInteger(3,colorCount);
		}else{
			query.setInteger(3,0);
		}
		query.setString(4, transientInstance.getSuin().getSuid());
		
		query.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialSupplierDao#delete(com.bean.BMaterialSupplier)
	 */
	@Override
	public void delete(BMaterialSupplier persistentInstance) {
		log.debug("deleting BMaterialSupplier instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialSupplierDao#findById(java.lang.Integer)
	 */
	@Override
	public BMaterialSupplier findById(java.lang.Integer id) {
		Session session = getSession();  
		SQLQuery query=null;
		String sql="select * from b_Material_Supplier ms " +
				"inner join Suin s on s.suid=ms.suid " +
				"inner join b_Material m on ms.mtl_id = m.mtl_id where ms.mts_id=?";
		query=session.createSQLQuery(sql);
		query.setInteger(0, id);
		query.addEntity(BMaterialSupplier.class);
		query.addEntity(Suin.class);
		query.addEntity(BMaterial.class);
		List<Object[]> resultSet = query.list();
		BMaterialSupplier supplier = null;
		for (Object[] r : resultSet) {
			supplier = (BMaterialSupplier)r[0];
			Suin suin = (Suin)r[1];
			BMaterial material = (BMaterial)r[2];
			supplier.setSuin(suin);
			supplier.setBMaterial(material);
		}
		return supplier;
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialSupplierDao#findByExample(com.bean.BMaterialSupplier)
	 */
	@Override
	public List findByExample(BMaterialSupplier instance) {
		log.debug("finding BMaterialSupplier instance by example");
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
	 * @see com.bean.BMaterialSupplierDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BMaterialSupplier instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BMaterialSupplier as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialSupplierDao#findByMtsCode(java.lang.Object)
	 */
	@Override
	public List findByMtsCode(Object mtsCode) {
		return findByProperty(MTS_CODE, mtsCode);
	}

	public List findByMtsName(Object mtsName) {
		return findByProperty(MTS_NAME, mtsName);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialSupplierDao#findByMtsColorCount(java.lang.Object)
	 */
	@Override
	public List findByMtsColorCount(Object mtsColorCount) {
		return findByProperty(MTS_COLOR_COUNT, mtsColorCount);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialSupplierDao#findAll()
	 */
	@Override
	public List<BMaterialSupplier> findAll(Integer mtlId) {
		List<BMaterialSupplier> list = new ArrayList<BMaterialSupplier>();
		Session session = getSession();  
		SQLQuery query=null;
		String sql="select * from b_Material_Supplier ms inner join b_Material m on ms.mtl_id = m.mtl_id where ms.mtl_id = :mtlId";
		query=session.createSQLQuery(sql);
		query.setInteger("mtlId",mtlId);
		query.addEntity(BMaterialSupplier.class);
		query.addEntity(BMaterial.class);
		List<Object[]> resultSet =query.list();
		for (Object[] r : resultSet) {
			BMaterialSupplier supplier = (BMaterialSupplier)r[0];
			BMaterial material = (BMaterial)r[1];
			supplier.setBMaterial(material);
			list.add(supplier);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialSupplierDao#merge(com.bean.BMaterialSupplier)
	 */
	@Override
	public BMaterialSupplier merge(BMaterialSupplier detachedInstance) {
		log.debug("merging BMaterialSupplier instance");
		try {
			BMaterialSupplier result = (BMaterialSupplier) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BMaterialSupplier instance) {
		log.debug("attaching dirty BMaterialSupplier instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BMaterialSupplier instance) {
		log.debug("attaching clean BMaterialSupplier instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BMaterialSupplierDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (BMaterialSupplierDao) ctx.getBean("bmaterialSupplierDao");
	}
}