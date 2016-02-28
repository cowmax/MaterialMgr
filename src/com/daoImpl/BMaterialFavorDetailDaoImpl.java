package com.daoImpl;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.BMaterialFavorDetail;
import com.dao.BMaterialFavorDetailDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * BMaterialFavorDetail entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.BMaterialFavorDetail
 * @author MyEclipse Persistence Tools
 */
public class BMaterialFavorDetailDaoImpl extends HibernateDaoSupport implements BMaterialFavorDetailDao {
	private static final Logger log = LoggerFactory
			.getLogger(BMaterialFavorDetailDaoImpl.class);
	// property constants
	public static final String DTL_MEMO = "dtlMemo";
	public static final String PLACE_TYPE = "placeType";
	public static final String STATUS = "status";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialFavorDetailDao#save(com.bean.BMaterialFavorDetail)
	 */
	@Override
	public void save(BMaterialFavorDetail transientInstance) {
		log.debug("saving BMaterialFavorDetail instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialFavorDetailDao#delete(com.bean.BMaterialFavorDetail)
	 */
	@Override
	public void delete(BMaterialFavorDetail persistentInstance) {
		log.debug("deleting BMaterialFavorDetail instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialFavorDetailDao#findById(java.lang.Integer)
	 */
	@Override
	public BMaterialFavorDetail findById(java.lang.Integer id) {
		log.debug("getting BMaterialFavorDetail instance with id: " + id);
		try {
			BMaterialFavorDetail instance = (BMaterialFavorDetail) getHibernateTemplate()
					.get("com.bean.BMaterialFavorDetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialFavorDetailDao#findByExample(com.bean.BMaterialFavorDetail)
	 */
	@Override
	public List findByExample(BMaterialFavorDetail instance) {
		log.debug("finding BMaterialFavorDetail instance by example");
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
	 * @see com.bean.BMaterialFavorDetailDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BMaterialFavorDetail instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BMaterialFavorDetail as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialFavorDetailDao#findByDtlMemo(java.lang.Object)
	 */
	@Override
	public List findByDtlMemo(Object dtlMemo) {
		return findByProperty(DTL_MEMO, dtlMemo);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialFavorDetailDao#findByPlaceType(java.lang.Object)
	 */
	@Override
	public List findByPlaceType(Object placeType) {
		return findByProperty(PLACE_TYPE, placeType);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialFavorDetailDao#findByStatus(java.lang.Object)
	 */
	@Override
	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialFavorDetailDao#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all BMaterialFavorDetail instances");
		try {
			String queryString = "from BMaterialFavorDetail";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialFavorDetailDao#merge(com.bean.BMaterialFavorDetail)
	 */
	@Override
	public BMaterialFavorDetail merge(BMaterialFavorDetail detachedInstance) {
		log.debug("merging BMaterialFavorDetail instance");
		try {
			BMaterialFavorDetail result = (BMaterialFavorDetail) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BMaterialFavorDetail instance) {
		log.debug("attaching dirty BMaterialFavorDetail instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BMaterialFavorDetail instance) {
		log.debug("attaching clean BMaterialFavorDetail instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BMaterialFavorDetailDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (BMaterialFavorDetailDao) ctx.getBean("bmaterialFavorDetailDao");
	}
}