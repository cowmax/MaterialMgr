package com.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * BMaterialImageType entity. @author MyEclipse Persistence Tools
 */

public class BMaterialImageType implements java.io.Serializable {

	// Fields

	private Integer imgType;
	private String imgTypeName;
	private String memo;
	private Set BMaterialImages = new HashSet(0);

	// Constructors

	/** default constructor */
	public BMaterialImageType() {
	}

	/** minimal constructor */
	public BMaterialImageType(Integer imgType) {
		this.imgType = imgType;
	}

	/** full constructor */
	public BMaterialImageType(Integer imgType, String imgTypeName, String memo,
			Set BMaterialImages) {
		this.imgType = imgType;
		this.imgTypeName = imgTypeName;
		this.memo = memo;
		this.BMaterialImages = BMaterialImages;
	}

	// Property accessors

	public Integer getImgType() {
		return this.imgType;
	}

	public void setImgType(Integer imgType) {
		this.imgType = imgType;
	}

	public String getImgTypeName() {
		return this.imgTypeName;
	}

	public void setImgTypeName(String imgTypeName) {
		this.imgTypeName = imgTypeName;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Set getBMaterialImages() {
		return this.BMaterialImages;
	}

	public void setBMaterialImages(Set BMaterialImages) {
		this.BMaterialImages = BMaterialImages;
	}

}