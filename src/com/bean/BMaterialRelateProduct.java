package com.bean;

/**
 * BMaterialRelateProduct entity. @author MyEclipse Persistence Tools
 */

public class BMaterialRelateProduct implements java.io.Serializable {

	// Fields

	private Integer mrpId;
	private BMaterial BMaterial;
	private String memo;
	private Integer relateStatus;
	private String productCode;

	// Constructors

	/** default constructor */
	public BMaterialRelateProduct() {
	}

	/** minimal constructor */
	public BMaterialRelateProduct(Integer mrpId) {
		this.mrpId = mrpId;
	}

	public BMaterialRelateProduct(com.bean.BMaterial bMaterial, String memo,
			String productCode) {
		super();
		BMaterial = bMaterial;
		this.memo = memo;
		this.productCode = productCode;
	}

	/** full constructor */
	public BMaterialRelateProduct(Integer mrpId, BMaterial BMaterial,
			String memo, Integer relateStatus, String productCode) {
		this.mrpId = mrpId;
		this.BMaterial = BMaterial;
		this.memo = memo;
		this.relateStatus = relateStatus;
		this.productCode = productCode;
	}

	// Property accessors

	public Integer getMrpId() {
		return this.mrpId;
	}

	public void setMrpId(Integer mrpId) {
		this.mrpId = mrpId;
	}

	public BMaterial getBMaterial() {
		return this.BMaterial;
	}

	public void setBMaterial(BMaterial BMaterial) {
		this.BMaterial = BMaterial;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getRelateStatus() {
		return this.relateStatus;
	}

	public void setRelateStatus(Integer relateStatus) {
		this.relateStatus = relateStatus;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

}