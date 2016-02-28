package com.daoImpl;

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
import com.bean.BMaterialIngredient;
import com.dao.BMaterialIngredientDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * BMaterialIngredient entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.BMaterialIngredient
 * @author MyEclipse Persistence Tools
 */
public class BMaterialIngredientDaoImpl extends HibernateDaoSupport implements BMaterialIngredientDao {
	private static final Logger log = LoggerFactory
			.getLogger(BMaterialIngredientDaoImpl.class);
	// property constants
	public static final String INGREDIENT_NAME = "ingredientName";
	public static final String PRECENT = "precent";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialIngredientDao#save(com.bean.BMaterialIngredient)
	 */
	@Override
	public void save(BMaterialIngredient transientInstance) {
		log.debug("saving BMaterialIngredient instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialIngredientDao#delete(com.bean.BMaterialIngredient)
	 */
	@Override
	public void delete(BMaterialIngredient persistentInstance) {
		log.debug("deleting BMaterialIngredient instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialIngredientDao#findById(java.lang.Integer)
	 */
	@Override
	public BMaterialIngredient findById(java.lang.Integer id) {
		log.debug("getting BMaterialIngredient instance with id: " + id);
		try {
			BMaterialIngredient instance = (BMaterialIngredient) getHibernateTemplate()
					.get("com.bean.BMaterialIngredient", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialIngredientDao#findByExample(com.bean.BMaterialIngredient)
	 */
	@Override
	public List findByExample(BMaterialIngredient instance) {
		log.debug("finding BMaterialIngredient instance by example");
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
	 * @see com.bean.BMaterialIngredientDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BMaterialIngredient instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BMaterialIngredient as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialIngredientDao#findByIngredientName(java.lang.Object)
	 */
	@Override
	public List findByIngredientName(Object ingredientName) {
		return findByProperty(INGREDIENT_NAME, ingredientName);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialIngredientDao#findByPrecent(java.lang.Object)
	 */
	@Override
	public List findByPrecent(Object precent) {
		return findByProperty(PRECENT, precent);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialIngredientDao#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all BMaterialIngredient instances");
		try {
			String queryString = "from BMaterialIngredient";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialIngredientDao#merge(com.bean.BMaterialIngredient)
	 */
	@Override
	public BMaterialIngredient merge(BMaterialIngredient detachedInstance) {
		log.debug("merging BMaterialIngredient instance");
		try {
			BMaterialIngredient result = (BMaterialIngredient) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BMaterialIngredient instance) {
		log.debug("attaching dirty BMaterialIngredient instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BMaterialIngredient instance) {
		log.debug("attaching clean BMaterialIngredient instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BMaterialIngredientDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (BMaterialIngredientDao) ctx.getBean("ingredientDao");
	}
	
	/**
	 * 查询所有面料
	 */
	
	public List<BMaterialIngredient> findAllBMIngred(Integer mtlId){
		
		String sql="SELECT * FROM b_Material_Ingredient where mtl_id =:mtlId  ";
		
		Session session = getSession();
		SQLQuery query = session.createSQLQuery(sql);
		
		query.setInteger("mtlId", mtlId);
		query.addEntity(BMaterialIngredient.class);
		List list=query.list(); 
		
		return list;
	}

	/**
	 * 删除成分
	 */
	public void delBMIngred(Integer id) {
		
		String sql="delete FROM  b_Material_Ingredient where id =:id  ";
		
		Session session = getSession();
		SQLQuery query = session.createSQLQuery(sql);
		query.setInteger("id", id);
		query.executeUpdate();
		
	}

	
	
}