package com.bean;

/**
 * BMaterialTestDetail entity. @author MyEclipse Persistence Tools
 */

public class BMaterialTestDetail implements java.io.Serializable {

	// Fields

	private Integer mtrdId;
	private BMaterialTestReport BMaterialTestReport;

	// Constructors

	/** default constructor */
	public BMaterialTestDetail() {
	}

	/** minimal constructor */
	public BMaterialTestDetail(Integer mtrdId) {
		this.mtrdId = mtrdId;
	}

	/** full constructor */
	public BMaterialTestDetail(Integer mtrdId,
			BMaterialTestReport BMaterialTestReport) {
		this.mtrdId = mtrdId;
		this.BMaterialTestReport = BMaterialTestReport;
	}

	// Property accessors

	public Integer getMtrdId() {
		return this.mtrdId;
	}

	public void setMtrdId(Integer mtrdId) {
		this.mtrdId = mtrdId;
	}

	public BMaterialTestReport getBMaterialTestReport() {
		return this.BMaterialTestReport;
	}

	public void setBMaterialTestReport(BMaterialTestReport BMaterialTestReport) {
		this.BMaterialTestReport = BMaterialTestReport;
	}

}