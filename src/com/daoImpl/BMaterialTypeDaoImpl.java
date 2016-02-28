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
import com.bean.BMaterialType;
import com.bean.ExtraType;
import com.bean.Suin;
import com.dao.BMaterialTypeDao;

/**
 * A data access object (DAO) providing persistence and search support for
 * BMaterialType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.bean.BMaterialType
 * @author MyEclipse Persistence Tools
 */
public class BMaterialTypeDaoImpl extends HibernateDaoSupport implements BMaterialTypeDao {
	private static final Logger log = LoggerFactory
			.getLogger(BMaterialTypeDaoImpl.class);
	// property constants
	public static final String ID = "id";
	public static final String PID = "pid";
	public static final String MTL_TYPE_NAME = "mtlTypeName";
	public static final String SYS_USER = "sysUser";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTypeDao#save(com.bean.BMaterialType)
	 */
	@Override
	public void save(BMaterialType transientInstance) {
		log.debug("saving BMaterialType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTypeDao#delete(com.bean.BMaterialType)
	 */
	@Override
	public void delete(BMaterialType persistentInstance) {
		log.debug("deleting BMaterialType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTypeDao#findById(java.lang.String)
	 */
	@Override
	public BMaterialType findById(java.lang.Integer id) {
		log.debug("getting BMaterialType instance with mtlType: " + id);
		try {
			BMaterialType instance = (BMaterialType) getHibernateTemplate()
					.get("com.bean.BMaterialType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTypeDao#findByExample(com.bean.BMaterialType)
	 */
	@Override
	public List findByExample(BMaterialType instance) {
		log.debug("finding BMaterialType instance by example");
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
	 * @see com.bean.BMaterialTypeDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding BMaterialType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from BMaterialType as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTypeDao#findById(java.lang.Object)
	 */
	@Override
	public List findById(Object id) {
		return findByProperty(ID, id);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTypeDao#findByPid(java.lang.Object)
	 */
	@Override
	public List findByPid(Object pid) {
		return findByProperty(PID, pid);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTypeDao#findByMtlTypeName(java.lang.Object)
	 */
	@Override
	public List findByMtlTypeName(Object mtlTypeName) {
		return findByProperty(MTL_TYPE_NAME, mtlTypeName);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTypeDao#findBySysUser(java.lang.Object)
	 */
	@Override
	public List findBySysUser(Object sysUser) {
		return findByProperty(SYS_USER, sysUser);
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTypeDao#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all BMaterialType instances");
		try {
			String queryString = "from BMaterialType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.BMaterialTypeDao#merge(com.bean.BMaterialType)
	 */
	@Override
	public BMaterialType merge(BMaterialType detachedInstance) {
		log.debug("merging BMaterialType instance");
		try {
			BMaterialType result = (BMaterialType) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(BMaterialType instance) {
		log.debug("attaching dirty BMaterialType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(BMaterialType instance) {
		log.debug("attaching clean BMaterialType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static BMaterialTypeDao getFromApplicationContext(
			ApplicationContext ctx) {
		return (BMaterialTypeDao) ctx.getBean("bmaterialTypeDao");
	}

	@Override
	public List<BMaterialType> loadParentTypeList() {
		List<BMaterialType> list=null;
		Session session = getSession();  
		SQLQuery query=null;
		String sql="select * from b_Material_Type where pid = 0";
		query=session.createSQLQuery(sql);
		query.addEntity(BMaterialType.class);
		list=query.list();
		return list;
	}

	@Override
	public List<BMaterialType> getMtlTypeListByPid(int pid) {
		List<BMaterialType> list=null;
		Session session = getSession();  
		SQLQuery query=null;
		String sql="select * from b_Material_Type where pid = :pid";
		query=session.createSQLQuery(sql);
		query.setInteger("pid", pid);
		query.addEntity(BMaterialType.class);
		list=query.list();
		return list;
	}

	@Override
	public List<ExtraType> getAllChildTypeList() {
		List<ExtraType> list = new ArrayList<ExtraType>();
		Session session = getSession();  
		SQLQuery query=null;
		String sql="select mtl_type,mtl_type_name from b_Material_Type where mtl_type not in (select distinct pid from b_Material_Type) and pid !=0";
		query=session.createSQLQuery(sql);
		List<Object[]> resultSet = query.list();
		ExtraType etype = null;
		for (Object[] r : resultSet) {
			etype = new ExtraType();
			etype.setMtlType((Integer)r[0]);
			etype.setMtlTypeName((String)r[1]);
			
			list.add(etype);
		}
		return list;
	}

	/**
	 * 获取所有类型信息集合
	 */
	public List<ExtraType> getAllTypeList() {
		List<ExtraType> list=new ArrayList<ExtraType>();
		Session session = getSession();  
		SQLQuery query=null;
		String sql="select a.mtl_type, a.mtl_type_name, a.pid, b.mtl_type_name as p_type_name from b_Material_Type a " +
				"inner join (select mtl_type, mtl_type_name from b_Material_Type) b " +
				"on a.pid = b.mtl_type where a.pid != 0 order by mtl_type desc";
		query=session.createSQLQuery(sql);
		List<Object[]> resultSet = query.list();
		for (Object[] r : resultSet) {
			ExtraType etype = new ExtraType();
			Integer mtlType = (Integer)r[0];
			String ptypeName = (String)r[1];
			Integer pid = (Integer)r[2];
			String mtlTypeName = (String)r[3];
			etype.setMtlType(mtlType);
			etype.setPid(pid);
			etype.setMtlTypeName(mtlTypeName);
			etype.setPtypeName(ptypeName);
			list.add(etype);
		}
		return list;
	}

	@Override
	public void saveType(BMaterialType mtype) {
		Session session = getSession();  
		SQLQuery query=null;
		
		String sql="insert into b_Material_Type (pid,mtl_type_name) values (?,?)";
		query=session.createSQLQuery(sql);
		query.setInteger(0, mtype.getPid());
		query.setString(1, mtype.getMtlTypeName());
		
		query.executeUpdate();
	}
	
}