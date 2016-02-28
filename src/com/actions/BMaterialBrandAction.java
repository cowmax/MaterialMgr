package com.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.bean.BMaterial;
import com.bean.BMaterialBrand;
import com.bean.ExtraBrand;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionSupport;
import com.serviceImpl.BMaterialBrandServiceImpl;

/**
 * @author Administrator
 *
 */
public class BMaterialBrandAction extends ActionSupport {

	private BMaterialBrandServiceImpl bmaterialBrandBiz;

	private List<ExtraBrand> brandsByMtlIdList;
	private Map<String, Object> jsonResult;
	private List<ExtraBrand> brandList;
	private Integer mtlId;
	private String param;
	private String brandValue;
	private boolean flag;
	private JSONArray brandsJson;

	public BMaterialBrandAction() {
		brandsByMtlIdList = new ArrayList<ExtraBrand>();
		brandList = new ArrayList<ExtraBrand>();
	}

	public BMaterialBrandServiceImpl getBmaterialBrandBiz() {
		return bmaterialBrandBiz;
	}

	public void setBmaterialBrandBiz(BMaterialBrandServiceImpl bmaterialBrandBiz) {
		this.bmaterialBrandBiz = bmaterialBrandBiz;
	}

	public List<ExtraBrand> getBrandsByMtlIdList() {
		return brandsByMtlIdList;
	}

	public void setBrandsByMtlIdList(List<ExtraBrand> brandsByMtlIdList) {
		this.brandsByMtlIdList = brandsByMtlIdList;
	}

	public List<ExtraBrand> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<ExtraBrand> brandList) {
		this.brandList = brandList;
	}

	public Map<String, Object> getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(Map<String, Object> jsonResult) {
		this.jsonResult = jsonResult;
	}

	public Integer getMtlId() {
		return mtlId;
	}

	public void setMtlId(Integer mtlId) {
		this.mtlId = mtlId;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getBrandValue() {
		return brandValue;
	}

	public void setBrandValue(String brandValue) {
		this.brandValue = brandValue;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public JSONArray getBrandsJson() {
		return brandsJson;
	}

	public void setBrandsJson(JSONArray brandsJson) {
		this.brandsJson = brandsJson;
	}

	/**
	 * 添加品牌操作
	 * @return
	 */
	public String addBrandOperation(){
		return "brandOperation";
	}

	/**
	 * 根据面料ID获取品牌信息
	 * @return
	 */
	public String findBrandsByMtlId(){
		brandsByMtlIdList = bmaterialBrandBiz.findBrandsByMtlId(mtlId);
		return "brandsList";
	}

	/**
	 * 保存品牌信息
	 * @return
	 */
	public String saveBrand(){
		try {
			brandList.clear();
			jsonResult = new HashMap<String, Object>();
			Gson gson = new Gson();
			StringBuffer brandBuf = new StringBuffer();

			brandList = gson.fromJson(param, new TypeToken<List<ExtraBrand>>(){}.getType());
			for (int i = 0; i < brandList.size(); i++) {
				ExtraBrand ebrand = brandList.get(i);
				brandBuf.append(ebrand.getBrandName());
				if(i!=brandList.size()-1){
					brandBuf.append(",");
				}
			}

			brandValue = brandBuf.toString();
			jsonResult.put("brandList", brandList);
			jsonResult.put("brandValue", brandValue);

			bmaterialBrandBiz.saveBrand(mtlId,brandValue);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}

		return "addBrand";
	}
	
	/**
	 * 获取产品信息中所有品牌信息
	 * @return
	 */
	public String getAllBrands(){
		List<ExtraBrand> ebrandList = bmaterialBrandBiz.findAllBrands(mtlId);
		brandsJson = JSONArray.fromObject(ebrandList); 
		return "allEbrands";
	}
}
