package com.daoImpl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.BMaterialFavor;
import com.dao.BMaterialFavorDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * BMaterialFavor entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.BMaterialFavor
 * @author MyEclipse Persistence Tools
 */
public class BMaterialFavorDaoImpl extends HibernateDaoSupport implements BMaterialFavorDao {
	private static final Logger log = LoggerFactory
			.getLogger(BMaterialFavorDaoImpl.class);
	// property constants
	public static final String MEMO = "memo";
	public static final String SYS_USER = "sysUser";
	public static final String RATE = "rate";
	public static final String PRODUCT_CODE = "productCode";
	public static final String STATUS = "status";
	public static final String FVR_DESC = "fvrDesc";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialFavorDao#save(com.bean.BMaterialFavor)
	 */
	@Override
	public void save(BMaterialFavor transientInstance) {
		log.debug("saving BMaterialFavor instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialFavorDao#delete(com.bean.BMaterialFavor)
	 */
	@Override
	public void delete(BMaterialFavor persistentInstance) {
		log.debug("deleting BMaterialFavor instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialFavorDao#findById(java.lang.Integer)
	 */
	@Override
	public BMaterialFavor findById(java.lang.Integer id) {
		log.debug("getting BMaterialFavor instance with id: " + id);
		try {
			BMaterialFavor instance = (BMaterialFavor) getHibernateTemplate()
					.get("com.bean.BMaterialFavor", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialFavorDao#findByExample(com.bean.BMaterialFavor)
	 */
	@Override
	public List findByExample(BMaterialFavor instance) {
		log.debug("finding BMaterialFavor instance by example");
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
	 * @see com.bean.BMaterialFavorDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BMaterialFavor instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BMaterialFavor as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialFavorDao#findByMemo(java.lang.Object)
	 */
	@Override
	public List findByMemo(Object memo) {
		return findByProperty(MEMO, memo);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialFavorDao#findBySysUser(java.lang.Object)
	 */
	@Override
	public List findBySysUser(Object sysUser) {
		return findByProperty(SYS_USER, sysUser);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialFavorDao#findByRate(java.lang.Object)
	 */
	@Override
	public List findByRate(Object rate) {
		return findByProperty(RATE, rate);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialFavorDao#findByProductCode(java.lang.Object)
	 */
	@Override
	public List findByProductCode(Object productCode) {
		return findByProperty(PRODUCT_CODE, productCode);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialFavorDao#findByStatus(java.lang.Object)
	 */
	@Override
	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialFavorDao#findByFvrDesc(java.lang.Object)
	 */
	@Override
	public List findByFvrDesc(Object fvrDesc) {
		return findByProperty(FVR_DESC, fvrDesc);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialFavorDao#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all BMaterialFavor instances");
		try {
			String queryString = "from BMaterialFavor";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialFavorDao#merge(com.bean.BMaterialFavor)
	 */
	@Override
	public BMaterialFavor merge(BMaterialFavor detachedInstance) {
		log.debug("merging BMaterialFavor instance");
		try {
			BMaterialFavor result = (BMaterialFavor) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BMaterialFavor instance) {
		log.debug("attaching dirty BMaterialFavor instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BMaterialFavor instance) {
		log.debug("attaching clean BMaterialFavor instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BMaterialFavorDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (BMaterialFavorDao) ctx.getBean("bmaterialFavorDao");
	}
}