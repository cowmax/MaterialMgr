package com.bean;

/**
 * BMaterialBrand entity. @author MyEclipse Persistence Tools
 */

public class BMaterialBrand implements java.io.Serializable {

	// Fields

	private Integer id;
	private BMaterial BMaterial;
	private String brandName;
	private String brand;

	// Constructors

	/** default constructor */
	public BMaterialBrand() {
	}

	/** minimal constructor */
	public BMaterialBrand(Integer id) {
		this.id = id;
	}

	public BMaterialBrand(String brandName,String brand) {
		this.brandName = brandName;
		this.brand = brand;
	}

	/** full constructor */
	public BMaterialBrand(Integer id, BMaterial BMaterial, String brandName,
			String brand) {
		this.id = id;
		this.BMaterial = BMaterial;
		this.brandName = brandName;
		this.brand = brand;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BMaterial getBMaterial() {
		return this.BMaterial;
	}

	public void setBMaterial(BMaterial BMaterial) {
		this.BMaterial = BMaterial;
	}

	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}