package com.daoImpl;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.BMaterialPropType;
import com.dao.BMaterialPropTypeDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * BMaterialPropType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.BMaterialPropType
 * @author MyEclipse Persistence Tools
 */
public class BMaterialPropTypeDaoImpl extends HibernateDaoSupport implements BMaterialPropTypeDao {
	private static final Logger log = LoggerFactory
			.getLogger(BMaterialPropTypeDaoImpl.class);
	// property constants
	public static final String MPT_NAME = "mptName";
	public static final String MTL_TYPE_ID = "mtlTypeId";
	public static final String SYS_USER = "sysUser";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialPropTypeDao#save(com.bean.BMaterialPropType)
	 */
	@Override
	public void save(BMaterialPropType transientInstance) {
		log.debug("saving BMaterialPropType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialPropTypeDao#delete(com.bean.BMaterialPropType)
	 */
	@Override
	public void delete(BMaterialPropType persistentInstance) {
		log.debug("deleting BMaterialPropType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialPropTypeDao#findById(java.lang.Integer)
	 */
	@Override
	public BMaterialPropType findById(java.lang.Integer id) {
		log.debug("getting BMaterialPropType instance with id: " + id);
		try {
			BMaterialPropType instance = (BMaterialPropType) getHibernateTemplate()
					.get("com.bean.BMaterialPropType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialPropTypeDao#findByExample(com.bean.BMaterialPropType)
	 */
	@Override
	public List findByExample(BMaterialPropType instance) {
		log.debug("finding BMaterialPropType instance by example");
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
	 * @see com.bean.BMaterialPropTypeDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BMaterialPropType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BMaterialPropType as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialPropTypeDao#findByMptName(java.lang.Object)
	 */
	@Override
	public List findByMptName(Object mptName) {
		return findByProperty(MPT_NAME, mptName);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialPropTypeDao#findByMtlTypeId(java.lang.Object)
	 */
	@Override
	public List findByMtlTypeId(Object mtlTypeId) {
		return findByProperty(MTL_TYPE_ID, mtlTypeId);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialPropTypeDao#findBySysUser(java.lang.Object)
	 */
	@Override
	public List findBySysUser(Object sysUser) {
		return findByProperty(SYS_USER, sysUser);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialPropTypeDao#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all BMaterialPropType instances");
		try {
			String queryString = "from BMaterialPropType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialPropTypeDao#merge(com.bean.BMaterialPropType)
	 */
	@Override
	public BMaterialPropType merge(BMaterialPropType detachedInstance) {
		log.debug("merging BMaterialPropType instance");
		try {
			BMaterialPropType result = (BMaterialPropType) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BMaterialPropType instance) {
		log.debug("attaching dirty BMaterialPropType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BMaterialPropType instance) {
		log.debug("attaching clean BMaterialPropType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BMaterialPropTypeDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (BMaterialPropTypeDao) ctx.getBean("bmaterialPropTypeDao");
	}
}