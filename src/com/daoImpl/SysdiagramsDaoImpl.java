package com.daoImpl;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.Sysdiagrams;
import com.dao.SysdiagramsDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * Sysdiagrams entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.Sysdiagrams
 * @author MyEclipse Persistence Tools
 */
public class SysdiagramsDaoImpl extends HibernateDaoSupport implements SysdiagramsDao {
	private static final Logger log = LoggerFactory
			.getLogger(SysdiagramsDaoImpl.class);
	// property constants
	public static final String NAME = "name";
	public static final String PRINCIPAL_ID = "principalId";
	public static final String VERSION = "version";
	public static final String DEFINITION = "definition";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.bean.SysdiagramsDao#save(com.bean.Sysdiagrams)
	 */
	@Override
	public void save(Sysdiagrams transientInstance) {
		log.debug("saving Sysdiagrams instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.SysdiagramsDao#delete(com.bean.Sysdiagrams)
	 */
	@Override
	public void delete(Sysdiagrams persistentInstance) {
		log.debug("deleting Sysdiagrams instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.SysdiagramsDao#findById(java.lang.Integer)
	 */
	@Override
	public Sysdiagrams findById(java.lang.Integer id) {
		log.debug("getting Sysdiagrams instance with id: " + id);
		try {
			Sysdiagrams instance = (Sysdiagrams) getHibernateTemplate().get(
					"com.bean.Sysdiagrams", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.SysdiagramsDao#findByExample(com.bean.Sysdiagrams)
	 */
	@Override
	public List findByExample(Sysdiagrams instance) {
		log.debug("finding Sysdiagrams instance by example");
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
	 * @see com.bean.SysdiagramsDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Sysdiagrams instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Sysdiagrams as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.SysdiagramsDao#findByName(java.lang.Object)
	 */
	@Override
	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	/* (non-Javadoc)
	 * @see com.bean.SysdiagramsDao#findByPrincipalId(java.lang.Object)
	 */
	@Override
	public List findByPrincipalId(Object principalId) {
		return findByProperty(PRINCIPAL_ID, principalId);
	}

	/* (non-Javadoc)
	 * @see com.bean.SysdiagramsDao#findByVersion(java.lang.Object)
	 */
	@Override
	public List findByVersion(Object version) {
		return findByProperty(VERSION, version);
	}

	/* (non-Javadoc)
	 * @see com.bean.SysdiagramsDao#findByDefinition(java.lang.Object)
	 */
	@Override
	public List findByDefinition(Object definition) {
		return findByProperty(DEFINITION, definition);
	}

	/* (non-Javadoc)
	 * @see com.bean.SysdiagramsDao#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all Sysdiagrams instances");
		try {
			String queryString = "from Sysdiagrams";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.SysdiagramsDao#merge(com.bean.Sysdiagrams)
	 */
	@Override
	public Sysdiagrams merge(Sysdiagrams detachedInstance) {
		log.debug("merging Sysdiagrams instance");
		try {
			Sysdiagrams result = (Sysdiagrams) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Sysdiagrams instance) {
		log.debug("attaching dirty Sysdiagrams instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Sysdiagrams instance) {
		log.debug("attaching clean Sysdiagrams instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SysdiagramsDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (SysdiagramsDao) ctx.getBean("sysdiagramsDao");
	}
}