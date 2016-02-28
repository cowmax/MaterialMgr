package com.serviceImpl;

import java.util.List;

import com.bean.BMaterialTestReport;
import com.dao.BMaterialTestReportDao;

public class BMaterialTestReportServiceImpl {

	private BMaterialTestReportDao bmaterialTestReportDao;

	public BMaterialTestReportDao getBmaterialTestReportDao() {
		return bmaterialTestReportDao;
	}

	public void setBmaterialTestReportDao(
			BMaterialTestReportDao bmaterialTestReportDao) {
		this.bmaterialTestReportDao = bmaterialTestReportDao;
	}
	
	/**
	 * 
	 * @param testReport
	 */
	public void saveTestReport(BMaterialTestReport testReport){
		bmaterialTestReportDao.save(testReport);
	}
	
	/**
	 * 获取面料测试报告
	 * @param mtlId
	 * @return
	 */
	public List<BMaterialTestReport> findTestReportsByMtlid(Integer mtlId){
		return bmaterialTestReportDao.findAll(mtlId);
	}
	
	/**
	 * 修改测试报告是否添加service
	 */
	public void  updateTestReport(BMaterialTestReport testReport){
		
		bmaterialTestReportDao.merge(testReport);
	
	}
	
	
}
