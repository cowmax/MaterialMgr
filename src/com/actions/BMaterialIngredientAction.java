package com.actions;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.bean.BMaterialIngredient;
import com.bean.BMaterialIngredientType;
import com.dao.BMaterialIngredientTypeDao;
import com.opensymphony.xwork2.ActionSupport;
import com.serviceImpl.BMaterialIngredientServiceImpl;
import com.serviceImpl.BMaterialIngredientTypeServiceImpl;


/**
 * 照片类型action类
 */
public class BMaterialIngredientAction extends ActionSupport {
	
	private BMaterialIngredientServiceImpl ingredientServiceImpl;//封装成分service
	private BMaterialIngredientTypeServiceImpl bmaterialIngredientTypeBiz;
	private String ingTypeJson;
	
	private List<BMaterialIngredient>  ingredientList; //成分集合封装
	private BMaterialIngredient bmaterialIngredient;//成分封装
	private Integer mtlId;//面料id
	private Boolean flag;//json
	private Integer id;//成分
	private String IngredBuf;//拼接json
	private List<BMaterialIngredientType> allIngredientTypes;
	
	public BMaterialIngredientAction() {
		ingredientList=new ArrayList<BMaterialIngredient>();
		allIngredientTypes = new ArrayList<BMaterialIngredientType>();
	}

	//get、set

	public BMaterialIngredient getBmaterialIngredient() {
		return bmaterialIngredient;
	}

	public void setBmaterialIngredient(BMaterialIngredient bmaterialIngredient) {
		this.bmaterialIngredient = bmaterialIngredient;
	}

	public BMaterialIngredientTypeServiceImpl getBmaterialIngredientTypeBiz() {
		return bmaterialIngredientTypeBiz;
	}

	public void setBmaterialIngredientTypeBiz(
			BMaterialIngredientTypeServiceImpl bmaterialIngredientTypeBiz) {
		this.bmaterialIngredientTypeBiz = bmaterialIngredientTypeBiz;
	}

	public List<BMaterialIngredientType> getAllIngredientTypes() {
		return allIngredientTypes;
	}

	public void setAllIngredientTypes(
			List<BMaterialIngredientType> allIngredientTypes) {
		this.allIngredientTypes = allIngredientTypes;
	}

	public List<BMaterialIngredient> getIngredientList() {
		return ingredientList;
	}

	public void setIngredientList(List<BMaterialIngredient> ingredientList) {
		this.ingredientList = ingredientList;
	}
	
	public Integer getMtlId() {
		return mtlId;
	}

	public void setMtlId(Integer mtlId) {
		this.mtlId = mtlId;
	}

	public BMaterialIngredientServiceImpl getIngredientServiceImpl() {
		return ingredientServiceImpl;
	}

	public void setIngredientServiceImpl(
			BMaterialIngredientServiceImpl ingredientServiceImpl) {
		this.ingredientServiceImpl = ingredientServiceImpl;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIngredBuf() {
		return IngredBuf;
	}

	public void setIngredBuf(String ingredBuf) {
		IngredBuf = ingredBuf;
	}

	public String getIngTypeJson() {
		return ingTypeJson;
	}

	public void setIngTypeJson(String ingTypeJson) {
		this.ingTypeJson = ingTypeJson;
	}

	/**
	 * 删除成分
	 */
	public String delIngdt(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//接收页面传过来的值
		String var=request.getParameter("id");
		//转成int类型
		 id=Integer.parseInt(var);
		
		flag=ingredientServiceImpl.delIngredient(id);
		
		return "del";
	}
	
	
	/**
	 * 添加面料成分
	 */
	public String addIngDt(){
		
		flag=ingredientServiceImpl.addIngredient(bmaterialIngredient);
	
	    return "savaInd";
	}
	
	/**
	 * 显示成分
	 */
	public String showIngredient() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		
		allIngredientTypes = bmaterialIngredientTypeBiz.findAllIngredientType();
//		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(allIngredientTypes);
//		ingTypeJson = jsonArray.toString();
		//接收mtlid
		String md=request.getParameter("mtlId");
		mtlId=Integer.valueOf(md);
		
		//查询所有成分
	    ingredientList=ingredientServiceImpl.getIngredient(mtlId);
		
	    //回传给页面
	    request.setAttribute("mtlId", mtlId);
	   
	    return "show";
	}
	
	/**
	 * 面料成分详情
	 * @return
	 * @throws Exception
	 */
	public String detailIngredient() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		
		//接收mtlid
		String md=request.getParameter("mtlId");
		mtlId=Integer.valueOf(md);
		
		//查询所有成分
	    ingredientList=ingredientServiceImpl.getIngredient(mtlId);
		
	    //回传给页面
	    request.setAttribute("mtlId", mtlId);
	   
	    return "detailIngredient";
	}
	
	
	/**
	 * 拼接
	 */
	public String materialShow(){
		//创建StringBuffer	
		StringBuffer brandBuf = new StringBuffer();

		for (int i = 0; i < ingredientList.size(); i++) {
			
			String name=ingredientList.get(i).getIngredientName();
			String prec=ingredientList.get(i).getPrecent()*100+"%";
			
			//拼接
			String ingred=name+" "+prec;
			brandBuf.append(ingred);
			if(i!=ingredientList.size()-1){
				brandBuf.append("，");
			}
		}

		IngredBuf=brandBuf.toString();
		
		return "ingredList";
	}

	/**
	 * 加载成分信息
	 * @return
	 */
//	public String getLoadIngredientTypes(){
//		allIngredientTypes = bmaterialIngredientTypeBiz.findAllIngredientType();
//		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(allIngredientTypes);
//		ingTypeJson = jsonArray.toString();
//		return "loadIngredientTypes";
//	}
		
}
