package com.bean;

public class ExtraImage {
	
	private String imgUrl;
	private String imgColor;
	private Integer imgId;
	private Integer mtlId;

	public ExtraImage() {
		super();
	}
	
	public ExtraImage(String imgUrl, String imgColor, Integer imgId,
			Integer mtlId) {
		super();
		this.imgUrl = imgUrl;
		this.imgColor = imgColor;
		this.imgId = imgId;
		this.mtlId = mtlId;
	}

	public Integer getMtlId() {
		return mtlId;
	}

	public void setMtlId(Integer mtlId) {
		this.mtlId = mtlId;
	}

	public String getImgUrl() {
		return imgUrl;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public String getImgColor() {
		return imgColor;
	}
	
	public void setImgColor(String imgColor) {
		this.imgColor = imgColor;
	}

	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}


}
