package com.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.BMaterialImage;
import com.dao.BMaterialImageDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * BMaterialImage entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.BMaterialImage
 * @author MyEclipse Persistence Tools
 */
public class BMaterialImageDaoImpl extends HibernateDaoSupport implements BMaterialImageDao {
	private static final Logger log = LoggerFactory
			.getLogger(BMaterialImageDaoImpl.class);
	// property constants
	public static final String IMG_TITLE = "imgTitle";
	public static final String IMG_DESCIPTION = "imgDesciption";
	public static final String IMG_URL = "imgUrl";
	public static final String IMG_SIZE_W = "imgSizeW";
	public static final String IMG_SIZE_H = "imgSizeH";
	public static final String IMG_COLOR = "imgColor";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialImageDao#save(com.bean.BMaterialImage)
	 */
	@Override
	public void save(BMaterialImage transientInstance) {
		log.debug("saving BMaterialImage instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialImageDao#delete(com.bean.BMaterialImage)
	 */
	@Override
	public void delete(BMaterialImage persistentInstance) {
		log.debug("deleting BMaterialImage instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialImageDao#findById(java.lang.Integer)
	 */
	@Override
	public BMaterialImage findById(java.lang.Integer id) {
		log.debug("getting BMaterialImage instance with id: " + id);
		try {
			BMaterialImage instance = (BMaterialImage) getHibernateTemplate()
					.get("com.bean.BMaterialImage", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialImageDao#findByExample(com.bean.BMaterialImage)
	 */
	@Override
	public List findByExample(BMaterialImage instance) {
		log.debug("finding BMaterialImage instance by example");
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
	 * @see com.bean.BMaterialImageDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BMaterialImage instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BMaterialImage as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialImageDao#findByImgTitle(java.lang.Object)
	 */
	@Override
	public List findByImgTitle(Object imgTitle) {
		return findByProperty(IMG_TITLE, imgTitle);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialImageDao#findByImgDesciption(java.lang.Object)
	 */
	@Override
	public List findByImgDesciption(Object imgDesciption) {
		return findByProperty(IMG_DESCIPTION, imgDesciption);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialImageDao#findByImgUrl(java.lang.Object)
	 */
	@Override
	public List findByImgUrl(Object imgUrl) {
		return findByProperty(IMG_URL, imgUrl);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialImageDao#findByImgSizeW(java.lang.Object)
	 */
	@Override
	public List findByImgSizeW(Object imgSizeW) {
		return findByProperty(IMG_SIZE_W, imgSizeW);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialImageDao#findByImgSizeH(java.lang.Object)
	 */
	@Override
	public List findByImgSizeH(Object imgSizeH) {
		return findByProperty(IMG_SIZE_H, imgSizeH);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialImageDao#findByImgColor(java.lang.Object)
	 */
	@Override
	public List findByImgColor(Object imgColor) {
		return findByProperty(IMG_COLOR, imgColor);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialImageDao#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all BMaterialImage instances");
		try {
			String queryString = "from BMaterialImage";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialImageDao#merge(com.bean.BMaterialImage)
	 */
	@Override
	public BMaterialImage merge(BMaterialImage detachedInstance) {
		log.debug("merging BMaterialImage instance");
		try {
			BMaterialImage result = (BMaterialImage) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BMaterialImage instance) {
		log.debug("attaching dirty BMaterialImage instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BMaterialImage instance) {
		log.debug("attaching clean BMaterialImage instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BMaterialImageDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (BMaterialImageDao) ctx.getBean("bmaterialImageDao");
	}
	
	/**
	 * 查找所有的照片颜色daoImpl
	 */
	 
	public List  getAllImageColor(){
		String sql="SELECT distinct  img_color FROM b_Material_Image ";
		Session session = getSession();
		Query query = session.createSQLQuery(sql);
		List list=query.list(); 
		return list;
	}
	
	/**
	 * 查找添加的照片daoImpl
	 */
	public List<BMaterialImage> getallPhoto(Integer mtlId){
		String sql="SELECT * FROM b_Material_Image where mtl_id =:mtlId ";
		Session session = getSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.setInteger("mtlId", mtlId);
		query.addEntity(BMaterialImage.class);
		
		List<BMaterialImage> list=query.list(); 

		return list;
	}
}