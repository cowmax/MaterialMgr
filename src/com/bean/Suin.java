package com.bean;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * Suin entity. @author MyEclipse Persistence Tools
 */

public class Suin implements java.io.Serializable {

	// Fields

	private String suid;
	private Integer id;
	private String psid;
	private String ceve;
	private String suna;
	private String stds;
	private String stco;
	private String geid;
	private String psad;
	private String pmad;
	private String whad;
	private String cous;
	private String post;
	private String tel1;
	private String tel2;
	private String fax;
	private String phon;
	private String emai;
	private String pmfl;
	private Boolean ifcn;
	private String cnty;
	private Double poit;
	private String acmt;
	private String pamt;
	private String bkna;
	private String ckna;
	private String ckno;
	private String pydt;
	private String sdco;
	private String crus;
	private String crna;
	private Timestamp crdt;
	private String edus;
	private String edna;
	private Timestamp eddt;
	private String cori;
	private String orid;
	private Integer ifus;
	private String isym;
	private String stat;
	private String rema;
	private String erpc;
	private Boolean ifcd;
	private String bkpt;
	private String cpco;
	private String yfcd;
	private Integer suNum;
	private String htco;

	// Constructors

	/** default constructor */
	public Suin() {
	}

	/** minimal constructor */
	public Suin(String suid, Integer id) {
		this.suid = suid;
		this.id = id;
	}
	
	public Suin(String suid, String suna) {
		this.suid = suid;
		this.suna = suna;
	}

	/** full constructor */
	public Suin(String suid, Integer id, String psid, String ceve, String suna,
			String stds, String stco, String geid, String psad, String pmad,
			String whad, String cous, String post, String tel1, String tel2,
			String fax, String phon, String emai, String pmfl, Boolean ifcn,
			String cnty, Double poit, String acmt, String pamt, String bkna,
			String ckna, String ckno, String pydt, String sdco, String crus,
			String crna, Timestamp crdt, String edus, String edna,
			Timestamp eddt, String cori, String orid, Integer ifus,
			String isym, String stat, String rema, String erpc, Boolean ifcd,
			String bkpt, String cpco, String yfcd, Integer suNum, String htco) 
	{
		this.suid = suid;
		this.id = id;
		this.psid = psid;
		this.ceve = ceve;
		this.suna = suna;
		this.stds = stds;
		this.stco = stco;
		this.geid = geid;
		this.psad = psad;
		this.pmad = pmad;
		this.whad = whad;
		this.cous = cous;
		this.post = post;
		this.tel1 = tel1;
		this.tel2 = tel2;
		this.fax = fax;
		this.phon = phon;
		this.emai = emai;
		this.pmfl = pmfl;
		this.ifcn = ifcn;
		this.cnty = cnty;
		this.poit = poit;
		this.acmt = acmt;
		this.pamt = pamt;
		this.bkna = bkna;
		this.ckna = ckna;
		this.ckno = ckno;
		this.pydt = pydt;
		this.sdco = sdco;
		this.crus = crus;
		this.crna = crna;
		this.crdt = crdt;
		this.edus = edus;
		this.edna = edna;
		this.eddt = eddt;
		this.cori = cori;
		this.orid = orid;
		this.ifus = ifus;
		this.isym = isym;
		this.stat = stat;
		this.rema = rema;
		this.erpc = erpc;
		this.ifcd = ifcd;
		this.bkpt = bkpt;
		this.cpco = cpco;
		this.yfcd = yfcd;
		this.suNum = suNum;
		this.htco = htco;
	}

	// Property accessors

	public String getSuid() {
		return this.suid;
	}

	public void setSuid(String suid) {
		this.suid = suid;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPsid() {
		return this.psid;
	}

	public void setPsid(String psid) {
		this.psid = psid;
	}

	public String getCeve() {
		return this.ceve;
	}

	public void setCeve(String ceve) {
		this.ceve = ceve;
	}

	public String getSuna() {
		return this.suna;
	}

	public void setSuna(String suna) {
		this.suna = suna;
	}

	public String getStds() {
		return this.stds;
	}

	public void setStds(String stds) {
		this.stds = stds;
	}

	public String getStco() {
		return this.stco;
	}

	public void setStco(String stco) {
		this.stco = stco;
	}

	public String getGeid() {
		return this.geid;
	}

	public void setGeid(String geid) {
		this.geid = geid;
	}

	public String getPsad() {
		return this.psad;
	}

	public void setPsad(String psad) {
		this.psad = psad;
	}

	public String getPmad() {
		return this.pmad;
	}

	public void setPmad(String pmad) {
		this.pmad = pmad;
	}

	public String getWhad() {
		return this.whad;
	}

	public void setWhad(String whad) {
		this.whad = whad;
	}

	public String getCous() {
		return this.cous;
	}

	public void setCous(String cous) {
		this.cous = cous;
	}

	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getTel1() {
		return this.tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	public String getTel2() {
		return this.tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPhon() {
		return this.phon;
	}

	public void setPhon(String phon) {
		this.phon = phon;
	}

	public String getEmai() {
		return this.emai;
	}

	public void setEmai(String emai) {
		this.emai = emai;
	}

	public String getPmfl() {
		return this.pmfl;
	}

	public void setPmfl(String pmfl) {
		this.pmfl = pmfl;
	}

	public Boolean getIfcn() {
		return this.ifcn;
	}

	public void setIfcn(Boolean ifcn) {
		this.ifcn = ifcn;
	}

	public String getCnty() {
		return this.cnty;
	}

	public void setCnty(String cnty) {
		this.cnty = cnty;
	}

	public Double getPoit() {
		return this.poit;
	}

	public void setPoit(Double poit) {
		this.poit = poit;
	}

	public String getAcmt() {
		return this.acmt;
	}

	public void setAcmt(String acmt) {
		this.acmt = acmt;
	}

	public String getPamt() {
		return this.pamt;
	}

	public void setPamt(String pamt) {
		this.pamt = pamt;
	}

	public String getBkna() {
		return this.bkna;
	}

	public void setBkna(String bkna) {
		this.bkna = bkna;
	}

	public String getCkna() {
		return this.ckna;
	}

	public void setCkna(String ckna) {
		this.ckna = ckna;
	}

	public String getCkno() {
		return this.ckno;
	}

	public void setCkno(String ckno) {
		this.ckno = ckno;
	}

	public String getPydt() {
		return this.pydt;
	}

	public void setPydt(String pydt) {
		this.pydt = pydt;
	}

	public String getSdco() {
		return this.sdco;
	}

	public void setSdco(String sdco) {
		this.sdco = sdco;
	}

	public String getCrus() {
		return this.crus;
	}

	public void setCrus(String crus) {
		this.crus = crus;
	}

	public String getCrna() {
		return this.crna;
	}

	public void setCrna(String crna) {
		this.crna = crna;
	}

	public Timestamp getCrdt() {
		return this.crdt;
	}

	public void setCrdt(Timestamp crdt) {
		this.crdt = crdt;
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

	public String getCori() {
		return this.cori;
	}

	public void setCori(String cori) {
		this.cori = cori;
	}

	public String getOrid() {
		return this.orid;
	}

	public void setOrid(String orid) {
		this.orid = orid;
	}

	public Integer getIfus() {
		return this.ifus;
	}

	public void setIfus(Integer ifus) {
		this.ifus = ifus;
	}

	public String getIsym() {
		return this.isym;
	}

	public void setIsym(String isym) {
		this.isym = isym;
	}

	public String getStat() {
		return this.stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getRema() {
		return this.rema;
	}

	public void setRema(String rema) {
		this.rema = rema;
	}

	public String getErpc() {
		return this.erpc;
	}

	public void setErpc(String erpc) {
		this.erpc = erpc;
	}

	public Boolean getIfcd() {
		return this.ifcd;
	}

	public void setIfcd(Boolean ifcd) {
		this.ifcd = ifcd;
	}

	public String getBkpt() {
		return this.bkpt;
	}

	public void setBkpt(String bkpt) {
		this.bkpt = bkpt;
	}

	public String getCpco() {
		return this.cpco;
	}

	public void setCpco(String cpco) {
		this.cpco = cpco;
	}

	public String getYfcd() {
		return this.yfcd;
	}

	public void setYfcd(String yfcd) {
		this.yfcd = yfcd;
	}

	public Integer getSuNum() {
		return this.suNum;
	}

	public void setSuNum(Integer suNum) {
		this.suNum = suNum;
	}

	public String getHtco() {
		return this.htco;
	}

	public void setHtco(String htco) {
		this.htco = htco;
	}

}