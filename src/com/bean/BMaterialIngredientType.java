package com.bean;

/**
 * BMaterialIngredientType entity. @author MyEclipse Persistence Tools
 */

public class BMaterialIngredientType implements java.io.Serializable {

	// Fields

	private Integer id;
	private String ingredientType;

	// Constructors

	/** default constructor */
	public BMaterialIngredientType() {
	}

	/** minimal constructor */
	public BMaterialIngredientType(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public BMaterialIngredientType(Integer id, String ingredientType) {
		this.id = id;
		this.ingredientType = ingredientType;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIngredientType() {
		return this.ingredientType;
	}

	public void setIngredientType(String ingredientType) {
		this.ingredientType = ingredientType;
	}

}