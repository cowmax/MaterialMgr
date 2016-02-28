package com.bean;

/**
 * BMaterialSupplier entity. @author MyEclipse Persistence Tools
 */

public class BMaterialSupplier implements java.io.Serializable {

	// Fields

	private Integer mtsId;
	private Suin suin;
	private BMaterial BMaterial;
	private String mtsCode;		// 供方面料编码
	private String mtsName;		// 供方面料名称
	private Integer mtsColorCount;	// 供方面料颜色

	// Constructors

	/** default constructor */
	public BMaterialSupplier() {
	}

	/** minimal constructor */
	public BMaterialSupplier(Integer mtsId, Suin suin) {
		this.mtsId = mtsId;
		this.suin = suin;
	}

	/** full constructor */
	public BMaterialSupplier(Integer mtsId, Suin suin, BMaterial BMaterial,
			String mtsCode, String mtsName, Integer mtsColorCount) {
		this.mtsId = mtsId;
		this.suin = suin;
		this.BMaterial = BMaterial;
		this.mtsCode = mtsCode;
		this.mtsName = mtsName;
		this.mtsColorCount = mtsColorCount;
	}

	// Property accessors

	public Integer getMtsId() {
		return this.mtsId;
	}

	public void setMtsId(Integer mtsId) {
		this.mtsId = mtsId;
	}

	public Suin getSuin() {
		return this.suin;
	}

	public void setSuin(Suin suin) {
		this.suin = suin;
	}

	public BMaterial getBMaterial() {
		return this.BMaterial;
	}

	public void setBMaterial(BMaterial BMaterial) {
		this.BMaterial = BMaterial;
	}

	public String getMtsCode() {
		return this.mtsCode;
	}

	public void setMtsCode(String mtsCode) {
		this.mtsCode = mtsCode;
	}

	public String getMtsName() {
		return this.mtsName;
	}

	public void setMtsName(String mtsName) {
		this.mtsName = mtsName;
	}

	public Integer getMtsColorCount() {
		return this.mtsColorCount;
	}

	public void setMtsColorCount(Integer mtsColorCount) {
		this.mtsColorCount = mtsColorCount;
	}

}