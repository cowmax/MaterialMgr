package com.daoImpl;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.BMaterialProp;
import com.dao.BMaterialPropDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * BMaterialProp entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.BMaterialProp
 * @author MyEclipse Persistence Tools
 */
public class BMaterialPropDaoImpl extends HibernateDaoSupport implements BMaterialPropDao {
	private static final Logger log = LoggerFactory
			.getLogger(BMaterialPropDaoImpl.class);
	// property constants
	public static final String PROP_VALUE = "propValue";
	public static final String SYS_USER = "sysUser";
	public static final String MTL_CODE = "mtlCode";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialPropDao#save(com.bean.BMaterialProp)
	 */
	@Override
	public void save(BMaterialProp transientInstance) {
		log.debug("saving BMaterialProp instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialPropDao#delete(com.bean.BMaterialProp)
	 */
	@Override
	public void delete(BMaterialProp persistentInstance) {
		log.debug("deleting BMaterialProp instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialPropDao#findById(java.lang.Integer)
	 */
	@Override
	public BMaterialProp findById(java.lang.Integer id) {
		log.debug("getting BMaterialProp instance with id: " + id);
		try {
			BMaterialProp instance = (BMaterialProp) getHibernateTemplate()
					.get("com.bean.BMaterialProp", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialPropDao#findByExample(com.bean.BMaterialProp)
	 */
	@Override
	public List findByExample(BMaterialProp instance) {
		log.debug("finding BMaterialProp instance by example");
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
	 * @see com.bean.BMaterialPropDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BMaterialProp instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BMaterialProp as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialPropDao#findByPropValue(java.lang.Object)
	 */
	@Override
	public List findByPropValue(Object propValue) {
		return findByProperty(PROP_VALUE, propValue);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialPropDao#findBySysUser(java.lang.Object)
	 */
	@Override
	public List findBySysUser(Object sysUser) {
		return findByProperty(SYS_USER, sysUser);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialPropDao#findByMtlCode(java.lang.Object)
	 */
	@Override
	public List findByMtlCode(Object mtlCode) {
		return findByProperty(MTL_CODE, mtlCode);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialPropDao#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all BMaterialProp instances");
		try {
			String queryString = "from BMaterialProp";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialPropDao#merge(com.bean.BMaterialProp)
	 */
	@Override
	public BMaterialProp merge(BMaterialProp detachedInstance) {
		log.debug("merging BMaterialProp instance");
		try {
			BMaterialProp result = (BMaterialProp) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BMaterialProp instance) {
		log.debug("attaching dirty BMaterialProp instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BMaterialProp instance) {
		log.debug("attaching clean BMaterialProp instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BMaterialPropDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (BMaterialPropDao) ctx.getBean("bmaterialPropDao");
	}
}