package com.bean;

import java.math.BigDecimal;
import java.util.Date;

public class ExtraBMaterial {

	private Integer mtlId;
	private String mtlName;
	private Integer mtlType;
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
	private Integer status;	//面料信息状态
	private Date createDt;	//创建时间
	private Double mtlNtxPrice;		//不含税价
	private String mtlCode;		//面料编码
	private String mtlUnit;		//面料单位
	private String mtlTypeName;
	private String suna;
	private String imgUrl;
	private String productInfo;
	private String relProduct;
	private String riskSelfAssessment;
	private String mtlOldCode;
	
	public ExtraBMaterial() {
		super();
	}

	public ExtraBMaterial(Integer mtlId, String mtlName, Integer mtlType,
			Double mtlPrice, Integer colorCount, String season, Double width,
			Double weigth, Double shrinkW, Double shrinkH, Integer amount,
			Integer minOrder, Integer prdCycle, Date sysDt, String sysUser,
			Integer status, Date createDt, Double mtlNtxPrice, String mtlCode,
			String mtlUnit, String mtlTypeName, String suna, String imgUrl,
			String productInfo, String relProduct, String riskSelfAssessment,
			String mtlOldCode) {
		super();
		this.mtlId = mtlId;
		this.mtlName = mtlName;
		this.mtlType = mtlType;
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
		this.status = status;
		this.createDt = createDt;
		this.mtlNtxPrice = mtlNtxPrice;
		this.mtlCode = mtlCode;
		this.mtlUnit = mtlUnit;
		this.mtlTypeName = mtlTypeName;
		this.suna = suna;
		this.imgUrl = imgUrl;
		this.productInfo = productInfo;
		this.relProduct = relProduct;
		this.riskSelfAssessment = riskSelfAssessment;
		this.mtlOldCode = mtlOldCode;
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

	public Integer getMtlId() {
		return mtlId;
	}

	public void setMtlId(Integer mtlId) {
		this.mtlId = mtlId;
	}

	public String getMtlName() {
		return mtlName;
	}

	public void setMtlName(String mtlName) {
		this.mtlName = mtlName;
	}

	public Integer getMtlType() {
		return mtlType;
	}

	public void setMtlType(Integer mtlType) {
		this.mtlType = mtlType;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getMtlTypeName() {
		return mtlTypeName;
	}

	public void setMtlTypeName(String mtlTypeName) {
		this.mtlTypeName = mtlTypeName;
	}

	public String getSuna() {
		return suna;
	}

	public void setSuna(String suna) {
		this.suna = suna;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}

	public String getRelProduct() {
		return relProduct;
	}

	public void setRelProduct(String relProduct) {
		this.relProduct = relProduct;
	}
}
