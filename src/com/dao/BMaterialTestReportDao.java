package com.dao;

import java.util.List;

import com.bean.BMaterialTestReport;

public interface BMaterialTestReportDao {

	public abstract void save(BMaterialTestReport transientInstance);

	public abstract void delete(BMaterialTestReport persistentInstance);

	public abstract BMaterialTestReport findById(java.lang.Integer id);

	public abstract List findByExample(BMaterialTestReport instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByRiskOfLineament(Object riskOfLineament);

	public abstract List findByRiskForClass(Object riskForClass);

	public abstract List findByMtrpScan(Object mtrpScan);

	public abstract List<BMaterialTestReport> findAll(Integer mtlId);

	public abstract int findTestReport(Integer mtlId);
	
	public abstract BMaterialTestReport merge(
			BMaterialTestReport detachedInstance);

}