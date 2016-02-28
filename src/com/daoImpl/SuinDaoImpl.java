package com.daoImpl;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bean.BMaterial;
import com.bean.Suin;
import com.dao.SuinDao;

/**
 * A data access object (DAO) providing persistence and search support for Suin
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.bean.Suin
 * @author MyEclipse Persistence Tools
 */
public class SuinDaoImpl extends HibernateDaoSupport implements SuinDao {
	private static final Logger log = LoggerFactory.getLogger(SuinDaoImpl.class);
	// property constants
	public static final String ID = "id";
	public static final String PSID = "psid";
	public static final String CEVE = "ceve";
	public static final String SUNA = "suna";
	public static final String STDS = "stds";
	public static final String STCO = "stco";
	public static final String GEID = "geid";
	public static final String PSAD = "psad";
	public static final String PMAD = "pmad";
	public static final String WHAD = "whad";
	public static final String COUS = "cous";
	public static final String POST = "post";
	public static final String TEL1 = "tel1";
	public static final String TEL2 = "tel2";
	public static final String FAX = "fax";
	public static final String PHON = "phon";
	public static final String EMAI = "emai";
	public static final String PMFL = "pmfl";
	public static final String IFCN = "ifcn";
	public static final String CNTY = "cnty";
	public static final String POIT = "poit";
	public static final String ACMT = "acmt";
	public static final String PAMT = "pamt";
	public static final String BKNA = "bkna";
	public static final String CKNA = "ckna";
	public static final String CKNO = "ckno";
	public static final String PYDT = "pydt";
	public static final String SDCO = "sdco";
	public static final String CRUS = "crus";
	public static final String CRNA = "crna";
	public static final String EDUS = "edus";
	public static final String EDNA = "edna";
	public static final String CORI = "cori";
	public static final String ORID = "orid";
	public static final String IFUS = "ifus";
	public static final String ISYM = "isym";
	public static final String STAT = "stat";
	public static final String REMA = "rema";
	public static final String ERPC = "erpc";
	public static final String IFCD = "ifcd";
	public static final String BKPT = "bkpt";
	public static final String CPCO = "cpco";
	public static final String YFCD = "yfcd";
	public static final String SU_NUM = "suNum";
	public static final String HTCO = "htco";

	protected void initDao() {
		// do nothing
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#save(com.bean.Suin)
	 */
	@Override
	public void save(Suin suin) {
		Session session = getSession();  
		SQLQuery query=null;
		String sql = "INSERT INTO Suin (suid,suna,cous,phon,tel1,fax,psad,emai,rema) VALUES(?,?,?,?,?,?,?,?,?)";
		query=session.createSQLQuery(sql);
		
		query.setString(0, suin.getSuid());
		query.setString(1, suin.getSuna());
		query.setString(2, suin.getCous());
		query.setString(3, suin.getPhon());
		query.setString(4, suin.getTel1());
		query.setString(5, suin.getFax());
		query.setString(6, suin.getPsad());
		query.setString(7, suin.getEmai());
		query.setString(8, suin.getRema());
		
		query.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#delete(com.bean.Suin)
	 */
	@Override
	public void delete(Suin persistentInstance) {
		log.debug("deleting Suin instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findById(java.lang.String)
	 */
	@Override
	public Suin findById(java.lang.String id) {
		log.debug("getting Suin instance with id: " + id);
		try {
			Suin instance = (Suin) getHibernateTemplate().get("com.bean.Suin",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByExample(com.bean.Suin)
	 */
	@Override
	public List findByExample(Suin instance) {
		log.debug("finding Suin instance by example");
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
	 * @see com.bean.SuinDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Suin instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Suin as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findById(java.lang.Object)
	 */
	@Override
	public List findById(Object id) {
		return findByProperty(ID, id);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByPsid(java.lang.Object)
	 */
	@Override
	public List findByPsid(Object psid) {
		return findByProperty(PSID, psid);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByCeve(java.lang.Object)
	 */
	@Override
	public List findByCeve(Object ceve) {
		return findByProperty(CEVE, ceve);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findBySuna(java.lang.Object)
	 */
	@Override
	public List findBySuna(Object suna) {
		return findByProperty(SUNA, suna);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByStds(java.lang.Object)
	 */
	@Override
	public List findByStds(Object stds) {
		return findByProperty(STDS, stds);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByStco(java.lang.Object)
	 */
	@Override
	public List findByStco(Object stco) {
		return findByProperty(STCO, stco);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByGeid(java.lang.Object)
	 */
	@Override
	public List findByGeid(Object geid) {
		return findByProperty(GEID, geid);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByPsad(java.lang.Object)
	 */
	@Override
	public List findByPsad(Object psad) {
		return findByProperty(PSAD, psad);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByPmad(java.lang.Object)
	 */
	@Override
	public List findByPmad(Object pmad) {
		return findByProperty(PMAD, pmad);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByWhad(java.lang.Object)
	 */
	@Override
	public List findByWhad(Object whad) {
		return findByProperty(WHAD, whad);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByCous(java.lang.Object)
	 */
	@Override
	public List findByCous(Object cous) {
		return findByProperty(COUS, cous);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByPost(java.lang.Object)
	 */
	@Override
	public List findByPost(Object post) {
		return findByProperty(POST, post);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByTel1(java.lang.Object)
	 */
	@Override
	public List findByTel1(Object tel1) {
		return findByProperty(TEL1, tel1);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByTel2(java.lang.Object)
	 */
	@Override
	public List findByTel2(Object tel2) {
		return findByProperty(TEL2, tel2);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByFax(java.lang.Object)
	 */
	@Override
	public List findByFax(Object fax) {
		return findByProperty(FAX, fax);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByPhon(java.lang.Object)
	 */
	@Override
	public List findByPhon(Object phon) {
		return findByProperty(PHON, phon);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByEmai(java.lang.Object)
	 */
	@Override
	public List findByEmai(Object emai) {
		return findByProperty(EMAI, emai);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByPmfl(java.lang.Object)
	 */
	@Override
	public List findByPmfl(Object pmfl) {
		return findByProperty(PMFL, pmfl);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByIfcn(java.lang.Object)
	 */
	@Override
	public List findByIfcn(Object ifcn) {
		return findByProperty(IFCN, ifcn);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByCnty(java.lang.Object)
	 */
	@Override
	public List findByCnty(Object cnty) {
		return findByProperty(CNTY, cnty);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByPoit(java.lang.Object)
	 */
	@Override
	public List findByPoit(Object poit) {
		return findByProperty(POIT, poit);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByAcmt(java.lang.Object)
	 */
	@Override
	public List findByAcmt(Object acmt) {
		return findByProperty(ACMT, acmt);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByPamt(java.lang.Object)
	 */
	@Override
	public List findByPamt(Object pamt) {
		return findByProperty(PAMT, pamt);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByBkna(java.lang.Object)
	 */
	@Override
	public List findByBkna(Object bkna) {
		return findByProperty(BKNA, bkna);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByCkna(java.lang.Object)
	 */
	@Override
	public List findByCkna(Object ckna) {
		return findByProperty(CKNA, ckna);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByCkno(java.lang.Object)
	 */
	@Override
	public List findByCkno(Object ckno) {
		return findByProperty(CKNO, ckno);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByPydt(java.lang.Object)
	 */
	@Override
	public List findByPydt(Object pydt) {
		return findByProperty(PYDT, pydt);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findBySdco(java.lang.Object)
	 */
	@Override
	public List findBySdco(Object sdco) {
		return findByProperty(SDCO, sdco);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByCrus(java.lang.Object)
	 */
	@Override
	public List findByCrus(Object crus) {
		return findByProperty(CRUS, crus);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByCrna(java.lang.Object)
	 */
	@Override
	public List findByCrna(Object crna) {
		return findByProperty(CRNA, crna);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByEdus(java.lang.Object)
	 */
	@Override
	public List findByEdus(Object edus) {
		return findByProperty(EDUS, edus);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByEdna(java.lang.Object)
	 */
	@Override
	public List findByEdna(Object edna) {
		return findByProperty(EDNA, edna);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByCori(java.lang.Object)
	 */
	@Override
	public List findByCori(Object cori) {
		return findByProperty(CORI, cori);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByOrid(java.lang.Object)
	 */
	@Override
	public List findByOrid(Object orid) {
		return findByProperty(ORID, orid);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByIfus(java.lang.Object)
	 */
	@Override
	public List findByIfus(Object ifus) {
		return findByProperty(IFUS, ifus);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByIsym(java.lang.Object)
	 */
	@Override
	public List findByIsym(Object isym) {
		return findByProperty(ISYM, isym);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByStat(java.lang.Object)
	 */
	@Override
	public List findByStat(Object stat) {
		return findByProperty(STAT, stat);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByRema(java.lang.Object)
	 */
	@Override
	public List findByRema(Object rema) {
		return findByProperty(REMA, rema);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByErpc(java.lang.Object)
	 */
	@Override
	public List findByErpc(Object erpc) {
		return findByProperty(ERPC, erpc);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByIfcd(java.lang.Object)
	 */
	@Override
	public List findByIfcd(Object ifcd) {
		return findByProperty(IFCD, ifcd);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByBkpt(java.lang.Object)
	 */
	@Override
	public List findByBkpt(Object bkpt) {
		return findByProperty(BKPT, bkpt);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByCpco(java.lang.Object)
	 */
	@Override
	public List findByCpco(Object cpco) {
		return findByProperty(CPCO, cpco);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByYfcd(java.lang.Object)
	 */
	@Override
	public List findByYfcd(Object yfcd) {
		return findByProperty(YFCD, yfcd);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findBySuNum(java.lang.Object)
	 */
	@Override
	public List findBySuNum(Object suNum) {
		return findByProperty(SU_NUM, suNum);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findByHtco(java.lang.Object)
	 */
	@Override
	public List findByHtco(Object htco) {
		return findByProperty(HTCO, htco);
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all Suin instances");
		try {
			String queryString = "from Suin where stco = '01'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bean.SuinDao#merge(com.bean.Suin)
	 */
	@Override
	public Suin merge(Suin detachedInstance) {
		log.debug("merging Suin instance");
		try {
			Suin result = (Suin) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Suin instance) {
		log.debug("attaching dirty Suin instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Suin instance) {
		log.debug("attaching clean Suin instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SuinDao getFromApplicationContext(ApplicationContext ctx) {
		return (SuinDao) ctx.getBean("suinDao");
	}

}