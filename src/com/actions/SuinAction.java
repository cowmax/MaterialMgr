package com.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.bean.BMaterial;
import com.bean.BMaterialSupplier;
import com.bean.Suin;
import com.opensymphony.xwork2.ActionSupport;
import com.serviceImpl.SuinServiceImpl;
import com.serviceImpl.UtilSupport;

public class SuinAction extends ActionSupport {

	private SuinServiceImpl suinBiz;
	private List<Suin> allSuinList;
	private UtilSupport util;		//公用类
	private Suin newSuin;
	private String suid;
	private boolean flag;
	
	private int offset;			//当前页
	private int pageSize=10;	//每页显示条数
	private int totalcount;		// 总记录数
	private int totalpage; 		// 总页数
	
	//查询条件
	private String qsuid;	//供应商编码
	private String qsuna;	//供应商全称
	private String qpsad;	//营业部地址
	private String qpmad;	//工厂地址
	private String qwhad;	//仓库地址
	private String qcous;	//联系人
	private String qtel;	//联系电话
	private String qrema;	//备注
	
	private String refreshList;//封装刷新的页面
	private String titleName;//封装title名字
	private String msg;//封装jsp提示语

	public SuinAction() {
		allSuinList = new ArrayList<Suin>();
	}

	public SuinServiceImpl getSuinBiz() {
		return suinBiz;
	}

	public void setSuinBiz(SuinServiceImpl suinBiz) {
		this.suinBiz = suinBiz;
	}

	public List<Suin> getAllSuinList() {
		return allSuinList;
	}

	public void setAllSuinList(List<Suin> allSuinList) {
		this.allSuinList = allSuinList;
	}
	
	public UtilSupport getUtil() {
		return util;
	}

	public void setUtil(UtilSupport util) {
		this.util = util;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public String getQsuid() {
		return qsuid;
	}

	public void setQsuid(String qsuid) {
		this.qsuid = qsuid;
	}

	public String getQsuna() {
		return qsuna;
	}

	public void setQsuna(String qsuna) {
		this.qsuna = qsuna;
	}

	public String getQpsad() {
		return qpsad;
	}

	public void setQpsad(String qpsad) {
		this.qpsad = qpsad;
	}

	public String getQpmad() {
		return qpmad;
	}

	public void setQpmad(String qpmad) {
		this.qpmad = qpmad;
	}

	public String getQwhad() {
		return qwhad;
	}

	public void setQwhad(String qwhad) {
		this.qwhad = qwhad;
	}

	public String getQcous() {
		return qcous;
	}

	public void setQcous(String qcous) {
		this.qcous = qcous;
	}

	public String getQtel() {
		return qtel;
	}

	public void setQtel(String qtel) {
		this.qtel = qtel;
	}

	public String getQrema() {
		return qrema;
	}

	public void setQrema(String qrema) {
		this.qrema = qrema;
	}

	public Suin getNewSuin() {
		return newSuin;
	}

	public void setNewSuin(Suin newSuin) {
		this.newSuin = newSuin;
	}

	public String getRefreshList() {
		return refreshList;
	}

	public void setRefreshList(String refreshList) {
		this.refreshList = refreshList;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSuid() {
		return suid;
	}

	public void setSuid(String suid) {
		this.suid = suid;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	/**
	 * 供应商添加操作
	 * @return
	 */
	public String addSupOperation(){
		allSuinList.clear();
		allSuinList = suinBiz.getAllSuin();
		return "addSupOpt";
	}
	
	// Added by JSL : 获取翻页偏移量(实际上是将要翻到的页面的页索引，页索引从 0 开始)
	private int getPageOffset() {
		HttpServletRequest request=ServletActionContext.getRequest();
		String ofst = request.getParameter("offset");
		int idx = 0;
		if(ofst!=null){
			idx = Integer.valueOf(ofst);
			idx = idx < 0 ? 0 : idx;                        // 超过第一页时，不再翻页
			idx = idx >= totalpage ? (totalpage-1) : idx;	// 超过最后一页时，不再翻页		
		}
		return idx;
	}

	/**
	 * 获取所有供应商信息
	 * @return
	 */
	public String getAllSuins(){
		HttpServletRequest request = ServletActionContext.getRequest();
		try {

			StringBuffer sql=new StringBuffer("select * from suin where stco = '01' ");

			this.qsuid = request.getParameter("qsuid");
			if(qsuid != null&&!qsuid.isEmpty()){
				qsuid=new String(qsuid.trim().getBytes("ISO-8859-1"),"UTF-8");
				sql.append(" and suid like '%"+qsuid+"%'");
			}
			
			this.qsuna = request.getParameter("qsuna");
			if(qsuna != null&&!qsuna.isEmpty()){
				qsuna=new String(qsuna.trim().getBytes("ISO-8859-1"),"UTF-8");
				sql.append(" and suna like '%"+qsuna+"%'");
			}
			
			this.qpsad = request.getParameter("qpsad");
			if(qpsad != null&&!qpsad.isEmpty()){
				qsuid=new String(qpsad.trim().getBytes("ISO-8859-1"),"UTF-8");
				sql.append(" and psad like '%"+qpsad+"%'");
			}
			
			this.qpmad = request.getParameter("qpmad");
			if(qpmad != null&&!qpmad.isEmpty()){
				qpmad=new String(qpmad.trim().getBytes("ISO-8859-1"),"UTF-8");
				sql.append(" and pmad like '%"+qpmad+"%'");
			}
			
			this.qwhad = request.getParameter("qwhad");
			if(qwhad != null&&!qwhad.isEmpty()){
				qwhad=new String(qwhad.trim().getBytes("ISO-8859-1"),"UTF-8");
				sql.append(" and whad like '%"+qwhad+"%'");
			}

			this.qcous = request.getParameter("qcous");
			if(qcous != null&&!qcous.isEmpty()){
				qcous=new String(qcous.trim().getBytes("ISO-8859-1"),"UTF-8");
				sql.append(" and cous like '%"+qcous+"%'");
			}
			
			this.qtel = request.getParameter("qtel");
			if(qtel != null&&!qtel.isEmpty()){
				qtel=new String(qtel.trim().getBytes("ISO-8859-1"),"UTF-8");
				sql.append(" and tel1 like '%"+qtel+"%'");
			}
			
			this.qrema = request.getParameter("qrema");
			if(qrema != null&&!qrema.isEmpty()){
				qrema=new String(qrema.trim().getBytes("ISO-8859-1"),"UTF-8");
				sql.append(" and rema like '%"+qrema+"%'");
			}
			
			totalcount = util.getTotalCount(sql.toString());		//获取总条数

			totalpage = totalcount % pageSize == 0 ? totalcount / pageSize 
					: totalcount / pageSize + 1;		//获取总页数

			offset = getPageOffset();

			allSuinList = util.getPageListBySql(sql.toString(),String.valueOf(offset),String.valueOf(pageSize),
					new Class[]{Suin.class});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "allSuin";
	}
	
	/**
	 * 添加供应商信息
	 * @return
	 */
	public String addSuidInfo(){
		String newSuid = suinBiz.getNewSuid();
		newSuin.setSuid(newSuid);
		newSuin.setStco("01");
		suinBiz.saveSuin(newSuin);
		
		refreshList = "suingetAllSuins";
		titleName = "查询供应商信息";
		msg = "供应商   【 "+newSuin.getSuna()+" 】 ";
		return "addSuid";
	}
	
	/**
	 * 删除供应商信息
	 * @return
	 */
	public String delSuinInfo(){
		try {
			suinBiz.delSuin(suid);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return SUCCESS;
	}
	
	/**
	 * 修改供应商操作
	 * @return
	 */
	public String mergeSuinOpr(){
		newSuin = suinBiz.findSuinBySuid(suid);
		return "mergeOpr";
	}
	
	/**
	 * 修改供应商信息
	 * @return
	 */
	public String mergeSuinInfo(){
		suinBiz.mergeSuin(newSuin);
		refreshList = "suingetAllSuins";
		titleName = "查询供应商信息";
		msg = "供应商   【 "+newSuin.getSuna()+" 】 ";
		return "addSuid";
	}
}
