package com.bean;

import java.sql.Timestamp;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */

public class Product implements java.io.Serializable {

	// Fields

	private Integer productId;
	private String productCode;	//大货款号
	private String stid;		//款式登记单号
	private String stno;		//设计款号
	private String inty;		//商品类别编号
	private String tyna;		//商品类别名称
	private String syea;		//年份
	private String coloId;		//颜色id
	private String colo;		//颜色编号
	private String cona;		//颜色名称
	private String szid;		//尺码
	private String szco;		//尺码编号
	private String cpco;		//成品代码
	private String sts;			//状态(A:有效/T：注销)
	private Timestamp createDate;	//创建日期
	private Timestamp modifyDate;
	private Timestamp sysDate;
	private String sourceBiid;		//来源生产订单号
	private String productDesc;		//规格代码
	private String brad;		//品牌
	private String spno;		//属性编号
	private String rema;		//备注
	private String edus;
	private String edna;
	private Timestamp eddt;
	private Timestamp jhdt;
	private Timestamp gfdt;
	private Timestamp xjdt;

	// Constructors

	/** default constructor */
	public Product() {
	}

	/** minimal constructor */
	public Product(Integer productId) {
		this.productId = productId;
	}

	/** full constructor */
	public Product(Integer productId, String productCode, String stid,
			String stno, String inty, String tyna, String syea, String coloId,
			String colo, String cona, String szid, String szco, String cpco,
			String sts, Timestamp createDate, Timestamp modifyDate,
			Timestamp sysDate, String sourceBiid, String productDesc,
			String brad, String spno, String rema, String edus, String edna,
			Timestamp eddt, Timestamp jhdt, Timestamp gfdt, Timestamp xjdt) {
		this.productId = productId;
		this.productCode = productCode;
		this.stid = stid;
		this.stno = stno;
		this.inty = inty;
		this.tyna = tyna;
		this.syea = syea;
		this.coloId = coloId;
		this.colo = colo;
		this.cona = cona;
		this.szid = szid;
		this.szco = szco;
		this.cpco = cpco;
		this.sts = sts;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.sysDate = sysDate;
		this.sourceBiid = sourceBiid;
		this.productDesc = productDesc;
		this.brad = brad;
		this.spno = spno;
		this.rema = rema;
		this.edus = edus;
		this.edna = edna;
		this.eddt = eddt;
		this.jhdt = jhdt;
		this.gfdt = gfdt;
		this.xjdt = xjdt;
	}

	// Property accessors

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getStid() {
		return this.stid;
	}

	public void setStid(String stid) {
		this.stid = stid;
	}

	public String getStno() {
		return this.stno;
	}

	public void setStno(String stno) {
		this.stno = stno;
	}

	public String getInty() {
		return this.inty;
	}

	public void setInty(String inty) {
		this.inty = inty;
	}

	public String getTyna() {
		return this.tyna;
	}

	public void setTyna(String tyna) {
		this.tyna = tyna;
	}

	public String getSyea() {
		return this.syea;
	}

	public void setSyea(String syea) {
		this.syea = syea;
	}

	public String getColoId() {
		return this.coloId;
	}

	public void setColoId(String coloId) {
		this.coloId = coloId;
	}

	public String getColo() {
		return this.colo;
	}

	public void setColo(String colo) {
		this.colo = colo;
	}

	public String getCona() {
		return this.cona;
	}

	public void setCona(String cona) {
		this.cona = cona;
	}

	public String getSzid() {
		return this.szid;
	}

	public void setSzid(String szid) {
		this.szid = szid;
	}

	public String getSzco() {
		return this.szco;
	}

	public void setSzco(String szco) {
		this.szco = szco;
	}

	public String getCpco() {
		return this.cpco;
	}

	public void setCpco(String cpco) {
		this.cpco = cpco;
	}

	public String getSts() {
		return this.sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Timestamp getSysDate() {
		return this.sysDate;
	}

	public void setSysDate(Timestamp sysDate) {
		this.sysDate = sysDate;
	}

	public String getSourceBiid() {
		return this.sourceBiid;
	}

	public void setSourceBiid(String sourceBiid) {
		this.sourceBiid = sourceBiid;
	}

	public String getProductDesc() {
		return this.productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public String getBrad() {
		return this.brad;
	}

	public void setBrad(String brad) {
		this.brad = brad;
	}

	public String getSpno() {
		return this.spno;
	}

	public void setSpno(String spno) {
		this.spno = spno;
	}

	public String getRema() {
		return this.rema;
	}

	public void setRema(String rema) {
		this.rema = rema;
	}

	public String getEdus() {
		return this.edus;
	}

	public void setEdus(String edus) {
		this.edus = edus;
	}

	public String getEdna() {
		return this.edna;
	}

	public void setEdna(String edna) {
		this.edna = edna;
	}

	public Timestamp getEddt() {
		return this.eddt;
	}

	public void setEddt(Timestamp eddt) {
		this.eddt = eddt;
	}

	public Timestamp getJhdt() {
		return this.jhdt;
	}

	public void setJhdt(Timestamp jhdt) {
		this.jhdt = jhdt;
	}

	public Timestamp getGfdt() {
		return this.gfdt;
	}

	public void setGfdt(Timestamp gfdt) {
		this.gfdt = gfdt;
	}

	public Timestamp getXjdt() {
		return this.xjdt;
	}

	public void setXjdt(Timestamp xjdt) {
		this.xjdt = xjdt;
	}

}