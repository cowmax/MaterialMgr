package com.daoImpl;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.BMaterialTestDetail;
import com.dao.BMaterialTestDetailDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * BMaterialTestDetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.BMaterialTestDetail
 * @author MyEclipse Persistence Tools
 */
public class BMaterialTestDetailDaoImpl extends HibernateDaoSupport implements BMaterialTestDetailDao {
	private static final Logger log = LoggerFactory
			.getLogger(BMaterialTestDetailDaoImpl.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTestDetailDao#save(com.bean.BMaterialTestDetail)
	 */
	@Override
	public void save(BMaterialTestDetail transientInstance) {
		log.debug("saving BMaterialTestDetail instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTestDetailDao#delete(com.bean.BMaterialTestDetail)
	 */
	@Override
	public void delete(BMaterialTestDetail persistentInstance) {
		log.debug("deleting BMaterialTestDetail instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTestDetailDao#findById(java.lang.Integer)
	 */
	@Override
	public BMaterialTestDetail findById(java.lang.Integer id) {
		log.debug("getting BMaterialTestDetail instance with id: " + id);
		try {
			BMaterialTestDetail instance = (BMaterialTestDetail) getHibernateTemplate()
					.get("com.bean.BMaterialTestDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTestDetailDao#findByExample(com.bean.BMaterialTestDetail)
	 */
	@Override
	public List findByExample(BMaterialTestDetail instance) {
		log.debug("finding BMaterialTestDetail instance by example");
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
	 * @see com.bean.BMaterialTestDetailDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BMaterialTestDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BMaterialTestDetail as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTestDetailDao#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all BMaterialTestDetail instances");
		try {
			String queryString = "from BMaterialTestDetail";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTestDetailDao#merge(com.bean.BMaterialTestDetail)
	 */
	@Override
	public BMaterialTestDetail merge(BMaterialTestDetail detachedInstance) {
		log.debug("merging BMaterialTestDetail instance");
		try {
			BMaterialTestDetail result = (BMaterialTestDetail) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BMaterialTestDetail instance) {
		log.debug("attaching dirty BMaterialTestDetail instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BMaterialTestDetail instance) {
		log.debug("attaching clean BMaterialTestDetail instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BMaterialTestDetailDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (BMaterialTestDetailDao) ctx.getBean("bmaterialTestDetailDao");
	}
}