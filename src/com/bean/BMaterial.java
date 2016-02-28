package com.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * BMaterial entity. @author MyEclipse Persistence Tools
 */

public class BMaterial implements java.io.Serializable {

	// Fields

	private Integer mtlId;
	private BMaterialType BMaterialType;
	private String mtlName;		//面料名称
	private Double mtlPrice;	//单价
	private Integer colorCount;	//颜色数量
	private String season;		//季节
	private Double width;		//幅宽
	private Double weigth;		//克重
	private Double shrinkW;		//横向收缩率
	private Double shrinkH;		//纵向收缩率
	private Integer amount;			//库存
	private Integer minOrder;		//最小起订量
	private Integer prdCycle;		//生产周期
	private Date sysDt;		//操作时间
	private String sysUser;	//操作用户
	private Date createDt;	//创建时间
	private Double mtlNtxPrice;		//不含税价
	private String mtlCode;		//面料编码
	private String mtlUnit;		//面料单位
	private Integer status;	//面料信息状态
	private Set BMaterialImages = new HashSet(0);
	private Set BMaterialRelateProducts = new HashSet(0);
	private Set BMaterialBrands = new HashSet(0);
	private Set BMaterialProps = new HashSet(0);
	private Set BMaterialIngredients = new HashSet(0);
	private Set BMaterialSuppliers = new HashSet(0);
	private Set BMaterialFavorDetails = new HashSet(0);
	private Set BMaterialTestReports = new HashSet(0);
	private String riskSelfAssessment;
	private String mtlOldCode;
	private String mtlMemo;

	// Constructors

	/** default constructor */
	public BMaterial() {
	}

	/** minimal constructor */
	public BMaterial(Integer mtlId) {
		this.mtlId = mtlId;
	}

	public BMaterial(String mtlCode) {
		this.mtlCode = mtlCode;
	}

	public BMaterial(Integer mtlId, com.bean.BMaterialType bMaterialType,
			String mtlName, Double mtlPrice, Integer colorCount, String season,
			Double width, Double weigth, Double shrinkW, Double shrinkH,
			Integer amount, Integer minOrder, Integer prdCycle, Date sysDt,
			String sysUser, Date createDt, Double mtlNtxPrice, String mtlCode,
			String mtlUnit, Integer status, Set bMaterialImages,
			Set bMaterialRelateProducts, Set bMaterialBrands,
			Set bMaterialProps, Set bMaterialIngredients,
			Set bMaterialSuppliers, Set bMaterialFavorDetails,
			Set bMaterialTestReports, String riskSelfAssessment,
			String mtlOldCode, String mtlMemo) {
		super();
		this.mtlId = mtlId;
		BMaterialType = bMaterialType;
		this.mtlName = mtlName;
		this.mtlPrice = mtlPrice;
		this.colorCount = colorCount;
		this.season = season;
		this.width = width;
		this.weigth = weigth;
		this.shrinkW = shrinkW;
		this.shrinkH = shrinkH;
		this.amount = amount;
		this.minOrder = minOrder;
		this.prdCycle = prdCycle;
		this.sysDt = sysDt;
		this.sysUser = sysUser;
		this.createDt = createDt;
		this.mtlNtxPrice = mtlNtxPrice;
		this.mtlCode = mtlCode;
		this.mtlUnit = mtlUnit;
		this.status = status;
		BMaterialImages = bMaterialImages;
		BMaterialRelateProducts = bMaterialRelateProducts;
		BMaterialBrands = bMaterialBrands;
		BMaterialProps = bMaterialProps;
		BMaterialIngredients = bMaterialIngredients;
		BMaterialSuppliers = bMaterialSuppliers;
		BMaterialFavorDetails = bMaterialFavorDetails;
		BMaterialTestReports = bMaterialTestReports;
		this.riskSelfAssessment = riskSelfAssessment;
		this.mtlOldCode = mtlOldCode;
		this.mtlMemo = mtlMemo;
	}

	public Integer getMtlId() {
		return mtlId;
	}

	public void setMtlId(Integer mtlId) {
		this.mtlId = mtlId;
	}

	public BMaterialType getBMaterialType() {
		return BMaterialType;
	}

	public void setBMaterialType(BMaterialType bMaterialType) {
		BMaterialType = bMaterialType;
	}

	public String getMtlName() {
		return mtlName;
	}

	public void setMtlName(String mtlName) {
		this.mtlName = mtlName;
	}

	public Double getMtlPrice() {
		return mtlPrice;
	}

	public void setMtlPrice(Double mtlPrice) {
		this.mtlPrice = mtlPrice;
	}

	public Integer getColorCount() {
		return colorCount;
	}

	public void setColorCount(Integer colorCount) {
		this.colorCount = colorCount;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getWeigth() {
		return weigth;
	}

	public void setWeigth(Double weigth) {
		this.weigth = weigth;
	}

	public Double getShrinkW() {
		return shrinkW;
	}

	public void setShrinkW(Double shrinkW) {
		this.shrinkW = shrinkW;
	}

	public Double getShrinkH() {
		return shrinkH;
	}

	public void setShrinkH(Double shrinkH) {
		this.shrinkH = shrinkH;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getMinOrder() {
		return minOrder;
	}

	public void setMinOrder(Integer minOrder) {
		this.minOrder = minOrder;
	}

	public Integer getPrdCycle() {
		return prdCycle;
	}

	public void setPrdCycle(Integer prdCycle) {
		this.prdCycle = prdCycle;
	}

	public Date getSysDt() {
		return sysDt;
	}

	public void setSysDt(Date sysDt) {
		this.sysDt = sysDt;
	}

	public String getSysUser() {
		return sysUser;
	}

	public void setSysUser(String sysUser) {
		this.sysUser = sysUser;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Double getMtlNtxPrice() {
		return mtlNtxPrice;
	}

	public void setMtlNtxPrice(Double mtlNtxPrice) {
		this.mtlNtxPrice = mtlNtxPrice;
	}

	public String getMtlCode() {
		return mtlCode;
	}

	public void setMtlCode(String mtlCode) {
		this.mtlCode = mtlCode;
	}

	public String getMtlUnit() {
		return mtlUnit;
	}

	public void setMtlUnit(String mtlUnit) {
		this.mtlUnit = mtlUnit;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set getBMaterialImages() {
		return BMaterialImages;
	}

	public void setBMaterialImages(Set bMaterialImages) {
		BMaterialImages = bMaterialImages;
	}

	public Set getBMaterialRelateProducts() {
		return BMaterialRelateProducts;
	}

	public void setBMaterialRelateProducts(Set bMaterialRelateProducts) {
		BMaterialRelateProducts = bMaterialRelateProducts;
	}

	public Set getBMaterialBrands() {
		return BMaterialBrands;
	}

	public void setBMaterialBrands(Set bMaterialBrands) {
		BMaterialBrands = bMaterialBrands;
	}

	public Set getBMaterialProps() {
		return BMaterialProps;
	}

	public void setBMaterialProps(Set bMaterialProps) {
		BMaterialProps = bMaterialProps;
	}

	public Set getBMaterialIngredients() {
		return BMaterialIngredients;
	}

	public void setBMaterialIngredients(Set bMaterialIngredients) {
		BMaterialIngredients = bMaterialIngredients;
	}

	public Set getBMaterialSuppliers() {
		return BMaterialSuppliers;
	}

	public void setBMaterialSuppliers(Set bMaterialSuppliers) {
		BMaterialSuppliers = bMaterialSuppliers;
	}

	public Set getBMaterialFavorDetails() {
		return BMaterialFavorDetails;
	}

	public void setBMaterialFavorDetails(Set bMaterialFavorDetails) {
		BMaterialFavorDetails = bMaterialFavorDetails;
	}

	public Set getBMaterialTestReports() {
		return BMaterialTestReports;
	}

	public void setBMaterialTestReports(Set bMaterialTestReports) {
		BMaterialTestReports = bMaterialTestReports;
	}

	public String getRiskSelfAssessment() {
		return riskSelfAssessment;
	}

	public void setRiskSelfAssessment(String riskSelfAssessment) {
		this.riskSelfAssessment = riskSelfAssessment;
	}

	public String getMtlOldCode() {
		return mtlOldCode;
	}

	public void setMtlOldCode(String mtlOldCode) {
		this.mtlOldCode = mtlOldCode;
	}

	public String getMtlMemo() {
		return mtlMemo;
	}

	public void setMtlMemo(String mtlMemo) {
		this.mtlMemo = mtlMemo;
	}

}