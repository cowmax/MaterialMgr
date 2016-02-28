package com.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BMaterialPropType entity. @author MyEclipse Persistence Tools
 */

public class BMaterialPropType implements java.io.Serializable {

	// Fields

	private Integer mptId;
	private String mptName;
	private Integer mtlTypeId;
	private Date sysDt;
	private String sysUser;
	private Set BMaterialProps = new HashSet(0);

	// Constructors

	/** default constructor */
	public BMaterialPropType() {
	}

	/** minimal constructor */
	public BMaterialPropType(Integer mptId) {
		this.mptId = mptId;
	}

	/** full constructor */
	public BMaterialPropType(Integer mptId, String mptName, Integer mtlTypeId,
			Date sysDt, String sysUser, Set BMaterialProps) {
		this.mptId = mptId;
		this.mptName = mptName;
		this.mtlTypeId = mtlTypeId;
		this.sysDt = sysDt;
		this.sysUser = sysUser;
		this.BMaterialProps = BMaterialProps;
	}

	// Property accessors

	public Integer getMptId() {
		return this.mptId;
	}

	public void setMptId(Integer mptId) {
		this.mptId = mptId;
	}

	public String getMptName() {
		return this.mptName;
	}

	public void setMptName(String mptName) {
		this.mptName = mptName;
	}

	public Integer getMtlTypeId() {
		return this.mtlTypeId;
	}

	public void setMtlTypeId(Integer mtlTypeId) {
		this.mtlTypeId = mtlTypeId;
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

	public Set getBMaterialProps() {
		return this.BMaterialProps;
	}

	public void setBMaterialProps(Set BMaterialProps) {
		this.BMaterialProps = BMaterialProps;
	}

}