package com.bean;

/**
 * BMaterialImage entity. @author MyEclipse Persistence Tools
 */

public class BMaterialImage implements java.io.Serializable {

	// Fields

	private Integer imgId;
	private BMaterialImageType bmaterialImageType;
	private BMaterial bmaterial;
	private String imgTitle;
	private String imgDesciption;
	private String imgUrl;
	private Double imgSizeW;
	private Double imgSizeH;
	private String imgColor;

	// Constructors

	/** default constructor */
	public BMaterialImage() {
	}

	public BMaterialImage(Integer imgId, BMaterialImageType bmaterialImageType,
			BMaterial bmaterial, String imgTitle, String imgDesciption,
			String imgUrl, Double imgSizeW, Double imgSizeH, String imgColor) {
		super();
		this.imgId = imgId;
		this.bmaterialImageType = bmaterialImageType;
		this.bmaterial = bmaterial;
		this.imgTitle = imgTitle;
		this.imgDesciption = imgDesciption;
		this.imgUrl = imgUrl;
		this.imgSizeW = imgSizeW;
		this.imgSizeH = imgSizeH;
		this.imgColor = imgColor;
	}




	/** minimal constructor */
	public BMaterialImage(Integer imgId) {
		this.imgId = imgId;
	}

	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public BMaterialImageType getBmaterialImageType() {
		return bmaterialImageType;
	}

	public void setBmaterialImageType(BMaterialImageType bmaterialImageType) {
		this.bmaterialImageType = bmaterialImageType;
	}

	public BMaterial getBmaterial() {
		return bmaterial;
	}

	public void setBmaterial(BMaterial bmaterial) {
		this.bmaterial = bmaterial;
	}

	public String getImgTitle() {
		return imgTitle;
	}

	public void setImgTitle(String imgTitle) {
		this.imgTitle = imgTitle;
	}

	public String getImgDesciption() {
		return imgDesciption;
	}

	public void setImgDesciption(String imgDesciption) {
		this.imgDesciption = imgDesciption;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Double getImgSizeW() {
		return imgSizeW;
	}

	public void setImgSizeW(Double imgSizeW) {
		this.imgSizeW = imgSizeW;
	}

	public Double getImgSizeH() {
		return imgSizeH;
	}

	public void setImgSizeH(Double imgSizeH) {
		this.imgSizeH = imgSizeH;
	}

	public String getImgColor() {
		return imgColor;
	}

	public void setImgColor(String imgColor) {
		this.imgColor = imgColor;
	}
	

}