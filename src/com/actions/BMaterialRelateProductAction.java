package com.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.bean.BMaterial;
import com.bean.BMaterialRelateProduct;
import com.opensymphony.xwork2.ActionSupport;
import com.serviceImpl.BMaterialRelateProductServiceImpl;

public class BMaterialRelateProductAction extends ActionSupport {

	private BMaterialRelateProductServiceImpl bmaterialRelateProductBiz;
	private List<BMaterialRelateProduct> bmRelateProducts;	//面料关联产品集合
	private BMaterialRelateProduct relateProduct;	//面料相关联产品
	private Integer mtlId;
	private Integer mrpId;
	
	private String relpduCode;
	private String memo;
	
	private boolean flag;
	private String productCodes;

	public BMaterialRelateProductServiceImpl getBmaterialRelateProductBiz() {
		return bmaterialRelateProductBiz;
	}

	public void setBmaterialRelateProductBiz(
			BMaterialRelateProductServiceImpl bmaterialRelateProductBiz) {
		this.bmaterialRelateProductBiz = bmaterialRelateProductBiz;
	}

	public List<BMaterialRelateProduct> getBmRelateProducts() {
		return bmRelateProducts;
	}

	public void setBmRelateProducts(List<BMaterialRelateProduct> bmRelateProducts) {
		this.bmRelateProducts = bmRelateProducts;
	} 
	
	public String getRelpduCode() {
		return relpduCode;
	}

	public void setRelpduCode(String relpduCode) {
		this.relpduCode = relpduCode;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public BMaterialRelateProduct getRelateProduct() {
		return relateProduct;
	}

	public void setRelateProduct(BMaterialRelateProduct relateProduct) {
		this.relateProduct = relateProduct;
	}

	public Integer getMtlId() {
		return mtlId;
	}

	public void setMtlId(Integer mtlId) {
		this.mtlId = mtlId;
	}

	public Integer getMrpId() {
		return mrpId;
	}

	public void setMrpId(Integer mrpId) {
		this.mrpId = mrpId;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getProductCodes() {
		return productCodes;
	}

	public void setProductCodes(String productCodes) {
		this.productCodes = productCodes;
	}

	/**
	 * 获取面料相关联产品
	 * @return
	 */
	public String getRelateProductS(){
		bmRelateProducts = bmaterialRelateProductBiz.getRelateProducts(mtlId);
		return "show";
	}
	
	/**
	 * 获取面料相关联产品详情
	 * @return
	 */
	public String detailRelateProductS(){
		bmRelateProducts = bmaterialRelateProductBiz.getRelateProducts(mtlId);
		return "detailRelpdu";
	}
	
	/**
	 * 保存相关联产品信息
	 * @return
	 */
	public String saveRelateProduct(){
		try {
			relateProduct = new BMaterialRelateProduct(new BMaterial(mtlId),memo,relpduCode);
			bmaterialRelateProductBiz.saveRelateProduct(relateProduct);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return SUCCESS;
	}
	
	/**
	 * 删除相关联产品信息
	 * @return
	 */
	public String delRelateProduct(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String delmrpId = request.getParameter("delmrpId");
		mrpId = Integer.valueOf(delmrpId);
		try {
			bmaterialRelateProductBiz.delRelateProduct(mrpId);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return SUCCESS;
	}
	
	public String closeRelpdusWin(){
		//创建StringBuffer	
		StringBuffer productBuf = new StringBuffer();

		for (int i = 0; i < bmRelateProducts.size(); i++) {

			String code = bmRelateProducts.get(i).getProductCode();

			//拼接
			productBuf.append(code);
			if(i!=bmRelateProducts.size()-1){
				productBuf.append("，");
			}
		}

		productCodes=productBuf.toString();

		return "closeRelpdu";
	}
}
