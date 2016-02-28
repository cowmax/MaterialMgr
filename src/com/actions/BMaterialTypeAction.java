package com.actions;

import com.bean.BMaterialType;
import com.opensymphony.xwork2.ActionSupport;
import com.serviceImpl.BMaterialTypeServiceImpl;

public class BMaterialTypeAction extends ActionSupport {
	private BMaterialTypeServiceImpl bmaterialTypeBiz;
	private String typeName;
	private Integer pid;
	private boolean flag;

	public BMaterialTypeServiceImpl getBmaterialTypeBiz() {
		return bmaterialTypeBiz;
	}

	public void setBmaterialTypeBiz(BMaterialTypeServiceImpl bmaterialTypeBiz) {
		this.bmaterialTypeBiz = bmaterialTypeBiz;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	/**
	 * 添加面料类型
	 * @return
	 */
	public String addType(){
		try {
			if(pid==null){
				pid = 0;
			}
			BMaterialType bmType = new BMaterialType(pid,typeName);
			bmaterialTypeBiz.saveType(bmType);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return SUCCESS;
	}
}
