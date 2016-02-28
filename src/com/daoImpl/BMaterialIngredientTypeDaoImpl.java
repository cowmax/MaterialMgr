package com.daoImpl;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.BMaterialIngredientType;
import com.dao.BMaterialIngredientTypeDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * BMaterialIngredientType entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.BMaterialIngredientType
 * @author MyEclipse Persistence Tools
 */
public class BMaterialIngredientTypeDaoImpl extends HibernateDaoSupport implements BMaterialIngredientTypeDao {
	private static final Logger log = LoggerFactory
			.getLogger(BMaterialIngredientTypeDaoImpl.class);
	// property constants
	public static final String INGREDIENT_TYPE = "ingredientType";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.daoImpl.BMaterialIngredientTypeDao#save(com.bean.BMaterialIngredientType)
	 */
	@Override
	public void save(BMaterialIngredientType transientInstance) {
		log.debug("saving BMaterialIngredientType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoImpl.BMaterialIngredientTypeDao#delete(com.bean.BMaterialIngredientType)
	 */
	@Override
	public void delete(BMaterialIngredientType persistentInstance) {
		log.debug("deleting BMaterialIngredientType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoImpl.BMaterialIngredientTypeDao#findById(java.lang.Integer)
	 */
	@Override
	public BMaterialIngredientType findById(java.lang.Integer id) {
		log.debug("getting BMaterialIngredientType instance with id: " + id);
		try {
			BMaterialIngredientType instance = (BMaterialIngredientType) getHibernateTemplate()
					.get("com.bean.BMaterialIngredientType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoImpl.BMaterialIngredientTypeDao#findByExample(com.bean.BMaterialIngredientType)
	 */
	@Override
	public List findByExample(BMaterialIngredientType instance) {
		log.debug("finding BMaterialIngredientType instance by example");
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
	 * @see com.daoImpl.BMaterialIngredientTypeDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BMaterialIngredientType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BMaterialIngredientType as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoImpl.BMaterialIngredientTypeDao#findByIngredientType(java.lang.Object)
	 */
	@Override
	public List findByIngredientType(Object ingredientType) {
		return findByProperty(INGREDIENT_TYPE, ingredientType);
	}

	/* (non-Javadoc)
	 * @see com.daoImpl.BMaterialIngredientTypeDao#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all BMaterialIngredientType instances");
		try {
			String queryString = "from BMaterialIngredientType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.daoImpl.BMaterialIngredientTypeDao#merge(com.bean.BMaterialIngredientType)
	 */
	@Override
	public BMaterialIngredientType merge(
			BMaterialIngredientType detachedInstance) {
		log.debug("merging BMaterialIngredientType instance");
		try {
			BMaterialIngredientType result = (BMaterialIngredientType) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BMaterialIngredientType instance) {
		log.debug("attaching dirty BMaterialIngredientType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BMaterialIngredientType instance) {
		log.debug("attaching clean BMaterialIngredientType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BMaterialIngredientTypeDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (BMaterialIngredientTypeDao) ctx
				.getBean("bmaterialIngredientTypeDao");
	}
}