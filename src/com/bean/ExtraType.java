package com.bean;

public class ExtraType {
	private Integer mtlType;
	private Integer pid;
	private String mtlTypeName;
	private String ptypeName;
	
	public ExtraType() {
		super();
	}

	public ExtraType(Integer mtlType, Integer pid, String mtlTypeName,
			String ptypeName) {
		super();
		this.mtlType = mtlType;
		this.pid = pid;
		this.mtlTypeName = mtlTypeName;
		this.ptypeName = ptypeName;
	}

	public Integer getMtlType() {
		return mtlType;
	}
	
	public void setMtlType(Integer mtlType) {
		this.mtlType = mtlType;
	}
	
	public Integer getPid() {
		return pid;
	}
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	public String getMtlTypeName() {
		return mtlTypeName;
	}
	
	public void setMtlTypeName(String mtlTypeName) {
		this.mtlTypeName = mtlTypeName;
	}
	
	public String getPtypeName() {
		return ptypeName;
	}
	
	public void setPtypeName(String ptypeName) {
		this.ptypeName = ptypeName;
	}
	
}
