package com.actions;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bean.BMaterialImageType;
import com.opensymphony.xwork2.ActionSupport;
import com.serviceImpl.BMaterialImageServiceImpl;
import com.serviceImpl.BMaterialImageTypeServiceImpl;
import com.serviceImpl.UtilSupport;


/**
 * 照片类型action类
 */
public class BMaterialImageTypeAction extends ActionSupport {
	
	private BMaterialImageTypeServiceImpl bmaterialImageTypeServiceImpl;//封装照片service
	private BMaterialImageServiceImpl bmaterialImageServiceImpl;//封装照片service
	private UtilSupport util;		//公用类
	private List<BMaterialImageType> imgTypeList;//类型集合所有
	private List imageColorList;//颜色集合所有
	
	private String imgTypeName;//类型名称
	private int rows;// 总的条数
	private int page;// 页数
	private int pageSize = 5;// 每页显示的条数
	private int offset;// 接受jsp页面传来的页面数 
	private String msg;//封装msg
	private Boolean flag;//封装判断
	private String imgColor;//颜色
	private BMaterialImageType bmaterialImageType;//照片类型对象
	private Integer mtlId;//面料id
	
	public BMaterialImageTypeAction() {
		imgTypeList=new ArrayList<BMaterialImageType>();
		imageColorList=new ArrayList();
	}

	//get、set
	public BMaterialImageTypeServiceImpl getBmaterialImageTypeServiceImpl() {
		return bmaterialImageTypeServiceImpl;
	}

	public UtilSupport getUtil() {
		return util;
	}

	public void setUtil(UtilSupport util) {
		this.util = util;
	}

	public void setBmaterialImageTypeServiceImpl(
			BMaterialImageTypeServiceImpl bmaterialImageTypeServiceImpl) {
		this.bmaterialImageTypeServiceImpl = bmaterialImageTypeServiceImpl;
	}
	
	public BMaterialImageServiceImpl getBmaterialImageServiceImpl() {
		return bmaterialImageServiceImpl;
	}

	public void setBmaterialImageServiceImpl(
			BMaterialImageServiceImpl bmaterialImageServiceImpl) {
		this.bmaterialImageServiceImpl = bmaterialImageServiceImpl;
	}

	public List<BMaterialImageType> getImgTypeList() {
		return imgTypeList;
	}

	public void setImgTypeList(List<BMaterialImageType> imgTypeList) {
		this.imgTypeList = imgTypeList;
	}
	
	public List getImageColorList() {
		return imageColorList;
	}

	public void setImageColorList(List imageColorList) {
		this.imageColorList = imageColorList;
	}

	public String getImgTypeName() {
		return imgTypeName;
	}

	public void setImgTypeName(String imgTypeName) {
		this.imgTypeName = imgTypeName;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public BMaterialImageType getBmaterialImageType() {
		return bmaterialImageType;
	}

	public void setBmaterialImageType(BMaterialImageType bmaterialImageType) {
		this.bmaterialImageType = bmaterialImageType;
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

	public String getImgColor() {
		return imgColor;
	}

	public void setImgColor(String imgColor) {
		this.imgColor = imgColor;
	}

	public Integer getMtlId() {
		return mtlId;
	}

	public void setMtlId(Integer mtlId) {
		this.mtlId = mtlId;
	}

	/**
	 * 删除照片类型
	 */
	public String delPhoto(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//接收页面传过来的值
		String var=request.getParameter("imgType");
		//转成int类型
		Integer imgType=Integer.parseInt(var);
		
		bmaterialImageType.setImgType(imgType);
		
		flag=bmaterialImageTypeServiceImpl.delImage(bmaterialImageType);
		
		return "del";
	}
	
	/**
	 * 添加照片类型
	 */
	public String addPoto(){
		HttpServletRequest request = ServletActionContext.getRequest();
		
		flag=bmaterialImageTypeServiceImpl.addImage(bmaterialImageType);
		
		return "savap";
	}
	
	
	
	/**
	 * 获取所有照片的类型
	 */
	public String  allImageType(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setCharacterEncoding("UTF-8");
			
			String md=request.getParameter("mtlId");
			mtlId=Integer.valueOf(md);
			
			imgTypeList=bmaterialImageTypeServiceImpl.allImageType();
			
			// 获取所以不相同的颜色
			imageColorList=bmaterialImageServiceImpl.getImagecColor();
			
			//回传给页面
			HttpServletResponse response = ServletActionContext.getResponse();
			request.setAttribute("mtlId", mtlId);
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "all";
	}
	
	
	/**
	 * 显示照片的类型 
	 */
	public String showImageType() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");

		StringBuffer sql = new StringBuffer("select * from b_Material_Image_Type ");
	
		rows = util.getTotalCount(sql.toString());

		page = rows % pageSize == 0 ? rows / pageSize : rows / pageSize + 1;

		offset = getPageOffset();

		imgTypeList = util.getPageListBySql(sql.toString(),String.valueOf(offset), String.valueOf(pageSize),
				new Class[] { BMaterialImageType.class});

		return "show";
	}
	
	// Added by JSL : 获取翻页偏移量(实际上是将要翻到的页面的页索引，页索引从 0 开始)
		private int getPageOffset() {
			HttpServletRequest request = ServletActionContext.getRequest();
			String ofst = request.getParameter("offset");
			int idx = 0;
			if (ofst != null) {
				idx = Integer.valueOf(ofst);
				idx = idx < 0 ? 0 : idx; // 超过第一页时，不再翻页
				idx = idx >= page ? (page - 1) : idx; // 超过最后一页时，不再翻页
			}
			return idx;
		}
	
		
}
