package com.bean;



/**
 * BMaterialIngredient entity. @author MyEclipse Persistence Tools
 */

public class BMaterialIngredient  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private BMaterial bmaterial;
     private String ingredientName;
     private Double precent;


    // Constructors

  


	public BMaterialIngredient(Integer id, BMaterial bmaterial,
			String ingredientName, Double precent) {
		super();
		this.id = id;
		this.bmaterial = bmaterial;
		this.ingredientName = ingredientName;
		this.precent = precent;
	}


	public BMaterialIngredient() {
		
	}


	/** minimal constructor */
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public BMaterial getBmaterial() {
		return bmaterial;
	}


	public void setBmaterial(BMaterial bmaterial) {
		this.bmaterial = bmaterial;
	}


	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public Double getPrecent() {
		return precent;
	}

	public void setPrecent(Double precent) {
		this.precent = precent;
	}


}