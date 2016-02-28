package com.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BMaterialTestReport entity. @author MyEclipse Persistence Tools
 */

public class BMaterialTestReport implements java.io.Serializable {

	// Fields

	private Integer mtrId;
	private BMaterial BMaterial;
	private String riskOfLineament;
	private String riskForClass;
	private Date mtrpTime;
	private String mtrpScan;
	private Set BMaterialTestDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public BMaterialTestReport() {
	}

	/** minimal constructor */
	public BMaterialTestReport(Integer mtrId) {
		this.mtrId = mtrId;
	}

	/** full constructor */
	public BMaterialTestReport(Integer mtrId, BMaterial BMaterial,
			String riskOfLineament, String riskForClass, Date mtrpTime,
			String mtrpScan, Set BMaterialTestDetails) {
		this.mtrId = mtrId;
		this.BMaterial = BMaterial;
		this.riskOfLineament = riskOfLineament;
		this.riskForClass = riskForClass;
		this.mtrpTime = mtrpTime;
		this.mtrpScan = mtrpScan;
		this.BMaterialTestDetails = BMaterialTestDetails;
	}

	// Property accessors

	public Integer getMtrId() {
		return this.mtrId;
	}

	public void setMtrId(Integer mtrId) {
		this.mtrId = mtrId;
	}

	public BMaterial getBMaterial() {
		return this.BMaterial;
	}

	public void setBMaterial(BMaterial BMaterial) {
		this.BMaterial = BMaterial;
	}

	public String getRiskOfLineament() {
		return this.riskOfLineament;
	}

	public void setRiskOfLineament(String riskOfLineament) {
		this.riskOfLineament = riskOfLineament;
	}

	public String getRiskForClass() {
		return this.riskForClass;
	}

	public void setRiskForClass(String riskForClass) {
		this.riskForClass = riskForClass;
	}

	public Date getMtrpTime() {
		return this.mtrpTime;
	}

	public void setMtrpTime(Date mtrpTime) {
		this.mtrpTime = mtrpTime;
	}

	public String getMtrpScan() {
		return this.mtrpScan;
	}

	public void setMtrpScan(String mtrpScan) {
		this.mtrpScan = mtrpScan;
	}

	public Set getBMaterialTestDetails() {
		return this.BMaterialTestDetails;
	}

	public void setBMaterialTestDetails(Set BMaterialTestDetails) {
		this.BMaterialTestDetails = BMaterialTestDetails;
	}

}