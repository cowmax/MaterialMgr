package com.daoImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.BMaterial;
import com.bean.BMaterialSupplier;
import com.bean.BMaterialTestReport;
import com.dao.BMaterialTestReportDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * BMaterialTestReport entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.BMaterialTestReport
 * @author MyEclipse Persistence Tools
 */
public class BMaterialTestReportDaoImpl extends HibernateDaoSupport implements BMaterialTestReportDao {
	private static final Logger log = LoggerFactory
			.getLogger(BMaterialTestReportDaoImpl.class);
	// property constants
	public static final String RISK_OF_LINEAMENT = "riskOfLineament";
	public static final String RISK_FOR_CLASS = "riskForClass";
	public static final String MTRP_SCAN = "mtrpScan";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTestReportDao#save(com.bean.BMaterialTestReport)
	 */
	@Override
	public void save(BMaterialTestReport transientInstance) {
		Session session = getSession();  
		SQLQuery query=null;
		
		String sql="insert into b_Material_Test_Report (mtl_id,risk_of_lineament,risk_for_class) values (?,?,?)";
		query=session.createSQLQuery(sql);
		query.setInteger(0, transientInstance.getBMaterial().getMtlId());
		query.setString(1, transientInstance.getRiskOfLineament());
		query.setString(2, transientInstance.getRiskForClass());
		
		query.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTestReportDao#delete(com.bean.BMaterialTestReport)
	 */
	@Override
	public void delete(BMaterialTestReport persistentInstance) {
		log.debug("deleting BMaterialTestReport instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTestReportDao#findById(java.lang.Integer)
	 */
	@Override
	public BMaterialTestReport findById(java.lang.Integer id) {
		log.debug("getting BMaterialTestReport instance with id: " + id);
		try {
			BMaterialTestReport instance = (BMaterialTestReport) getHibernateTemplate()
					.get("com.bean.BMaterialTestReport", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTestReportDao#findByExample(com.bean.BMaterialTestReport)
	 */
	@Override
	public List findByExample(BMaterialTestReport instance) {
		log.debug("finding BMaterialTestReport instance by example");
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
	 * @see com.bean.BMaterialTestReportDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BMaterialTestReport instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BMaterialTestReport as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTestReportDao#findByRiskOfLineament(java.lang.Object)
	 */
	@Override
	public List findByRiskOfLineament(Object riskOfLineament) {
		return findByProperty(RISK_OF_LINEAMENT, riskOfLineament);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTestReportDao#findByRiskForClass(java.lang.Object)
	 */
	@Override
	public List findByRiskForClass(Object riskForClass) {
		return findByProperty(RISK_FOR_CLASS, riskForClass);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTestReportDao#findByMtrpScan(java.lang.Object)
	 */
	@Override
	public List findByMtrpScan(Object mtrpScan) {
		return findByProperty(MTRP_SCAN, mtrpScan);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTestReportDao#findAll()
	 */
	@Override
	public List<BMaterialTestReport> findAll(Integer mtlId) {
		List<BMaterialTestReport> list = new ArrayList<BMaterialTestReport>();
		Session session = getSession();  
		SQLQuery query=null;
		String sql="select * from b_Material_Test_Report tr inner join b_Material m on tr.mtl_id = m.mtl_id where tr.mtl_id = :mtlId";
		query=session.createSQLQuery(sql);
		query.setInteger("mtlId",mtlId);
		query.addEntity(BMaterialTestReport.class);
		query.addEntity(BMaterial.class);
		List<Object[]> resultSet =query.list();
		for (Object[] r : resultSet) {
			BMaterialTestReport testReport = (BMaterialTestReport)r[0];
			BMaterial material = (BMaterial)r[1];
			testReport.setBMaterial(material);
			list.add(testReport);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTestReportDao#merge(com.bean.BMaterialTestReport)
	 */
	@Override
	public BMaterialTestReport merge(BMaterialTestReport detachedInstance) {
		log.debug("merging BMaterialTestReport instance");
		try {
			BMaterialTestReport result = (BMaterialTestReport) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BMaterialTestReport instance) {
		log.debug("attaching dirty BMaterialTestReport instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BMaterialTestReport instance) {
		log.debug("attaching clean BMaterialTestReport instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * 判断测试报告是否添加daoImpl
	 */
	public int findTestReport(Integer mtlId){
		Session session = getSession();  
		SQLQuery query=null;
		
		String sql="select * from b_Material_Test_Report where mtl_id = :mtlId";
		
		query=session.createSQLQuery(sql);
		
		query.setInteger("mtlId",mtlId);
		
		query.addEntity(BMaterialTestReport.class);
	
		int testReportRow =query.list().size();
		
		return testReportRow;
	}
	
	
	public static BMaterialTestReportDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (BMaterialTestReportDao) ctx.getBean("bmaterialTestReportDao");
	}
}