package com.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.BMaterial;
import com.bean.BMaterialRelateProduct;
import com.bean.Product;
import com.dao.ProductDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * Product entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.Product
 * @author MyEclipse Persistence Tools
 */
public class ProductDaoImpl extends HibernateDaoSupport implements ProductDao {
	private static final Logger log = LoggerFactory.getLogger(ProductDaoImpl.class);
	// property constants
	public static final String PRODUCT_CODE = "productCode";
	public static final String STID = "stid";
	public static final String STNO = "stno";
	public static final String INTY = "inty";
	public static final String TYNA = "tyna";
	public static final String SYEA = "syea";
	public static final String COLO_ID = "coloId";
	public static final String COLO = "colo";
	public static final String CONA = "cona";
	public static final String SZID = "szid";
	public static final String SZCO = "szco";
	public static final String CPCO = "cpco";
	public static final String STS = "sts";
	public static final String SOURCE_BIID = "sourceBiid";
	public static final String PRODUCT_DESC = "productDesc";
	public static final String BRAD = "brad";
	public static final String SPNO = "spno";
	public static final String REMA = "rema";
	public static final String EDUS = "edus";
	public static final String EDNA = "edna";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#save(com.bean.Product)
	 */
	@Override
	public void save(Product transientInstance) {
		log.debug("saving Product instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#delete(com.bean.Product)
	 */
	@Override
	public void delete(Product persistentInstance) {
		log.debug("deleting Product instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findById(java.lang.Integer)
	 */
	@Override
	public Product findById(java.lang.Integer id) {
		log.debug("getting Product instance with id: " + id);
		try {
			Product instance = (Product) getHibernateTemplate().get(
					"com.bean.Product", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findByExample(com.bean.Product)
	 */
	@Override
	public List findByExample(Product instance) {
		log.debug("finding Product instance by example");
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
	 * @see com.bean.ProductDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Product instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Product as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findByProductCode(java.lang.Object)
	 */
	@Override
	public List findByProductCode(Object productCode) {
		return findByProperty(PRODUCT_CODE, productCode);
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findByStid(java.lang.Object)
	 */
	@Override
	public List findByStid(Object stid) {
		return findByProperty(STID, stid);
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findByStno(java.lang.Object)
	 */
	@Override
	public List findByStno(Object stno) {
		return findByProperty(STNO, stno);
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findByInty(java.lang.Object)
	 */
	@Override
	public List findByInty(Object inty) {
		return findByProperty(INTY, inty);
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findByTyna(java.lang.Object)
	 */
	@Override
	public List findByTyna(Object tyna) {
		return findByProperty(TYNA, tyna);
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findBySyea(java.lang.Object)
	 */
	@Override
	public List findBySyea(Object syea) {
		return findByProperty(SYEA, syea);
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findByColoId(java.lang.Object)
	 */
	@Override
	public List findByColoId(Object coloId) {
		return findByProperty(COLO_ID, coloId);
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findByColo(java.lang.Object)
	 */
	@Override
	public List findByColo(Object colo) {
		return findByProperty(COLO, colo);
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findByCona(java.lang.Object)
	 */
	@Override
	public List findByCona(Object cona) {
		return findByProperty(CONA, cona);
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findBySzid(java.lang.Object)
	 */
	@Override
	public List findBySzid(Object szid) {
		return findByProperty(SZID, szid);
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findBySzco(java.lang.Object)
	 */
	@Override
	public List findBySzco(Object szco) {
		return findByProperty(SZCO, szco);
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findByCpco(java.lang.Object)
	 */
	@Override
	public List findByCpco(Object cpco) {
		return findByProperty(CPCO, cpco);
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findBySts(java.lang.Object)
	 */
	@Override
	public List findBySts(Object sts) {
		return findByProperty(STS, sts);
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findBySourceBiid(java.lang.Object)
	 */
	@Override
	public List findBySourceBiid(Object sourceBiid) {
		return findByProperty(SOURCE_BIID, sourceBiid);
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findByProductDesc(java.lang.Object)
	 */
	@Override
	public List findByProductDesc(Object productDesc) {
		return findByProperty(PRODUCT_DESC, productDesc);
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findByBrad(java.lang.Object)
	 */
	@Override
	public List findByBrad(Object brad) {
		return findByProperty(BRAD, brad);
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findBySpno(java.lang.Object)
	 */
	@Override
	public List findBySpno(Object spno) {
		return findByProperty(SPNO, spno);
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findByRema(java.lang.Object)
	 */
	@Override
	public List findByRema(Object rema) {
		return findByProperty(REMA, rema);
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findByEdus(java.lang.Object)
	 */
	@Override
	public List findByEdus(Object edus) {
		return findByProperty(EDUS, edus);
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findByEdna(java.lang.Object)
	 */
	@Override
	public List findByEdna(Object edna) {
		return findByProperty(EDNA, edna);
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#findAll()
	 */
	@Override
	public List<Product> findAll(String sql) {
		List<Product> products = new ArrayList<Product>();
		Session session = getSession();  
		
		SQLQuery query=null;
		query=session.createSQLQuery(sql);
		
		query.addEntity(Product.class);
		
		products = query.list();
		return products;
	}

	/* (non-Javadoc)
	 * @see com.bean.ProductDao#merge(com.bean.Product)
	 */
	@Override
	public Product merge(Product detachedInstance) {
		log.debug("merging Product instance");
		try {
			Product result = (Product) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Product instance) {
		log.debug("attaching dirty Product instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Product instance) {
		log.debug("attaching clean Product instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProductDao getFromApplicationContext(ApplicationContext ctx) {
		return (ProductDao) ctx.getBean("productDao");
	}

	@Override
	public int getTotalCount(String sql) {
		int count = 0;
		Session session = getSession();  
		
		SQLQuery query=null;
		query=session.createSQLQuery(sql);
		
		query.addEntity(Product.class);
		
		count = query.list().size();
		return count;
	}
}