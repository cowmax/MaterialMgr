package com.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BMaterialFavor entity. @author MyEclipse Persistence Tools
 */

public class BMaterialFavor implements java.io.Serializable {

	// Fields

	private Integer mfId;
	private String memo;
	private String sysUser;
	private Date sysDt;
	private Integer rate;
	private String productCode;
	private Integer status;
	private String fvrDesc;
	private Set BMaterialFavorDetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public BMaterialFavor() {
	}

	/** minimal constructor */
	public BMaterialFavor(Integer mfId) {
		this.mfId = mfId;
	}

	/** full constructor */
	public BMaterialFavor(Integer mfId, String memo, String sysUser,
			Date sysDt, Integer rate, String productCode, Integer status,
			String fvrDesc, Set BMaterialFavorDetails) {
		this.mfId = mfId;
		this.memo = memo;
		this.sysUser = sysUser;
		this.sysDt = sysDt;
		this.rate = rate;
		this.productCode = productCode;
		this.status = status;
		this.fvrDesc = fvrDesc;
		this.BMaterialFavorDetails = BMaterialFavorDetails;
	}

	// Property accessors

	public Integer getMfId() {
		return this.mfId;
	}

	public void setMfId(Integer mfId) {
		this.mfId = mfId;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getSysUser() {
		return this.sysUser;
	}

	public void setSysUser(String sysUser) {
		this.sysUser = sysUser;
	}

	public Date getSysDt() {
		return this.sysDt;
	}

	public void setSysDt(Date sysDt) {
		this.sysDt = sysDt;
	}

	public Integer getRate() {
		return this.rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getFvrDesc() {
		return this.fvrDesc;
	}

	public void setFvrDesc(String fvrDesc) {
		this.fvrDesc = fvrDesc;
	}

	public Set getBMaterialFavorDetails() {
		return this.BMaterialFavorDetails;
	}

	public void setBMaterialFavorDetails(Set BMaterialFavorDetails) {
		this.BMaterialFavorDetails = BMaterialFavorDetails;
	}

}