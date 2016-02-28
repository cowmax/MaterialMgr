package com.actions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;



import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.bean.BMaterialImage;
import com.bean.ExtraImage;
import com.opensymphony.xwork2.ActionSupport;
import com.serviceImpl.BMaterialImageServiceImpl;
import com.serviceImpl.BMaterialImageTypeServiceImpl;
/**
 * 照片action类
 */
public class BMaterialImageAction extends ActionSupport {
	
	private BMaterialImageServiceImpl bmaterialImageServiceImpl;//封装照片service


	private String imgWholeUrl;//照片路径
	private BMaterialImage bmaterialImage;//照片对象
	private String msg;//提示语
	private Boolean flag;//判断
	private List<BMaterialImage> photoList;//添加的照片路径集合
	private Map<String,Object> jsonResult;//json封装的集合
	private ExtraImage extraImage;
	private List<ExtraImage> jsonList;//添加的照片路径集合
	
	private Integer imgId;	//照片ID
	private Integer mtlId;	//面料ID
	
	public BMaterialImageAction() {
		jsonList=new ArrayList<ExtraImage>();
		photoList = null;
	}

	//get、set
	public BMaterialImageServiceImpl getBmaterialImageServiceImpl() {
		return bmaterialImageServiceImpl;
	}

	public void setBmaterialImageServiceImpl(
			BMaterialImageServiceImpl bmaterialImageServiceImpl) {
		this.bmaterialImageServiceImpl = bmaterialImageServiceImpl;
	}
	

	public List<BMaterialImage> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<BMaterialImage> photoList) {
		this.photoList = photoList;
	}

	public String getImgWholeUrl() {
		return imgWholeUrl;
	}

	public void setImgWholeUrl(String imgWholeUrl) {
		this.imgWholeUrl = imgWholeUrl;
	}
	
	public BMaterialImage getBmaterialImage() {
		return bmaterialImage;
	}

	public void setBmaterialImage(BMaterialImage bmaterialImage) {
		this.bmaterialImage = bmaterialImage;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	
	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Map<String, Object> getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(Map<String, Object> jsonResult) {
		this.jsonResult = jsonResult;
	}

	public List<ExtraImage> getJsonList() {
		return jsonList;
	}

	public void setJsonList(List<ExtraImage> jsonList) {
		this.jsonList = jsonList;
	}

	public ExtraImage getExtraImage() {
		return extraImage;
	}

	public void setExtraImage(ExtraImage extraImage) {
		this.extraImage = extraImage;
	}

	public Integer getImgId() {
		return imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	public Integer getMtlId() {
		return mtlId;
	}

	public void setMtlId(Integer mtlId) {
		this.mtlId = mtlId;
	}

	/**
	 * 保存照片
	 */
	public String saveImage(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//清空集合
		jsonList.clear();
		
		//获取提交的名称和ulr
		String name=bmaterialImage.getImgTitle();
		String ulr=bmaterialImage.getImgUrl();
		
		//组成完整的ulr
		imgWholeUrl=ulr+name+".jpg";
		bmaterialImage.setImgUrl(imgWholeUrl);
		
		//保存
		flag=bmaterialImageServiceImpl.saveBMaterialImage(bmaterialImage);
		
		//获取提交mtlid
		Integer mtlId=bmaterialImage.getBmaterial().getMtlId();
		
		photoList = bmaterialImageServiceImpl.getImageUlr(mtlId);
		
		
		for (int i = 0; i < photoList.size(); i++) {
			ExtraImage eim =new ExtraImage();
			eim.setImgUrl(photoList.get(i).getImgUrl());
			eim.setImgColor(photoList.get(i).getImgColor());
			eim.setImgId(photoList.get(i).getImgId());
			eim.setMtlId(photoList.get(i).getBmaterial().getMtlId());
			jsonList.add(eim);
		}
		
		jsonResult=new HashMap<String, Object>();
		jsonResult.put("flag", flag);
		jsonResult.put("jsonList", jsonList);
		
		return "jsonResult";
	}
	

	
	
	/**
	 * 下载原图
	 */
	public  void makeImg() {  
		//接收文件路径
		HttpServletRequest request = ServletActionContext.getRequest();
	    imgWholeUrl = request.getParameter("imgWholeUrl");
		
		try {  

			URL url=new URL(imgWholeUrl);
			
			// 创建流  
			BufferedInputStream in = new BufferedInputStream(url.openStream());  

			// 生成图片名  
			int index = imgWholeUrl.lastIndexOf("/");  
			String sName = imgWholeUrl.substring(index+1, imgWholeUrl.length());  
			System.out.println(sName);  
			
			HttpServletResponse response = null;// 创建一个HttpServletResponse对象
			OutputStream out = null;// 创建一个输出流对象
			
			response = ServletActionContext.getResponse();// 初始化HttpServletResponse对象
			out = response.getOutputStream();//
			response.setHeader("Content-disposition", "attachment; filename="
					+ sName);
			response.setContentType("application/msexcel;charset=UTF-8");// 设置类型
			
			byte[] buf = new byte[2048];  
			int length = in.read(buf);  
			while (length != -1) {  
				out.write(buf, 0, length);  
				length = in.read(buf);  
			}  
			
			in.close();  
			out.close();  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
	}  
	
	/**
	 * 照片删除
	 * @return
	 */
	public String delImage(){
		try {
			jsonList.clear();
			bmaterialImageServiceImpl.deleteImage(imgId);
			flag = true;
			photoList = bmaterialImageServiceImpl.getImageUlr(mtlId);
			
			for (int i = 0; i < photoList.size(); i++) {
				ExtraImage eim =new ExtraImage();
				eim.setImgUrl(photoList.get(i).getImgUrl());
				eim.setImgColor(photoList.get(i).getImgColor());
				eim.setImgId(photoList.get(i).getImgId());
				eim.setMtlId(photoList.get(i).getBmaterial().getMtlId());
				jsonList.add(eim);
			}
			
			jsonResult=new HashMap<String, Object>();
			jsonResult.put("flag", flag);
			jsonResult.put("jsonList", jsonList);
			
		} catch (Exception e) {
			flag = false;
		}
		return "jsonResult";
	}
}
