package com.bean;

public class TypeCombox {

	private Integer id;
	private String text;
	private boolean isChecked;
	
	public TypeCombox() {
		super();
	}

	public TypeCombox(Integer id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public TypeCombox(Integer id, String text, boolean isChecked) {
		super();
		this.id = id;
		this.text = text;
		this.isChecked = isChecked;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
}
