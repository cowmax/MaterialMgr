package com.actions;

import java.util.ArrayList;
import java.util.List;

import com.bean.BMaterial;
import com.bean.BMaterialTestReport;
import com.opensymphony.xwork2.ActionSupport;
import com.serviceImpl.BMaterialServiceImpl;
import com.serviceImpl.BMaterialTestReportServiceImpl;

public class BMaterialTestReportAction extends ActionSupport {

	private BMaterialTestReportServiceImpl bmaterialTestReportBiz;
	private BMaterialServiceImpl bmaterialBiz;
	private BMaterialTestReport testReport;
	private BMaterial material;
	private Integer mtlId;
	private boolean flag;
	
	private List<BMaterialTestReport> mtlTestReports;//获取所有的风险集合

	
	public BMaterialTestReportAction() {
		mtlTestReports =new ArrayList<BMaterialTestReport>();
	}

	public BMaterialTestReportServiceImpl getBmaterialTestReportBiz() {
		return bmaterialTestReportBiz;
	}

	public void setBmaterialTestReportBiz(
			BMaterialTestReportServiceImpl bmaterialTestReportBiz) {
		this.bmaterialTestReportBiz = bmaterialTestReportBiz;
	}
	
	public BMaterialServiceImpl getBmaterialBiz() {
		return bmaterialBiz;
	}

	public void setBmaterialBiz(BMaterialServiceImpl bmaterialBiz) {
		this.bmaterialBiz = bmaterialBiz;
	}

	public Integer getMtlId() {
		return mtlId;
	}

	public void setMtlId(Integer mtlId) {
		this.mtlId = mtlId;
	}

	public BMaterialTestReport getTestReport() {
		return testReport;
	}

	public void setTestReport(BMaterialTestReport testReport) {
		this.testReport = testReport;
	}

	public BMaterial getMaterial() {
		return material;
	}

	public void setMaterial(BMaterial material) {
		this.material = material;
	}
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public List<BMaterialTestReport> getMtlTestReports() {
		return mtlTestReports;
	}

	public void setMtlTestReports(List<BMaterialTestReport> mtlTestReports) {
		this.mtlTestReports = mtlTestReports;
	}

	/**
	 * 添加面料测试报告操作
	 * @return
	 */
	public String addTestReportOpt(){
		
		material = bmaterialBiz.findByMtlId(mtlId);
		
		//判断测试报告是否存在
		mtlTestReports=bmaterialTestReportBiz.findTestReportsByMtlid(mtlId);
		
		if(mtlTestReports.size()>0){
			
			//返回对象
			testReport=mtlTestReports.get(0);
			
			return "allTestReport";
		
		}else{
			
			return "testReportOpt";
		}
	}
	
	/**
	 * 添加面料测试报告操作
	 * @return
	 */
	public String detailTestReportOpt(){
		
		material = bmaterialBiz.findByMtlId(mtlId);
		
		//判断测试报告是否存在
		mtlTestReports=bmaterialTestReportBiz.findTestReportsByMtlid(mtlId);
		
		if(mtlTestReports.size()>0){
			
			//返回对象
			testReport=mtlTestReports.get(0);
			
			return "detailTestReport";
		
		}else{
			
			return "testReportOpt";
		}
	}
	
	/**
	 * 添加测试报告
	 * @return
	 */
	public String addTestReport(){
		try {
			
			testReport.setBMaterial(material);
			bmaterialTestReportBiz.saveTestReport(testReport);
			flag = true;
		} catch (Exception e) {
			flag = false;
		} 
		return SUCCESS;
	}
	
	/**
	 * 修改测试报告
	 */
	public String updateTestReport(){
	try{
		testReport.setBMaterial(material);
		bmaterialTestReportBiz.updateTestReport(testReport);
		flag = true;
	}catch (Exception e) {
		flag = false;
	}
		return "update";
	}
	
	
	/**
	 * 上传测试报告
	 */
	public static String UploadTestReport(){
		
		String mtrpScan;
		return "mtrpScan";
	}
	
}
