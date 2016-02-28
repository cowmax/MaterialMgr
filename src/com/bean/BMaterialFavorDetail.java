package com.bean;

import java.util.Date;

/**
 * BMaterialFavorDetail entity. @author MyEclipse Persistence Tools
 */

public class BMaterialFavorDetail implements java.io.Serializable {

	// Fields

	private Integer mfdId;
	private BMaterialFavor BMaterialFavor;
	private BMaterial BMaterial;
	private String dtlMemo;
	private Date sysDt;
	private String placeType;
	private Integer status;

	// Constructors

	/** default constructor */
	public BMaterialFavorDetail() {
	}

	/** minimal constructor */
	public BMaterialFavorDetail(Integer mfdId) {
		this.mfdId = mfdId;
	}

	/** full constructor */
	public BMaterialFavorDetail(Integer mfdId, BMaterialFavor BMaterialFavor,
			BMaterial BMaterial, String dtlMemo, Date sysDt, String placeType,
			Integer status) {
		this.mfdId = mfdId;
		this.BMaterialFavor = BMaterialFavor;
		this.BMaterial = BMaterial;
		this.dtlMemo = dtlMemo;
		this.sysDt = sysDt;
		this.placeType = placeType;
		this.status = status;
	}

	// Property accessors

	public Integer getMfdId() {
		return this.mfdId;
	}

	public void setMfdId(Integer mfdId) {
		this.mfdId = mfdId;
	}

	public BMaterialFavor getBMaterialFavor() {
		return this.BMaterialFavor;
	}

	public void setBMaterialFavor(BMaterialFavor BMaterialFavor) {
		this.BMaterialFavor = BMaterialFavor;
	}

	public BMaterial getBMaterial() {
		return this.BMaterial;
	}

	public void setBMaterial(BMaterial BMaterial) {
		this.BMaterial = BMaterial;
	}

	public String getDtlMemo() {
		return this.dtlMemo;
	}

	public void setDtlMemo(String dtlMemo) {
		this.dtlMemo = dtlMemo;
	}

	public Date getSysDt() {
		return this.sysDt;
	}

	public void setSysDt(Date sysDt) {
		this.sysDt = sysDt;
	}

	public String getPlaceType() {
		return this.placeType;
	}

	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}