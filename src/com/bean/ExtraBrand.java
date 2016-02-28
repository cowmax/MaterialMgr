package com.bean;

public class ExtraBrand {
	private String brandName;
	private String brand;
	private boolean isChoose;
	
	public ExtraBrand() {
		super();
	}

	public ExtraBrand(String brandName, String brand) {
		super();
		this.brandName = brandName;
		this.brand = brand;
	}

	public ExtraBrand(String brandName, String brand, boolean isChoose) {
		super();
		this.brandName = brandName;
		this.brand = brand;
		this.isChoose = isChoose;
	}

	public String getBrandName() {
		return brandName;
	}
	
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public boolean isChoose() {
		return isChoose;
	}

	public void setChoose(boolean isChoose) {
		this.isChoose = isChoose;
	}

}
