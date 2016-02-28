package com.daoImpl;

import java.util.List;
import java.util.Set;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.BMaterialImageType;
import com.dao.BMaterialImageTypeDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * BMaterialImageType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.BMaterialImageType
 * @author MyEclipse Persistence Tools
 */
public class BMaterialImageTypeDaoImpl extends HibernateDaoSupport implements BMaterialImageTypeDao {
	private static final Logger log = LoggerFactory
			.getLogger(BMaterialImageTypeDaoImpl.class);
	// property constants
	public static final String IMG_TYPE_NAME = "imgTypeName";
	public static final String MEMO = "memo";


	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialImageTypeDao#save(com.bean.BMaterialImageType)
	 */
	@Override
	public void save(BMaterialImageType transientInstance) {
		log.debug("saving BMaterialImageType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialImageTypeDao#delete(com.bean.BMaterialImageType)
	 */
	@Override
	public void delete(BMaterialImageType persistentInstance) {
		log.debug("deleting BMaterialImageType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialImageTypeDao#findById(java.lang.String)
	 */
	@Override
	public BMaterialImageType findById(java.lang.String id) {
		log.debug("getting BMaterialImageType instance with id: " + id);
		try {
			BMaterialImageType instance = (BMaterialImageType) getHibernateTemplate()
					.get("com.bean.BMaterialImageType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialImageTypeDao#findByExample(com.bean.BMaterialImageType)
	 */
	@Override
	public List findByExample(BMaterialImageType instance) {
		log.debug("finding BMaterialImageType instance by example");
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
	 * @see com.bean.BMaterialImageTypeDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BMaterialImageType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BMaterialImageType as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialImageTypeDao#findByImgTypeName(java.lang.Object)
	 */
	@Override
	public List findByImgTypeName(Object imgTypeName) {
		return findByProperty(IMG_TYPE_NAME, imgTypeName);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialImageTypeDao#findByMemo(java.lang.Object)
	 */
	@Override
	public List findByMemo(Object memo) {
		return findByProperty(MEMO, memo);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialImageTypeDao#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all BMaterialImageType instances");
		try {
			String queryString = "from BMaterialImageType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialImageTypeDao#merge(com.bean.BMaterialImageType)
	 */
	@Override
	public BMaterialImageType merge(BMaterialImageType detachedInstance) {
		log.debug("merging BMaterialImageType instance");
		try {
			BMaterialImageType result = (BMaterialImageType) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BMaterialImageType instance) {
		log.debug("attaching dirty BMaterialImageType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BMaterialImageType instance) {
		log.debug("attaching clean BMaterialImageType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BMaterialImageTypeDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (BMaterialImageTypeDao) ctx.getBean("bmaterialImageTypeDao");
	}
	
	/**
	 * 获取所有照片的类型
	 */
	public List  getAllImageType(){
		String sql="SELECT img_type FROM b_Material_Image_Type ";
		Session session = getSession();
		Query query = session.createSQLQuery(sql);
		List list=query.list(); 
		return list;
	}


}