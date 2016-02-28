package com.bean;

import java.util.Date;

/**
 * BMaterialProp entity. @author MyEclipse Persistence Tools
 */

public class BMaterialProp implements java.io.Serializable {

	// Fields

	private Integer mpId;
	private BMaterialPropType BMaterialPropType;
	private BMaterial BMaterial;
	private String propValue;
	private Date sysDt;
	private String sysUser;
	private String mtlCode;

	// Constructors

	/** default constructor */
	public BMaterialProp() {
	}

	/** minimal constructor */
	public BMaterialProp(Integer mpId) {
		this.mpId = mpId;
	}

	/** full constructor */
	public BMaterialProp(Integer mpId, BMaterialPropType BMaterialPropType,
			BMaterial BMaterial, String propValue, Date sysDt, String sysUser,
			String mtlCode) {
		this.mpId = mpId;
		this.BMaterialPropType = BMaterialPropType;
		this.BMaterial = BMaterial;
		this.propValue = propValue;
		this.sysDt = sysDt;
		this.sysUser = sysUser;
		this.mtlCode = mtlCode;
	}

	// Property accessors

	public Integer getMpId() {
		return this.mpId;
	}

	public void setMpId(Integer mpId) {
		this.mpId = mpId;
	}

	public BMaterialPropType getBMaterialPropType() {
		return this.BMaterialPropType;
	}

	public void setBMaterialPropType(BMaterialPropType BMaterialPropType) {
		this.BMaterialPropType = BMaterialPropType;
	}

	public BMaterial getBMaterial() {
		return this.BMaterial;
	}

	public void setBMaterial(BMaterial BMaterial) {
		this.BMaterial = BMaterial;
	}

	public String getPropValue() {
		return this.propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

	public Date getSysDt() {
		return this.sysDt;
	}

	public void setSysDt(Date sysDt) {
		this.sysDt = sysDt;
	}

	public String getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(String sysUser) {
		this.sysUser = sysUser;
	}

	public String getMtlCode() {
		return this.mtlCode;
	}

	public void setMtlCode(String mtlCode) {
		this.mtlCode = mtlCode;
	}

}