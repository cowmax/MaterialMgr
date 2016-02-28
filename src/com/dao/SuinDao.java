package com.dao;

import java.util.List;

import com.bean.Suin;

public interface SuinDao {

	public abstract void save(Suin transientInstance);

	public abstract void delete(Suin persistentInstance);

	public abstract Suin findById(java.lang.String id);

	public abstract List findByExample(Suin instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findById(Object id);

	public abstract List findByPsid(Object psid);

	public abstract List findByCeve(Object ceve);

	public abstract List findBySuna(Object suna);

	public abstract List findByStds(Object stds);

	public abstract List findByStco(Object stco);

	public abstract List findByGeid(Object geid);

	public abstract List findByPsad(Object psad);

	public abstract List findByPmad(Object pmad);

	public abstract List findByWhad(Object whad);

	public abstract List findByCous(Object cous);

	public abstract List findByPost(Object post);

	public abstract List findByTel1(Object tel1);

	public abstract List findByTel2(Object tel2);

	public abstract List findByFax(Object fax);

	public abstract List findByPhon(Object phon);

	public abstract List findByEmai(Object emai);

	public abstract List findByPmfl(Object pmfl);

	public abstract List findByIfcn(Object ifcn);

	public abstract List findByCnty(Object cnty);

	public abstract List findByPoit(Object poit);

	public abstract List findByAcmt(Object acmt);

	public abstract List findByPamt(Object pamt);

	public abstract List findByBkna(Object bkna);

	public abstract List findByCkna(Object ckna);

	public abstract List findByCkno(Object ckno);

	public abstract List findByPydt(Object pydt);

	public abstract List findBySdco(Object sdco);

	public abstract List findByCrus(Object crus);

	public abstract List findByCrna(Object crna);

	public abstract List findByEdus(Object edus);

	public abstract List findByEdna(Object edna);

	public abstract List findByCori(Object cori);

	public abstract List findByOrid(Object orid);

	public abstract List findByIfus(Object ifus);

	public abstract List findByIsym(Object isym);

	public abstract List findByStat(Object stat);

	public abstract List findByRema(Object rema);

	public abstract List findByErpc(Object erpc);

	public abstract List findByIfcd(Object ifcd);

	public abstract List findByBkpt(Object bkpt);

	public abstract List findByCpco(Object cpco);

	public abstract List findByYfcd(Object yfcd);

	public abstract List findBySuNum(Object suNum);

	public abstract List findByHtco(Object htco);

	public abstract List findAll();

	public abstract Suin merge(Suin detachedInstance);
	
}