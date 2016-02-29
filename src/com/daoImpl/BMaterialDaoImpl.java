package com.daoImpl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.BMaterial;
import com.bean.BMaterialType;
import com.bean.ExtraBMaterial;
import com.bean.ExtraBrand;
import com.dao.BMaterialDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * BMaterial entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bean.BMaterial
 * @author MyEclipse Persistence Tools
 */
public class BMaterialDaoImpl extends HibernateDaoSupport implements BMaterialDao {
	private static final Logger log = LoggerFactory
			.getLogger(BMaterialDaoImpl.class);
	// property constants
	public static final String MTL_NAME = "mtlName";
	public static final String MTL_PRICE = "mtlPrice";
	public static final String COLOR_COUNT = "colorCount";
	public static final String SEASON = "season";
	public static final String WIDTH = "width";
	public static final String WEIGTH = "weigth";
	public static final String SHRINK_W = "shrinkW";
	public static final String SHRINK_H = "shrinkH";
	public static final String AMOUNT = "amount";
	public static final String MIN_ORDER = "minOrder";
	public static final String PRD_CYCLE = "prdCycle";
	public static final String SYS_USER = "sysUser";
	public static final String MTL_NTX_PRICE = "mtlNtxPrice";
	public static final String MTL_CODE = "mtlCode";
	public static final String MTL_UNIT = "mtlUnit";
	public static final String STATUS = "status";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialDao#save(com.bean.BMaterial)
	 */
	@Override
	public void save(BMaterial transientInstance) {
		log.debug("saving BMaterial instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialDao#delete(com.bean.BMaterial)
	 */
	@Override
	public void delete(Integer mtlId) {
		Session session = getSession();  
		SQLQuery query=null;
		
		String sql="update b_Material set status = 0 where mtl_id = "+mtlId;
		query=session.createSQLQuery(sql);
		
		query.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialDao#findById(java.lang.Integer)
	 */
	@Override
	public BMaterial findById(java.lang.Integer id) {
		log.debug("getting BMaterial instance with id: " + id);
		try {
			BMaterial instance = (BMaterial) getHibernateTemplate().get(
					"com.bean.BMaterial", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialDao#findByExample(com.bean.BMaterial)
	 */
	@Override
	public List findByExample(BMaterial instance) {
		log.debug("finding BMaterial instance by example");
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
	 * @see com.bean.BMaterialDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BMaterial instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from BMaterial as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialDao#findByMtlName(java.lang.Object)
	 */
	@Override
	public List findByMtlName(Object mtlName) {
		return findByProperty(MTL_NAME, mtlName);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialDao#findByMtlPrice(java.lang.Object)
	 */
	@Override
	public List findByMtlPrice(Object mtlPrice) {
		return findByProperty(MTL_PRICE, mtlPrice);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialDao#findByColorCount(java.lang.Object)
	 */
	@Override
	public List findByColorCount(Object colorCount) {
		return findByProperty(COLOR_COUNT, colorCount);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialDao#findBySeason(java.lang.Object)
	 */
	@Override
	public List findBySeason(Object season) {
		return findByProperty(SEASON, season);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialDao#findByWidth(java.lang.Object)
	 */
	@Override
	public List findByWidth(Object width) {
		return findByProperty(WIDTH, width);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialDao#findByWeigth(java.lang.Object)
	 */
	@Override
	public List findByWeigth(Object weigth) {
		return findByProperty(WEIGTH, weigth);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialDao#findByShrinkW(java.lang.Object)
	 */
	@Override
	public List findByShrinkW(Object shrinkW) {
		return findByProperty(SHRINK_W, shrinkW);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialDao#findByShrinkH(java.lang.Object)
	 */
	@Override
	public List findByShrinkH(Object shrinkH) {
		return findByProperty(SHRINK_H, shrinkH);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialDao#findByAmount(java.lang.Object)
	 */
	@Override
	public List findByAmount(Object amount) {
		return findByProperty(AMOUNT, amount);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialDao#findByMinOrder(java.lang.Object)
	 */
	@Override
	public List findByMinOrder(Object minOrder) {
		return findByProperty(MIN_ORDER, minOrder);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialDao#findByPrdCycle(java.lang.Object)
	 */
	@Override
	public List findByPrdCycle(Object prdCycle) {
		return findByProperty(PRD_CYCLE, prdCycle);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialDao#findBySysUser(java.lang.Object)
	 */
	@Override
	public List findBySysUser(Object sysUser) {
		return findByProperty(SYS_USER, sysUser);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialDao#findByMtlNtxPrice(java.lang.Object)
	 */
	@Override
	public List findByMtlNtxPrice(Object mtlNtxPrice) {
		return findByProperty(MTL_NTX_PRICE, mtlNtxPrice);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialDao#findByMtlCode(java.lang.Object)
	 */
	@Override
	public List findByMtlCode(Object mtlCode) {
		return findByProperty(MTL_CODE, mtlCode);
	}

	public List findByMtlUnit(Object mtlUnit) {
		return findByProperty(MTL_UNIT, mtlUnit);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findAll() {
		log.debug("finding all BMaterial instances");
		try {
			String queryString = "from BMaterial";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialDao#merge(com.bean.BMaterial)
	 */
	@Override
	public BMaterial merge(BMaterial detachedInstance) {
		log.debug("merging BMaterial instance");
		try {
			BMaterial result = (BMaterial) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BMaterial instance) {
		log.debug("attaching dirty BMaterial instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BMaterial instance) {
		log.debug("attaching clean BMaterial instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BMaterialDao getFromApplicationContext(ApplicationContext ctx) {
		return (BMaterialDao) ctx.getBean("bmaterialDao");
	}

	/**
	 * 根据mtl_type获取面料信息
	 */
	public List<BMaterial> getMtlCodeByType(int mtlType){
		List<BMaterial> mtlList=null;
		Session session = getSession();  
		SQLQuery query=null;
		String sql="select * from b_Material where mtl_type = "+mtlType+"";
		query=session.createSQLQuery(sql);
		query.addEntity(BMaterial.class);
		mtlList=query.list();
		return mtlList;
	}
	
	public void saveFirst(BMaterial material){
		Session session = getSession();  
		SQLQuery query=null;
		String sql = "insert into b_Material (mtl_code) values (?)";
		query=session.createSQLQuery(sql);
		query.setString(0, material.getMtlCode());
		query.executeUpdate();
	}

	@Override
	public List<ExtraBMaterial> getMtlByOptions(Map<String, Object> options) {
		Session session = getSession();  
		SQLQuery query=null;
		StringBuffer sqlbuf = new StringBuffer("execute [dbo].[sp_get_materials] ");
		sqlbuf.append("@sysUser=admin");
		if(options.size()>0){
			Iterator it = options.entrySet().iterator();
			while (it.hasNext()) {
			   Map.Entry entry = (Map.Entry) it.next();
			   Object key = entry.getKey();
			   Object value = entry.getValue();
			   sqlbuf.append(","+key+"="+value);
			}
		}
		
		List<ExtraBMaterial> eMaterialList = new ArrayList<ExtraBMaterial>();
		query=session.createSQLQuery(sqlbuf.toString());
		List<Object[]> resultSet = query.list();
		
		for (Object[] r : resultSet) {
			ExtraBMaterial ematerial = new ExtraBMaterial();
			ematerial.setMtlId((Integer)r[0]);
			ematerial.setMtlName((String)r[1]);
			ematerial.setMtlType((Integer)r[2]);
			BigDecimal mtlPrice=(BigDecimal)r[3];
			if(mtlPrice!=null){
				ematerial.setMtlPrice(mtlPrice.doubleValue());
			}
			ematerial.setColorCount((Integer)r[4]);
			ematerial.setSeason((String)r[5]);
			ematerial.setWidth((Double)r[6]);
			ematerial.setWeigth((Double)r[7]);
			ematerial.setShrinkW((Double)r[8]);
			ematerial.setShrinkH((Double)r[9]);
			ematerial.setAmount((Integer)r[10]);
			ematerial.setMinOrder((Integer)r[11]);
			ematerial.setPrdCycle((Integer)r[12]);
			ematerial.setSysDt((Date)r[13]);
			ematerial.setSysUser((String)r[14]);
			ematerial.setStatus((Integer)r[15]);
			ematerial.setCreateDt((Date)r[16]);
			BigDecimal mtlNtxPrice = (BigDecimal)r[17];
			if(mtlNtxPrice!=null){
				ematerial.setMtlNtxPrice(mtlNtxPrice.doubleValue());
			}
			ematerial.setMtlCode((String)r[18]);
			ematerial.setMtlUnit((String)r[19]);
			ematerial.setMtlTypeName((String)r[20]);
			ematerial.setSuna((String)r[21]);
			ematerial.setImgUrl((String)r[22]);
			ematerial.setProductInfo((String)r[23]);
			ematerial.setRelProduct((String)r[24]);
			
			eMaterialList.add(ematerial);
		}
		return eMaterialList;
	}
}