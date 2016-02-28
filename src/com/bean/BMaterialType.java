package com.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BMaterialType entity. @author MyEclipse Persistence Tools
 */

public class BMaterialType implements java.io.Serializable {

	// Fields

	private Integer mtlType;
	private Integer pid;
	private String mtlTypeName;
	private Date sysDt;
	private String sysUser;
	private Set BMaterials = new HashSet(0);

	// Constructors

	/** default constructor */
	public BMaterialType() {
	}

	/** minimal constructor */
	public BMaterialType(Integer mtlType) {
		this.mtlType = mtlType;
	}

	public BMaterialType(Integer pid, String mtlTypeName) {
		this.pid = pid;
		this.mtlTypeName = mtlTypeName;
	}

	/** full constructor */
	public BMaterialType(Integer mtlType, Integer pid, String mtlTypeName,
			Date sysDt, String sysUser, Set BMaterials) {
		this.mtlType = mtlType;
		this.pid = pid;
		this.mtlTypeName = mtlTypeName;
		this.sysDt = sysDt;
		this.sysUser = sysUser;
		this.BMaterials = BMaterials;
	}

	// Property accessors

	public Integer getMtlType() {
		return this.mtlType;
	}

	public void setMtlType(Integer mtlType) {
		this.mtlType = mtlType;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getMtlTypeName() {
		return this.mtlTypeName;
	}

	public void setMtlTypeName(String mtlTypeName) {
		this.mtlTypeName = mtlTypeName;
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

	public Set getBMaterials() {
		return this.BMaterials;
	}

	public void setBMaterials(Set BMaterials) {
		this.BMaterials = BMaterials;
	}

}