package com.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;

import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import com.bean.BMaterial;
import com.bean.BMaterialImage;
import com.bean.BMaterialIngredient;
import com.bean.BMaterialRelateProduct;
import com.bean.BMaterialSupplier;
import com.bean.BMaterialTestReport;
import com.bean.BMaterialType;
import com.bean.ExtraBMaterial;
import com.bean.ExtraBrand;
import com.bean.ExtraImage;
import com.bean.ExtraType;
import com.bean.TypeCombox;
import com.opensymphony.xwork2.ActionSupport;
import com.serviceImpl.BMaterialBrandServiceImpl;
import com.serviceImpl.BMaterialImageServiceImpl;
import com.serviceImpl.BMaterialIngredientServiceImpl;
import com.serviceImpl.BMaterialRelateProductServiceImpl;
import com.serviceImpl.BMaterialServiceImpl;
import com.serviceImpl.BMaterialSupplierSerivceImpl;
import com.serviceImpl.BMaterialTestReportServiceImpl;
import com.serviceImpl.UtilSupport;

@SuppressWarnings("serial")
public class BMaterialAction extends ActionSupport {

	private UtilSupport util;		//公用类
	private boolean flag;
	private BMaterialServiceImpl bmaterialBiz;
	private BMaterialImageServiceImpl bmaterialImageServiceImpl;
	private BMaterialRelateProductServiceImpl bmaterialRelateProductBiz;
	private BMaterialBrandServiceImpl bmaterialBrandBiz;
	private BMaterialIngredientServiceImpl ingredientServiceImpl;
	private BMaterialSupplierSerivceImpl bmaterialSupplierBiz;
	private BMaterialTestReportServiceImpl bmaterialTestReportBiz;
	
	private BMaterial material;
	private BMaterial newMaterial;		//新增面料对象
	private List<BMaterial> bmaterialList;		//面料集合
	private List<ExtraBMaterial> ebmaterialList;	//面料档案集合
	private List<BMaterialType> parentMtlTypeList;	//面料父类型集合
	private List<BMaterialType> childtMtlTypeList;	//某面料父类型下子类型集合
	private List<ExtraType> allChildMaList;		//所有子类型集合
	private List<ExtraType> allTypeList;
	private Map<String, Object> jsonResult;
	private String typesJson;
	private JSONArray ctypesJson;
	private List<TypeCombox> typeComboxs;
	
	//面料修改关联集合
	private List<BMaterialImage> mtlImages;
	private List<BMaterialRelateProduct> mtlRelateProducts;
	private List<ExtraBrand> mtlBrands;
	private List<BMaterialIngredient> mtlIngredients;
	private List<BMaterialSupplier> mtlSupplies;
	private List<BMaterialTestReport> mtlTestReports;
	
	private String brandValue;		//修改页面上品牌显示
	private List<ExtraImage> eimages;
	private String eimagesJson;		//页面照片显示
	private String ingreds;	//修改页面上成分显示
	private String productCodes;	//修改页面上关联产品显示
	
	private int offset;			//当前页
	private int pageSize=10;	//每页显示条数
	private int totalcount;		// 总记录数
	private int totalpage; 		// 总页数
	
	private int mtlTypeId;
	private String selfMtlCode; //根据mtl_typt自动生成面料编码
	private int qpid;//根据父面料类型获取子类型集合（面料父类型ID）
	
	//查询条件
	private Map<String, Object> options;
	private String qmtlCode;//面料编码
	private String qmtlName;//面料名称
	private Integer qpmtlType;//面料父类型
	private Integer qmtlType;//面料类型
	private String qseason;//季节
	private String qcolor;//颜色编码
	private Timestamp qscreateDt;//开始创建时间
	private Timestamp qecreateDt;//结束创建时间
	private Double qminMtlPrice;//最小单价
	private Double qmaxMtlPrice;//最高单价
	private Double qminWeigth;//最小克重
	private Double qmaxWeigth;//最大克重
	private Integer qminPrdCycle;//最小周期
	private Integer qmaxPrdCycle;//最大周期
	private Integer qminOrder;//最小订单量
	private Integer qmaxOrder;//最大订单量
	private String qsupplierName;//供应商名称
	
	//类型添加、删除、修改
	private String typeName;
	private Integer pid;
	private Integer mtlId;
	
	private File myFile;//封装上传的文件
	private String myFileContentType;//封装上传文件的类型
	private String myFileFileName;//封装上传文件的文件名
	private String refreshList;//封装刷新的页面
	private String titleName;//封装title名字
	private boolean headerJudge;//封装表头的判断
	private String msg;//封装jsp提示语
	
	public BMaterialAction() {
		bmaterialList = new ArrayList<BMaterial>();
		ebmaterialList = new ArrayList<ExtraBMaterial>();
		allChildMaList = new ArrayList<ExtraType>();
		allTypeList = new ArrayList<ExtraType>();
		
		mtlImages = new ArrayList<BMaterialImage>();
		mtlRelateProducts = new ArrayList<BMaterialRelateProduct>();
		mtlBrands = new ArrayList<ExtraBrand>();
		mtlIngredients = new ArrayList<BMaterialIngredient>();
		mtlSupplies = new ArrayList<BMaterialSupplier>();
		mtlTestReports =new ArrayList<BMaterialTestReport>();
		eimages = new ArrayList<ExtraImage>();
	}

	public List<TypeCombox> getTypeComboxs() {
		return typeComboxs;
	}

	public void setTypeComboxs(List<TypeCombox> typeComboxs) {
		this.typeComboxs = typeComboxs;
	}

	public JSONArray getCtypesJson() {
		return ctypesJson;
	}

	public void setCtypesJson(JSONArray ctypesJson) {
		this.ctypesJson = ctypesJson;
	}

	public String getProductCodes() {
		return productCodes;
	}

	public void setProductCodes(String productCodes) {
		this.productCodes = productCodes;
	}

	public String getIngreds() {
		return ingreds;
	}

	public void setIngreds(String ingreds) {
		this.ingreds = ingreds;
	}

	public List<ExtraImage> getEimages() {
		return eimages;
	}

	public void setEimages(List<ExtraImage> eimages) {
		this.eimages = eimages;
	}

	public String getBrandValue() {
		return brandValue;
	}

	public void setBrandValue(String brandValue) {
		this.brandValue = brandValue;
	}

	public List<BMaterialImage> getMtlImages() {
		return mtlImages;
	}

	public void setMtlImages(List<BMaterialImage> mtlImages) {
		this.mtlImages = mtlImages;
	}

	public List<BMaterialRelateProduct> getMtlRelateProducts() {
		return mtlRelateProducts;
	}

	public void setMtlRelateProducts(List<BMaterialRelateProduct> mtlRelateProducts) {
		this.mtlRelateProducts = mtlRelateProducts;
	}

	public List<ExtraBrand> getMtlBrands() {
		return mtlBrands;
	}

	public void setMtlBrands(List<ExtraBrand> mtlBrands) {
		this.mtlBrands = mtlBrands;
	}

	public List<BMaterialIngredient> getMtlIngredients() {
		return mtlIngredients;
	}

	public void setMtlIngredients(List<BMaterialIngredient> mtlIngredients) {
		this.mtlIngredients = mtlIngredients;
	}

	public List<BMaterialSupplier> getMtlSupplies() {
		return mtlSupplies;
	}

	public void setMtlSupplies(List<BMaterialSupplier> mtlSupplies) {
		this.mtlSupplies = mtlSupplies;
	}

	public List<BMaterialTestReport> getMtlTestReports() {
		return mtlTestReports;
	}

	public void setMtlTestReports(List<BMaterialTestReport> mtlTestReports) {
		this.mtlTestReports = mtlTestReports;
	}

	public BMaterialImageServiceImpl getBmaterialImageServiceImpl() {
		return bmaterialImageServiceImpl;
	}

	public void setBmaterialImageServiceImpl(
			BMaterialImageServiceImpl bmaterialImageServiceImpl) {
		this.bmaterialImageServiceImpl = bmaterialImageServiceImpl;
	}

	public BMaterialRelateProductServiceImpl getBmaterialRelateProductBiz() {
		return bmaterialRelateProductBiz;
	}

	public void setBmaterialRelateProductBiz(
			BMaterialRelateProductServiceImpl bmaterialRelateProductBiz) {
		this.bmaterialRelateProductBiz = bmaterialRelateProductBiz;
	}

	public BMaterialBrandServiceImpl getBmaterialBrandBiz() {
		return bmaterialBrandBiz;
	}

	public void setBmaterialBrandBiz(BMaterialBrandServiceImpl bmaterialBrandBiz) {
		this.bmaterialBrandBiz = bmaterialBrandBiz;
	}

	public BMaterialIngredientServiceImpl getIngredientServiceImpl() {
		return ingredientServiceImpl;
	}

	public void setIngredientServiceImpl(
			BMaterialIngredientServiceImpl ingredientServiceImpl) {
		this.ingredientServiceImpl = ingredientServiceImpl;
	}

	public BMaterialSupplierSerivceImpl getBmaterialSupplierBiz() {
		return bmaterialSupplierBiz;
	}

	public void setBmaterialSupplierBiz(
			BMaterialSupplierSerivceImpl bmaterialSupplierBiz) {
		this.bmaterialSupplierBiz = bmaterialSupplierBiz;
	}

	public BMaterialTestReportServiceImpl getBmaterialTestReportBiz() {
		return bmaterialTestReportBiz;
	}

	public void setBmaterialTestReportBiz(
			BMaterialTestReportServiceImpl bmaterialTestReportBiz) {
		this.bmaterialTestReportBiz = bmaterialTestReportBiz;
	}

	public BMaterial getMaterial() {
		return material;
	}

	public void setMaterial(BMaterial material) {
		this.material = material;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public BMaterial getNewMaterial() {
		return newMaterial;
	}

	public void setNewMaterial(BMaterial newMaterial) {
		this.newMaterial = newMaterial;
	}

	public UtilSupport getUtil() {
		return util;
	}

	public void setUtil(UtilSupport util) {
		this.util = util;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public int getMtlTypeId() {
		return mtlTypeId;
	}

	public void setMtlTypeId(int mtlTypeId) {
		this.mtlTypeId = mtlTypeId;
	}

	public String getSelfMtlCode() {
		return selfMtlCode;
	}

	public void setSelfMtlCode(String selfMtlCode) {
		this.selfMtlCode = selfMtlCode;
	}

	public List<BMaterial> getBmaterialList() {
		return bmaterialList;
	}

	public void setBmaterialList(List<BMaterial> bmaterialList) {
		this.bmaterialList = bmaterialList;
	}

	public List<ExtraBMaterial> getEbmaterialList() {
		return ebmaterialList;
	}

	public void setEbmaterialList(List<ExtraBMaterial> ebmaterialList) {
		this.ebmaterialList = ebmaterialList;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
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

	public BMaterialServiceImpl getBmaterialBiz() {
		return bmaterialBiz;
	}

	public void setBmaterialBiz(BMaterialServiceImpl bmaterialBiz) {
		this.bmaterialBiz = bmaterialBiz;
	}

	public List<BMaterialType> getParentMtlTypeList() {
		return parentMtlTypeList;
	}

	public void setParentMtlTypeList(List<BMaterialType> parentMtlTypeList) {
		this.parentMtlTypeList = parentMtlTypeList;
	}

	public List<BMaterialType> getChildtMtlTypeList() {
		return childtMtlTypeList;
	}

	public void setChildtMtlTypeList(List<BMaterialType> childtMtlTypeList) {
		this.childtMtlTypeList = childtMtlTypeList;
	}

	public int getQpid() {
		return qpid;
	}

	public void setQpid(int qpid) {
		this.qpid = qpid;
	}

	public String getQmtlCode() {
		return qmtlCode;
	}

	public void setQmtlCode(String qmtlCode) {
		this.qmtlCode = qmtlCode;
	}

	public String getQmtlName() {
		return qmtlName;
	}

	public void setQmtlName(String qmtlName) {
		this.qmtlName = qmtlName;
	}

	public Integer getQpmtlType() {
		return qpmtlType;
	}

	public void setQpmtlType(Integer qpmtlType) {
		this.qpmtlType = qpmtlType;
	}

	public Integer getQmtlType() {
		return qmtlType;
	}

	public void setQmtlType(Integer qmtlType) {
		this.qmtlType = qmtlType;
	}

	public String getQseason() {
		return qseason;
	}

	public void setQseason(String qseason) {
		this.qseason = qseason;
	}

	public String getQcolor() {
		return qcolor;
	}

	public void setQcolor(String qcolor) {
		this.qcolor = qcolor;
	}

	public Timestamp getQscreateDt() {
		return qscreateDt;
	}

	public void setQscreateDt(Timestamp qscreateDt) {
		this.qscreateDt = qscreateDt;
	}

	public Timestamp getQecreateDt() {
		return qecreateDt;
	}

	public void setQecreateDt(Timestamp qecreateDt) {
		this.qecreateDt = qecreateDt;
	}


	public Double getQminMtlPrice() {
		return qminMtlPrice;
	}

	public void setQminMtlPrice(Double qminMtlPrice) {
		this.qminMtlPrice = qminMtlPrice;
	}

	public Double getQmaxMtlPrice() {
		return qmaxMtlPrice;
	}

	public void setQmaxMtlPrice(Double qmaxMtlPrice) {
		this.qmaxMtlPrice = qmaxMtlPrice;
	}

	public Double getQminWeigth() {
		return qminWeigth;
	}

	public void setQminWeigth(Double qminWeigth) {
		this.qminWeigth = qminWeigth;
	}

	public Double getQmaxWeigth() {
		return qmaxWeigth;
	}

	public void setQmaxWeigth(Double qmaxWeigth) {
		this.qmaxWeigth = qmaxWeigth;
	}

	public Integer getQminPrdCycle() {
		return qminPrdCycle;
	}

	public void setQminPrdCycle(Integer qminPrdCycle) {
		this.qminPrdCycle = qminPrdCycle;
	}

	public Integer getQmaxPrdCycle() {
		return qmaxPrdCycle;
	}

	public void setQmaxPrdCycle(Integer qmaxPrdCycle) {
		this.qmaxPrdCycle = qmaxPrdCycle;
	}

	public Integer getQminOrder() {
		return qminOrder;
	}

	public void setQminOrder(Integer qminOrder) {
		this.qminOrder = qminOrder;
	}

	public Integer getQmaxOrder() {
		return qmaxOrder;
	}

	public void setQmaxOrder(Integer qmaxOrder) {
		this.qmaxOrder = qmaxOrder;
	}

	public String getQsupplierName() {
		return qsupplierName;
	}

	public void setQsupplierName(String qsupplierName) {
		this.qsupplierName = qsupplierName;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public String getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
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

	public boolean isHeaderJudge() {
		return headerJudge;
	}

	public void setHeaderJudge(boolean headerJudge) {
		this.headerJudge = headerJudge;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<ExtraType> getAllChildMaList() {
		return allChildMaList;
	}

	public void setAllChildMaList(List<ExtraType> allChildMaList) {
		this.allChildMaList = allChildMaList;
	}

	public Map<String, Object> getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(Map<String, Object> jsonResult) {
		this.jsonResult = jsonResult;
	}

	public List<ExtraType> getAllTypeList() {
		return allTypeList;
	}

	public void setAllTypeList(List<ExtraType> allTypeList) {
		this.allTypeList = allTypeList;
	}

	public Integer getMtlId() {
		return mtlId;
	}

	public void setMtlId(Integer mtlId) {
		this.mtlId = mtlId;
	}

	public String getTypesJson() {
		return typesJson;
	}

	public void setTypesJson(String typesJson) {
		this.typesJson = typesJson;
	}

	public String getEimagesJson() {
		return eimagesJson;
	}

	public void setEimagesJson(String eimagesJson) {
		this.eimagesJson = eimagesJson;
	}

	public Map<String, Object> getOptions() {
		return options;
	}

	public void setOptions(Map<String, Object> options) {
		this.options = options;
	}

	/**
	 * 添加面料信息
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String addMaterial() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Date date = new Date();//创建一个时间对象，获取到当前的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置时间显示格式
		String str = sdf.format(date);// 将当前时间格式化为需要的类型
		
		String mtlCode = (String)request.getAttribute("selfMtlCode");
		if(mtlCode!=null&&!mtlCode.isEmpty()){
			selfMtlCode=new String(mtlCode.trim().getBytes("ISO-8859-1"),"UTF-8");
		}
		//设置编码
		newMaterial.setMtlCode(selfMtlCode);
		//设置当前时间
		newMaterial.setSysDt(Timestamp.valueOf(str));
		//添加当前操作用户
		newMaterial.setSysUser("admin");
		//修改状态（2）
		newMaterial.setStatus(2);
		//确认添加
		bmaterialBiz.mergeBMaterial(newMaterial);
		
		HttpSession session = request.getSession(false);
		
		//获取名称
		String mtlName = newMaterial.getMtlName();
		
		//返回给Sussess.jsp
		refreshList = "materialgetMaterialList";
		titleName = "查询面料档案";
		msg = "面料   【 "+mtlName+" 】 ";
		session.setAttribute("msg", msg);
		
		return "saveMater";
		
	}

	/**
	 * 添加面料信息
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String editMaterial() throws UnsupportedEncodingException{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Date date = new Date();//创建一个时间对象，获取到当前的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置时间显示格式
		String str = sdf.format(date);// 将当前时间格式化为需要的类型
		
		String mtlCode = (String)request.getAttribute("selfMtlCode");
		if(mtlCode!=null&&!mtlCode.isEmpty()){
			selfMtlCode=new String(mtlCode.trim().getBytes("ISO-8859-1"),"UTF-8");
		}
		//设置编码
		newMaterial.setMtlCode(selfMtlCode);
		//设置当前时间
		newMaterial.setSysDt(Timestamp.valueOf(str));
		//添加当前操作用户
		newMaterial.setSysUser("admin");
		//修改状态（2）
		newMaterial.setStatus(2);
		//确认添加
		bmaterialBiz.mergeBMaterial(newMaterial);
		
		HttpSession session = request.getSession(false);
		
		//获取名称
		String mtlName = newMaterial.getMtlName();
		
		//返回给Sussess.jsp
		refreshList = "materialgetMaterialList";
		titleName = "查询面料档案";
		msg = "面料   【 "+mtlName+" 】 ";
		session.setAttribute("msg", msg);
		
		return "saveMater";
		
	}
	
	/**
	 * 删除面料信息
	 * @return
	 */
	public String delMaterial(){
		try {
			bmaterialBiz.deleteBMaterial(mtlId);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 获取面料信息
	 * @return
	 */
	private void getMaterialInfo(){
		//获取要修改面料信息
		newMaterial = bmaterialBiz.findByMtlId(mtlId);
		
		//获取面料相关信息
		mtlImages= bmaterialImageServiceImpl.getImageUlr(mtlId);	//相关照片信息
		mtlRelateProducts = bmaterialRelateProductBiz.getRelateProducts(mtlId);	//相关联产品信息
		mtlBrands = bmaterialBrandBiz.findBrandsByMtlId(mtlId);		//相关联品牌信息
		mtlIngredients = ingredientServiceImpl.getIngredient(mtlId);	//相关联成分信息
		mtlSupplies = bmaterialSupplierBiz.getSuppliersByMtlId(mtlId);	//相关联供应商信息
		mtlTestReports = bmaterialTestReportBiz.findTestReportsByMtlid(mtlId);	//相关联测试报告信息
		
		//获取品牌显示
		StringBuffer brandBuf = new StringBuffer();
		for (int i = 0; i < mtlBrands.size(); i++) {
			ExtraBrand ebrand = mtlBrands.get(i);
			brandBuf.append(ebrand.getBrandName());
			if(i!=mtlBrands.size()-1){
				brandBuf.append(",");
			}
		}
		brandValue = brandBuf.toString();
		
		//填充类型下拉框
		jsonResult = getAllChildTypeJson();
		
		eimages.clear();
		//照片显示
		for (int i = 0; i < mtlImages.size(); i++) {
			ExtraImage eim =new ExtraImage();
			eim.setImgUrl(mtlImages.get(i).getImgUrl());
			eim.setImgColor(mtlImages.get(i).getImgColor());
			eim.setImgId(mtlImages.get(i).getImgId());
			eim.setMtlId(mtlImages.get(i).getBmaterial().getMtlId());
			eimages.add(eim);
		}
		net.sf.json.JSONArray jsonArray = net.sf.json.JSONArray.fromObject(eimages);
		eimagesJson = jsonArray.toString();
		
		//成分显示
		//创建StringBuffer	
		StringBuffer ingredBuf = new StringBuffer();

		for (int i = 0; i < mtlIngredients.size(); i++) {

			String name=mtlIngredients.get(i).getIngredientName();
			String prec=mtlIngredients.get(i).getPrecent()*100+"%";

			//拼接
			String ingred=name+" "+prec;
			ingredBuf.append(ingred);
			if(i!=mtlIngredients.size()-1){
				ingredBuf.append("，");
			}
		}

		ingreds=ingredBuf.toString();
		
		//产品显示
		//创建StringBuffer	
		StringBuffer productBuf = new StringBuffer();

		for (int i = 0; i < mtlRelateProducts.size(); i++) {

			String code = mtlRelateProducts.get(i).getProductCode();

			//拼接
			productBuf.append(code);
			if(i!=mtlRelateProducts.size()-1){
				productBuf.append("，");
			}
		}

		productCodes=productBuf.toString();
		
	}
	
	/**
	 * 修改面料信息
	 * @return
	 */
	public String mergeMaterial(){
		getMaterialInfo();
		return "editMaterialOpr";
	}
	
	public String getMateialDetail(){
		getMaterialInfo();
		return "materialDetail";
	}
	
	/**
	 * 根据父面料类型获取子类型集合
	 * @return
	 */
	public String getMtlTypeListByPid(){
		childtMtlTypeList = bmaterialBiz.getMtlTypeListByPid(qpid);
		typeComboxs = new ArrayList<TypeCombox>();
		Integer typeId;
		String typeName;
		flag = false;
		for (int i = 0; i < childtMtlTypeList.size(); i++) {
			typeId = childtMtlTypeList.get(i).getMtlType();
			typeName = childtMtlTypeList.get(i).getMtlTypeName();
			if(qmtlType!=null){
				if(qmtlType.equals(typeId)){
					flag = true;
				}
			}
			TypeCombox typeCombox = new TypeCombox(typeId,typeName,flag);
			typeComboxs.add(typeCombox);
		}
		ctypesJson = JSONArray.fromObject(typeComboxs); 
		
		return "loadCombobox";
	}
	
	/**
	 * 获取所有面料子类型集合
	 * @return
	 */
	private Map<String, Object> getAllChildTypeJson(){
		Map<String, Object> jsonSource = new HashMap<String, Object>();
		try {
			allChildMaList = bmaterialBiz.getAllChildTypeList();
			StringBuffer typesub = new StringBuffer("[");
			for (int i = 0; i < allChildMaList.size(); i++) {
				if(i == allChildMaList.size()-1){
					typesub.append("{\"value\":"+allChildMaList.get(i).getMtlType()+",\"text\":\""+allChildMaList.get(i).getMtlTypeName()+"\"}]");
				}else{
					typesub.append("{\"value\":"+allChildMaList.get(i).getMtlType()+",\"text\":\""+allChildMaList.get(i).getMtlTypeName()+"\"},");
				}
			}
			typesJson = typesub.toString();
			jsonSource.put("jsonTypes", typesJson);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		jsonSource.put("flag", flag);
		return jsonSource;
	}
	/**
	 * 获取面料信息集合
	 * @return
	 */
	public String getMaterialList() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		options = new HashMap<String, Object>();
		this.qmtlCode = request.getParameter("qmtlCode");
		if(qmtlCode!=null&&!qmtlCode.isEmpty()){
			qmtlCode=new String(qmtlCode.trim().getBytes("ISO-8859-1"),"UTF-8");
			options.put("@qmtlCode", qmtlCode);
		}
		
		this.qmtlName = request.getParameter("qmtlName");
		if(qmtlName != null&&!qmtlName.isEmpty()){
			qmtlName=new String(qmtlName.trim().getBytes("ISO-8859-1"),"UTF-8");
			options.put("@qmtlName", qmtlName);
		}
		
		String ptype = request.getParameter("qpmtlType");
		if(ptype!=null&&!ptype.isEmpty()){
			this.qpmtlType = Integer.parseInt(ptype);
			options.put("@qpmtlType", qpmtlType);
		}
		
		String type = request.getParameter("qmtlType");
		if(type!=null&&!type.isEmpty()){
			this.qmtlType = Integer.parseInt(type);
			options.put("@qmtlType", qmtlType);
		}
		
		this.qseason = request.getParameter("qseason");
		if(qseason != null&&!qseason.isEmpty()){
			qseason=new String(qseason.trim().getBytes("ISO-8859-1"),"UTF-8");
			options.put("@qseason", qseason);
		}
		
		this.qcolor = request.getParameter("qcolor");
		if(qcolor != null&&!qcolor.isEmpty()){
			qcolor=new String(qcolor.trim().getBytes("ISO-8859-1"),"UTF-8");
			options.put("@qcolor", qcolor);
		}
		
		String screateDt=request.getParameter("qscreateDt");
		if(screateDt!=null&&!screateDt.isEmpty()){
			this.qscreateDt=Timestamp.valueOf(screateDt);
			options.put("@qscreateDt", qscreateDt);
		}
		
		String ecreateDt=request.getParameter("qecreateDt");
		if(ecreateDt!=null&&!ecreateDt.isEmpty()){
			this.qecreateDt=Timestamp.valueOf(ecreateDt);
			options.put("@qecreateDt", qecreateDt);
		}
		
		String minMtlPrice = request.getParameter("qminMtlPrice");
		if(minMtlPrice!=null&&!minMtlPrice.isEmpty()){
			this.qminMtlPrice = Double.parseDouble(minMtlPrice);
			options.put("@qminMtlPrice", qminMtlPrice);
		}
		
		String maxMtlPrice = request.getParameter("qmaxMtlPrice");
		if(maxMtlPrice!=null&&!maxMtlPrice.isEmpty()){
			this.qmaxMtlPrice = Double.parseDouble(maxMtlPrice);
			options.put("@qmaxMtlPrice", qmaxMtlPrice);
		}
		
		String minWeigth = request.getParameter("qminWeigth");
		if(minWeigth!=null&&!minWeigth.isEmpty()){
			this.qminWeigth = Double.parseDouble(minWeigth);
			options.put("@qminWeigth", qminWeigth);
		}
		
		String maxWeigth = request.getParameter("qmaxWeigth");
		if(maxWeigth!=null&&!maxWeigth.isEmpty()){
			this.qmaxWeigth = Double.parseDouble(maxWeigth);
			options.put("@qmaxWeigth", qmaxWeigth);
		}
		
		String minPrdCycle = request.getParameter("qminPrdCycle");
		if(minPrdCycle!=null&&!minPrdCycle.isEmpty()){
			this.qminPrdCycle = Integer.parseInt(minPrdCycle);
			options.put("@qminPrdCycle", qminPrdCycle);
		}

		String maxPrdCycle = request.getParameter("qmaxPrdCycle");
		if(maxPrdCycle!=null&&!maxPrdCycle.isEmpty()){
			this.qmaxPrdCycle = Integer.parseInt(maxPrdCycle);
			options.put("@qmaxPrdCycle", qmaxPrdCycle);
		}
		
		String minOrder = request.getParameter("qminOrder");
		if(minOrder!=null&&!minOrder.isEmpty()){
			this.qminOrder = Integer.parseInt(minOrder);
			options.put("@qminOrder", qminOrder);
		}
		
		String maxOrder = request.getParameter("qmaxOrder");
		if(maxOrder!=null&&!maxOrder.isEmpty()){
			this.qmaxOrder = Integer.parseInt(maxOrder);
			options.put("@qmaxOrder", qmaxOrder);
		}
		
		this.qsupplierName = request.getParameter("qsupplierName");
		if(qsupplierName != null&&!qsupplierName.isEmpty()){
			qsupplierName=new String(qsupplierName.trim().getBytes("ISO-8859-1"),"UTF-8");
			options.put("@qsupplierName", qsupplierName);
		}
		
		ebmaterialList = bmaterialBiz.getMtlByOptions(options);
		
		totalcount = ebmaterialList.size();		//获取总条数

		totalpage = totalcount % pageSize == 0 ? totalcount / pageSize 
				: totalcount / pageSize + 1;		//获取总页数

		offset = getPageOffset();
		parentMtlTypeList = bmaterialBiz.allParentType();
		
		return "show";
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
	
//	public String loadTypeCombox(){
//		parentMtlTypeList = bmaterialBiz.allParentType();
//		typeComboxs = new ArrayList<TypeCombox>();
//		
//		for (int i = 0; i < parentMtlTypeList.size(); i++) {
//			TypeCombox typeCombox = new TypeCombox(parentMtlTypeList.get(i).getMtlType(),parentMtlTypeList.get(i).getMtlTypeName());
//			typeComboxs.add(typeCombox);
//		}
//		ptypesJson = JSONArray.fromObject(typeComboxs); 
//		
//		return "typeCombox";
//	}
	
	/**
	 * 添加操作
	 * @return
	 */
	public String addMtlOperation(){
		jsonResult = getAllChildTypeJson();
		newMaterial = new BMaterial();
		bmaterialBiz.saveBMaterial(newMaterial);
		return "addOperation";
	}
	
	/**
	 * 获取面料编码
	 */
	public String getMtlCode(){
		try {
			jsonResult = new HashMap<String, Object>();
			selfMtlCode = bmaterialBiz.getMtlCodeByType(mtlTypeId);
			flag = true;
			jsonResult.put("selfMtlCode", selfMtlCode);
		} catch (Exception e) {
			flag = false;
		}
		jsonResult.put("flag", flag);
		return "getmtlCode";
	}
	
	/**
	 * 获取所有类型信息集合
	 * @return
	 */
	public String getMtlTypeList(){
		allTypeList =  bmaterialBiz.loadAllTypeList();
		jsonResult = getAllChildTypeJson();
		parentMtlTypeList = bmaterialBiz.allParentType();
		return "addMtlTypeOperation";
	}
	
	/**
	 * 添加面料类型
	 * @return
	 */
	public String addType(){
		try {
			if(pid==null){
				pid = 0;
			}
			BMaterialType bmType = new BMaterialType(pid,typeName);
			bmaterialBiz.saveType(bmType);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return SUCCESS;
	}
	
	public String addOrDelType(){
		jsonResult = getAllChildTypeJson();
		return "typeOpr";
	}
	
	/**
	 * 删除类型信息
	 * @return
	 */
	public String delType(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String mtlTypeId = request.getParameter("delmtlType");
		mtlId = Integer.valueOf(mtlTypeId);
		int count = 0;
		
		BMaterialType mtlType = bmaterialBiz.findMtlTypeByMtlType(mtlId);
		Set<BMaterialType> mtlTypeSets = mtlType.getBMaterials();
		if(mtlTypeSets.size()>0){
			count=1;
		}else{
			bmaterialBiz.deleteType(mtlType);
		}
		if(count>0){
			flag = false;
		}else{
			flag = true;
		}
		return SUCCESS;
	}
	
	
	/**
	 * 文件上传
	 */
	public String uploadFiles() throws Exception{
		// 基于myFile创建一个文件输入流
		InputStream is = new FileInputStream(myFile);
		// 设置上传文件目录
		String uploadPath = ServletActionContext.getServletContext()
				.getRealPath("/upload");
		// 设置目标文件
		File toFile = new File(uploadPath, this.getMyFileFileName());
		// 创建一个输出流
		File toDir = new File(uploadPath);
		
		if (!toDir.exists()) {
			toDir.mkdir();
		}
		
		if (!toFile.exists()) {
			toFile.createNewFile();
		}
		
		OutputStream os = new FileOutputStream(toFile);
		// 设置缓存
		byte[] buffer = new byte[1024];
		int length = 0;
		// 读取myFile文件输出到toFile文件中
		while ((length = is.read(buffer)) > 0) {
			os.write(buffer, 0, length);
		}
		// 关闭输入流
		is.close();
		// 关闭输出流
		os.close();
	
		return "upload";
	}
	
	/**
	 * 导入Excel
	 */
	public String intoDB() throws IOException {
		String uploadPath = ServletActionContext.getServletContext()
				.getRealPath("/upload");
		Boolean isImpSuccess = false;	//判断数据导入是否成功
		
		// 基于myFile创建一个文件输入流
		InputStream is = new FileInputStream(myFile);
		// 设置目标文件
		File toFile = new File(uploadPath, this.getMyFileFileName());

		 String mtlCode = null;//面料编码
		 String mtlName = null;//面料名称
		 String season = null;//面料适应季节
		 Integer colorCount=null;//颜色数量
		 String mtlType = null;//面料类型
		 Double width = null;// 面料幅宽
		 Double weigth = null;// 面料克重
		 Double shrinkW = null;//面料横向收缩率
		 Double shrinkH = null;//面料纵向收缩率
		 Double mtlPrice = null;//面料单价
		 Double mtlNtxPrice = null;//面料不含税单价
		 String mtlUnit=null;//面料价格单位
		 Integer amount = null;//库存数量
		 Integer minOrder=null;//最小订单量
		 Integer prdCycle = null;//生成周期
		 Date createDt = null;//面料档案创建时间
		 Date sysDt = null;//面料档案更新时间
		 String sysUser = null;//操作用户ID
		 
		Date date = new Date();// 创建一个时间对象，获取到当前的时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置时间显示格式
		String str = sdf.format(date);// 将当前时间格式化为需要的类型
		sysDt = Timestamp.valueOf(str);
		
		sysUser="admin";
		
		String[] tableHeader = { "面料编码","面料名称", "季节","颜色数量", "面料类型", "幅宽","克重", "横向缩率","横向缩率","含税单价",
				"不含税单价","价格单位","库存数量","最小订单量","生成周期","开发时间"};
	
		
		/**
		 * 2007版的读取方法
		 */
		int k = 0;
		int pointer = 0; // 指示指针所访问的位置
		if (myFile != null) {
			try {
				//查找所有的type
				List typelist=bmaterialBiz.typeList();
				
				Workbook workbook = WorkbookFactory.create(toFile);
				bmaterialList.clear();
				outterLoop:
				for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) { // 读取每一个sheet
					
					if (null != workbook.getSheetAt(numSheets)) {
						// 定义Sheet对象
						XSSFSheet aSheet = (XSSFSheet) workbook.getSheetAt(numSheets);
						
						// 进入当前sheet的行的循环
						for (int rowNumOfSheet = 1; rowNumOfSheet <= aSheet.getLastRowNum(); rowNumOfSheet++) {
							
							if (null != aSheet.getRow(rowNumOfSheet)) {
								XSSFRow aRow = aSheet.getRow(rowNumOfSheet);// 定义行，并赋值
								
								// 进入当前sheet的行的列循环
								for (int cellNumOfRow = 0; cellNumOfRow <= aRow.getLastCellNum(); cellNumOfRow++) {
									
									// 读取rowNumOfSheet值所对应行的数据
									XSSFCell xCell = aRow.getCell(cellNumOfRow); // 获得行的列数
																					// //获得列值
									if (null != aRow.getCell(cellNumOfRow)) {
										// 如果rowNumOfSheet的值为1，则读取表头，判断excel的格式和预定格式是否相符
										if (rowNumOfSheet == 1) {
											if (xCell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
												//循环接收表头添加到数组中
												String materialHeader=xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
												
												 //一下根据从Excel的各列命名是否符合要求
												if(!materialHeader.equals(tableHeader[cellNumOfRow])){
													msg = "导入数据与模板不符，导入失败！";
													break outterLoop;
												}
											}
										} else {
											// rowNumOfSheet != 0 即开始打印内容
											// 获取excel中每列的值，并赋予相应的变量，如下的赋值的ID，name,
											if (xCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) { // 为数值型
												System.out.println("===============进入XSSFCell .CELL_TYPE_NUMERIC模块============");
												if(cellNumOfRow == 0){
													msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的面料编码";
													break outterLoop;
												}else if(cellNumOfRow == 1){
													msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的面料名称";
													break outterLoop;
												}else if(cellNumOfRow == 2){
													msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的面料适用季节";
													break outterLoop;
												}else if(cellNumOfRow == 3){
													//接收Excel中的colorCount颜色数量
													colorCount  = (int) xCell.getNumericCellValue(); 
													
													//判断颜色数量是否符合格式
													Pattern p = Pattern.compile("^[1-9]\\d*$");//为正整数
													Matcher m = p.matcher(String.valueOf(colorCount)); 
													if(!m.matches()){ 
														msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的颜色数量";
														break outterLoop;
													}
													pointer++;	
												}else if(cellNumOfRow == 4){
													msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的面料类型";
													break outterLoop;
												}else if(cellNumOfRow == 5){	
													//接收Excel中的width面料幅宽
													width  = xCell.getNumericCellValue(); 
													
													//判断面料幅宽是否符合格式
													Pattern p = Pattern.compile("^(([1-9]\\d{0,9})|0)(\\.\\d{1,2})?$");
													Matcher m = p.matcher(String.valueOf(width)); 
													if(!m.matches()){ 
														msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的面料编码";
														break outterLoop;
													}
													pointer++;	
												}else if(cellNumOfRow == 6){
													//接收Excel中的weigth面料克重
													weigth  = xCell.getNumericCellValue(); 
													
													//判断面料克重是否符合格式
													Pattern p = Pattern.compile("^(([1-9]\\d{0,9})|0)(\\.\\d{1,2})?$");
													Matcher m = p.matcher(String.valueOf(weigth)); 
													if(!m.matches()){ 
														msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的面料编码";
														break outterLoop;
													}
													pointer++;	
												}else if(cellNumOfRow==7){
													//接收Excel中的shrinkW面料横向收缩率
													shrinkW  = xCell.getNumericCellValue(); 
													
													//判断面料横向收缩率是否符合格式
													Pattern p = Pattern.compile("^0*\\.\\d+$");//0-1之间的小数
													Matcher m = p.matcher(String.valueOf(shrinkW)); 
													if(!m.matches()){ 
														msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的面料横向收缩率";
														break outterLoop;
													}
													pointer++;	
												}else if(cellNumOfRow==8){
													//接收Excel中的shrinkH面料纵向收缩率
													shrinkH  = xCell.getNumericCellValue(); 
													
													//判断面料纵向收缩率是否符合格式
													Pattern p = Pattern.compile("^0*\\.\\d+$");
													Matcher m = p.matcher(String.valueOf(shrinkH)); 
													if(!m.matches()){ 
														msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的面料纵向收缩率";
														break outterLoop;
													}
													pointer++;	
												}else if(cellNumOfRow==9){
													//接收Excel中的mtlPrice面料价格
													
													mtlPrice  = xCell.getNumericCellValue();
													pointer++;	
												}else if(cellNumOfRow==10){
													//接收Excel中的mtlNtxPrice面料不含税价
													
													mtlNtxPrice  = xCell.getNumericCellValue();
													pointer++;	
												}else if(cellNumOfRow==11){
													msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的价格单位";
													break outterLoop;
												}else if(cellNumOfRow==11){
													//接收Excel中的amount面料库存
													
													amount = (int) xCell.getNumericCellValue();
													pointer++;
												}else if(cellNumOfRow==12){
													//接收Excel中的minOrder最小周期
													
													minOrder = (int) xCell.getNumericCellValue();
													pointer++;
												}else if(cellNumOfRow==13){
													//接收Excel中的prdCycle生成周期
													
													prdCycle=(int) xCell.getNumericCellValue();
													pointer++;	
												}else if(cellNumOfRow==14){
													Date creat = (Date) xCell.getDateCellValue(); // 对日期处理
													createDt = new Timestamp(creat.getTime());
													pointer++;	
												}
												
											} else if (xCell.getCellType() == XSSFCell.CELL_TYPE_STRING) { // 为字符串型
												System.out.println("===============进入XSSFCell .CELL_TYPE_STRING模块============");
												
												if(cellNumOfRow == 0){	
													//接收Excel中的mtlCode
													mtlCode = xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
													
													//判断编码是否符合格式
													Pattern p = Pattern.compile("^\\d{9}$");
													Matcher m = p.matcher(mtlCode); 
													if(!m.matches()){ 
														msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的面料编码";
														break outterLoop;
													}
													pointer++;	
												}else if(cellNumOfRow == 1){
													//接收Excel中的mtlName
													
													mtlName = xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
													pointer++;	
												}else if(cellNumOfRow == 2){
													//接收Excel中的season
													season = xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
													 
													 //判断季节是否合格式
													 Pattern p = Pattern.compile("^[\\u4e00-\\u9fa5]{1,2}$");
													 Matcher m = p.matcher(season); 
													 if(!m.matches()){ 
														msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的面料季节";
														break outterLoop;
													 }
													 pointer++;	
												}else if(cellNumOfRow==3){
													msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的颜色数量";
													break outterLoop;
												}else if(cellNumOfRow==4){
													//接收Excel中的mtlType面料类型
													
													 mtlType = xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
													if(typelist.contains(mtlType)){
														pointer++;
													}else{
														msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的类型不存";
														 break outterLoop;
													}
												}else if(cellNumOfRow==5){
													 msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的面料幅宽";
													 break outterLoop;
												}else if(cellNumOfRow==6){
													 msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的面料克重";
													 break outterLoop;
												}else if(cellNumOfRow==7){
													 msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的面料横向收缩率";
													 break outterLoop;	
												}else if(cellNumOfRow==8){
													 msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的面料纵向收缩率";
													 break outterLoop;
												}else if(cellNumOfRow==9){
													 msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的面料单价";
													 break outterLoop;
												}else if(cellNumOfRow==10){
													 msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的不含税单价";
													 break outterLoop;
												}else if(cellNumOfRow==11){
													//接收Excel中的mtlUnit面料类型
													
													mtlUnit = xCell.getStringCellValue().replace('\t', ' ').replace('\n', ' ').replace('\r', ' ').trim();
													pointer++;
												}else if(cellNumOfRow==12){
													 msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的库存数量";
													 break outterLoop;
												}else if(cellNumOfRow==13){
													 msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的最小订单量";
													 break outterLoop;
												}else if(cellNumOfRow==14){
													msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的生成周期";
													break outterLoop;
												}else if(cellNumOfRow==15){
													 msg="错误：在Sheet"+(numSheets+1)+"中的第"+(rowNumOfSheet+1)+"行的第"+(cellNumOfRow+1)+"列的创建时间";
													 break outterLoop;
												}
												
											} else if (xCell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
												switch (cellNumOfRow) {
												case 0:
													mtlCode=null;	pointer++;	break;
												case 1:
													mtlName=null;	pointer++;	break;
												case 2:
													season=null;	pointer++;	break;
												case 3:
													colorCount=null;	pointer++;	break;	
												case 4:
													mtlType=null;  pointer++;	break;
												case 5:
													width=null;    pointer++;	break;
												case 6:
													weigth=null;   pointer++;	break;
												case 7:
													shrinkW=null;	pointer++;	break;
												case 8:
													shrinkH=null;	pointer++;	break;
												case 9:
													mtlPrice=null;	pointer++;	break;
												case 10:	
													mtlNtxPrice=null;	pointer++;	break;
												case 11:
													mtlUnit=null;	pointer++;	break;
												case 12:
													amount=null;	pointer++;	break;
												case 13:
													minOrder=null;	pointer++;	break;
												case 14:
													prdCycle=null;	pointer++;	break;
												case 15:
													createDt=null;	pointer++;	break;
												}
											}
										}
									}
								}
								// 判断各个元素被赋值,如果放入数据库，就直接使用数据的插入的函数就可以了。
								if (aRow.getRowNum() > 1&&pointer==15) {
									
									BMaterial bm=new BMaterial();
									
									bm.setMtlCode(mtlCode);
									bm.setMtlName(mtlName);
									bm.setSeason(season);
//									bm.getBMaterialType().setMtlType(mtlType);
									bm.setMtlPrice(mtlPrice);
									bm.setMtlNtxPrice(mtlNtxPrice);
									bm.setMtlUnit(mtlUnit);
									bm.setPrdCycle(prdCycle);
									bm.setShrinkH(shrinkH);
									bm.setShrinkW(shrinkW);
									bm.setWeigth(weigth);
									bm.setWidth(width);
									bm.setAmount(amount);
									bm.setMinOrder(minOrder);
									bm.setCreateDt(createDt);
									bm.setSysDt(sysDt);
									bm.setSysUser(sysUser);
								
									bmaterialList.add(bm);

								}
							} // 获得一行，即读取每一行
						}
						// 读取每一个sheet
					}
				}
				
				refreshList = "materialgetMaterialList.action";
				titleName = "面料档案列表";
				
				if(msg==null){
					//调用sever方法
				   isImpSuccess = bmaterialBiz.addOneBoat(bmaterialList,500);
					if(isImpSuccess){
						msg = "面料档案导入成功！";
					}else{
						msg = "面料档案导入失败！";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return "importExcel";
	}
	
	
	
	
	/**
	 *Excel模板下载
	 */
	public String importTemplate() throws Exception {
		/*
		 * 设置表头：对Excel每列取名(必须根据你取的数据编写)
		 */
		String[] tableHeader = { "面料编码","面料名称", "季节","颜色数量", "面料类型", "幅宽","克重", "横向缩率","横向缩率","含税单价",
				"不含税单价","价格单位","库存数量","最小订单量","生成周期","开发时间"};
		
		/**
		 * 设置表头的宽度
		 */
		int[] headerWidths = new int[tableHeader.length];
		for(int i=0; i < tableHeader.length; i++){
			headerWidths[i] = tableHeader[i].length()*2;
		}

		short cellNumber = (short) tableHeader.length;// 表的列数
		XSSFWorkbook workbook = new XSSFWorkbook(); // 创建一个excel
		XSSFCell cell = null; // Excel的列
		XSSFRow row = null; // Excel的行
		XSSFCellStyle style = workbook.createCellStyle(); // 设置表头的类型
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		XSSFCellStyle style1 = workbook.createCellStyle(); // 设置数据类型
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		XSSFFont font = workbook.createFont(); // 设置字体
		XSSFSheet sheet = workbook.createSheet("sheet1"); // 创建一个sheet
		Header header = sheet.getHeader();// 设置sheet的头

		/**
		 * 设置标题样式
		 */

		// 设置字体  
		XSSFFont headfont = workbook.createFont();  
		headfont.setFontName("黑体");  
		headfont.setFontHeightInPoints((short) 22);// 字体大小  
		headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗

		//设置行
		XSSFCellStyle headstyle = workbook.createCellStyle();  
		headstyle.setFont(headfont);  
		headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中  
		headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中  
		headstyle.setLocked(true);  
		headstyle.setWrapText(true);// 自动换行 

		// 创建第一行  
		XSSFRow row0 = sheet.createRow(0);
		// 设置行高  
		row0.setHeight((short) 900);  
		// 创建第一列  
		XSSFCell cell0 = row0.createCell(0);  
		cell0.setCellValue("面料录入表");  
		cell0.setCellStyle(headstyle);  

		//合并单元格 
		CellRangeAddress range = new CellRangeAddress(0, 0, 0, cellNumber);  
		sheet.addMergedRegion(range);  


		/**
		 * 根据是否取出数据，设置header信息
		 * 
		 */
		row = sheet.createRow(1);
		row.setHeight((short) 400);
		for (int k = 0; k < cellNumber; k++) {
			cell = row.createCell(k);// 创建第0行第k列
			cell.setCellValue(tableHeader[k]);// 设置第0行第k列的值
			sheet.setColumnWidth(k, headerWidths[k]*256);// 设置列的宽度
			font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色.
			font.setFontHeightInPoints((short) 11);// 设置字体大小
			style1.setFont(font);// 设置字体风格
			style.setFont(font);// 设置字体风格
			cell.setCellStyle(style1);
		}

		/*
		 * 下面的可以不用编写，直接拷贝
		 */
		HttpServletResponse response = null;// 创建一个HttpServletResponse对象
		OutputStream out = null;// 创建一个输出流对象
		try {
			response = ServletActionContext.getResponse();// 初始化HttpServletResponse对象
			out = response.getOutputStream();//
			response.setHeader("Content-disposition", "attachment; filename="
					+ "material.xlsx");// filename是下载的xls的名，建议最好用英文
			response.setContentType("application/msexcel;charset=UTF-8");// 设置类型
			response.setHeader("Pragma", "No-cache");// 设置头
			response.setHeader("Cache-Control", "no-cache");// 设置头
			response.setDateHeader("Expires", 0);// 设置日期头
			workbook.write(out);
			out.flush();
			workbook.write(out);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				if (out != null) {
					out.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		return null;
	}
	
	
	/**
	 *Excel导出
	 */
	public String getexport() throws Exception {
		
		/*
		 * 设置表头：对Excel每列取名(必须根据你取的数据编写)
		 */
		
		String[] tableHeader = { "面料编码","面料名称", "季节","颜色数量", "面料类型", "幅宽","克重", "横向缩率","纵向缩率","含税单价",
				"不含税单价","价格单位","库存数量","最小订单量","生成周期","开发时间"};
		
		/**
		 * 设置表头的宽度
		 */
		int[] headerWidths = new int[tableHeader.length];
		for(int i=0; i < tableHeader.length; i++){
		    headerWidths[i] = tableHeader[i].length()*2;
		}
		/*
		 * 下面的都可以拷贝不用编写
		 */
		short cellNumber = (short) tableHeader.length;// 表的列数
		XSSFWorkbook workbook = new XSSFWorkbook(); // 创建一个excel
		XSSFCell cell = null; // Excel的列
		XSSFRow row = null; // Excel的行
		XSSFCellStyle style = workbook.createCellStyle(); // 设置表头的类型
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		XSSFCellStyle style1 = workbook.createCellStyle(); // 设置数据类型
		style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		XSSFFont font = workbook.createFont(); // 设置字体
		XSSFSheet sheet = workbook.createSheet("sheet1"); // 创建一个sheet
		Header header = sheet.getHeader();// 设置sheet的头
		
		try {
			/**
			 * 设置标题样式
			 */
			
			    // 设置字体  
		    	XSSFFont headfont = workbook.createFont();  
		    	headfont.setFontName("黑体");  
		    	headfont.setFontHeightInPoints((short) 22);// 字体大小  
		    	headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		    	
		    	//设置行
		    	XSSFCellStyle headstyle = workbook.createCellStyle();  
		    	headstyle.setFont(headfont);  
		    	headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中  
		    	headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 上下居中  
		    	headstyle.setLocked(true);  
		    	headstyle.setWrapText(true);// 自动换行 
				
		    	// 创建第一行  
				XSSFRow row0 = sheet.createRow(0);
				// 设置行高  
		        row0.setHeight((short) 900);  
		        // 创建第一列  
		        XSSFCell cell0 = row0.createCell(0);  
		        cell0.setCellValue("营销活动类型管理表");  
		        cell0.setCellStyle(headstyle);  
		     
		        /** 
		         * 合并单元格 
		         */  
		        CellRangeAddress range = new CellRangeAddress(0, 0, 0, cellNumber);  
		        sheet.addMergedRegion(range);  
			
			
			/**
			 * 根据是否取出数据，设置header信息
			 * 
			 */
			if (ebmaterialList.size() < 1) {
				header.setCenter("查无资料");
			} else {
				header.setCenter("营销活动类型表");
				row = sheet.createRow(1);
				row.setHeight((short) 400);
				for (int k = 0; k < cellNumber; k++) {
					cell = row.createCell(k);// 创建第0行第k列
					cell.setCellValue(tableHeader[k]);// 设置第0行第k列的值
					sheet.setColumnWidth(k, headerWidths[k]*256);// 设置列的宽度
					font.setColor(HSSFFont.COLOR_NORMAL); // 设置单元格字体的颜色.
					font.setFontHeightInPoints((short) 10);// 设置字体大小
					style1.setFont(font);// 设置字体风格
					style.setFont(font);// 设置字体风格
					cell.setCellStyle(style1);
				}
				
				/*
				 * 给excel填充数据这里需要编写
				 */
				for (int i = 0; i < ebmaterialList.size(); i++) {
					ExtraBMaterial exBMater = (ExtraBMaterial) ebmaterialList.get(i);// 获取面料对象
					row = sheet.createRow((short) (i + 2));// 创建第i+1行
					row.setHeight((short) 400);// 设置行高
					
					//面料编码
					if (exBMater.getMtlCode() != null) {
						cell = row.createCell(0);// 创建第i+1行第0列
						cell.setCellValue(exBMater.getMtlCode());// 设置第i+1行第0列的值
						cell.setCellStyle(style);// 设置风格
					}
					
					//面料名称
					if (exBMater.getMtlName() != null) {
						cell = row.createCell(1); // 创建第i+1行第1列
						cell.setCellValue(exBMater.getMtlName());// 设置第i+1行第1列的值
						cell.setCellStyle(style); // 设置风格
					}
					
					//季节
					if (exBMater.getSeason() != null) {
						cell = row.createCell(2); // 创建第i+1行第2列
						cell.setCellValue(exBMater.getSeason());// 设置第i+1行第2列的值
						cell.setCellStyle(style); // 设置风格
					}
					
					//颜色数量
					if (exBMater.getColorCount() != null) {
						cell = row.createCell(3); // 创建第i+1行第3列
						cell.setCellValue(exBMater.getColorCount());// 设置第i+1行第3列的值
						cell.setCellStyle(style); // 设置风格
					}
					
					//面料类型
					if (exBMater.getMtlType() != null) {
						cell = row.createCell(4); // 创建第i+1行第4列
						cell.setCellValue(exBMater.getMtlType());// 设置第i+1行第4列的值
						cell.setCellStyle(style); // 设置风格
					}
					
					//幅宽
					if (exBMater.getWidth() != null) {
						cell = row.createCell(5); // 创建第i+1行第5列
						cell.setCellValue(exBMater.getWidth());// 设置第i+1行第5列的值
						cell.setCellStyle(style); // 设置风格
					}
					
					//克重
					if (exBMater.getWeigth() != null) {
						cell = row.createCell(6); // 创建第i+1行第7列
						cell.setCellValue(exBMater.getWeigth());// 设置第i+1行第7列的值
						cell.setCellStyle(style); // 设置风格
					}
					
					//横向缩率
					if (exBMater.getShrinkH() != null) {
						cell = row.createCell(7); // 创建第i+1行第6列
						cell.setCellValue(exBMater.getShrinkH());// 设置第i+1行第6列的值
						cell.setCellStyle(style); // 设置风格
					}
					
					//纵向缩率
					if (exBMater.getShrinkW() != null) {
						cell = row.createCell(8); // 创建第i+1行第8列
						cell.setCellValue(exBMater.getShrinkW());// 设置第i+1行第8列的值
						cell.setCellStyle(style); // 设置风格
					}
					
					//含税单价
					if (exBMater.getMtlPrice() != null) {
						cell = row.createCell(9); // 创建第i+1行第9列
						cell.setCellValue(exBMater.getMtlPrice());// 设置第i+1行第9列的值
						cell.setCellStyle(style); // 设置风格
					}
					
					//不含税单价
					if (exBMater.getMtlNtxPrice()!= null) {
						cell = row.createCell(10); // 创建第i+1行第10列
						cell.setCellValue(exBMater.getMtlNtxPrice());// 设置第i+1行第9列的值
						cell.setCellStyle(style); // 设置风格
					}
					
					//价格单位
					if (exBMater.getMtlUnit() != null) {
						cell = row.createCell(11); // 创建第i+1行第11列
						cell.setCellValue(exBMater.getMtlUnit());// 设置第i+1行第9列的值
						cell.setCellStyle(style); // 设置风格
					}
					
					//库存数量
					if (exBMater.getAmount() != null) {
						cell = row.createCell(12); // 创建第i+1行第12列
						cell.setCellValue(exBMater.getAmount());// 设置第i+1行第9列的值
						cell.setCellStyle(style); // 设置风格
					}
					
					//最小订单量
					if (exBMater.getMinOrder() != null) {
						cell = row.createCell(13); // 创建第i+1行第13列
						cell.setCellValue(exBMater.getMinOrder());// 设置第i+1行第9列的值
						cell.setCellStyle(style); // 设置风格
					}
					
					//生成周期
					if (exBMater.getPrdCycle() != null) {
						cell = row.createCell(14); // 创建第i+1行第14列
						cell.setCellValue(exBMater.getPrdCycle());// 设置第i+1行第9列的值
						cell.setCellStyle(style); // 设置风格
					}
					
					//开发时间
					if (exBMater.getCreateDt() != null) {
						cell = row.createCell(15); // 创建第i+1行第15列
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//定义格式
						String time =df.format(exBMater.getCreateDt());
						cell.setCellValue(time);// 设置第i+1行第8列的值
						cell.setCellStyle(style); // 设置风格
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * 下面的可以不用编写，直接拷贝
		 */
		HttpServletResponse response = null;// 创建一个HttpServletResponse对象
		OutputStream out = null;// 创建一个输出流对象
		try {
			response = ServletActionContext.getResponse();// 初始化HttpServletResponse对象
			out = response.getOutputStream();//
			response.setHeader("Content-disposition", "attachment; filename="
					+"BMaterial.xlsx");// filename是下载的xls的名，建议最好用英文
			response.setContentType("application/msexcel;charset=UTF-8");// 设置类型
			response.setHeader("Pragma", "No-cache");// 设置头
			response.setHeader("Cache-Control", "no-cache");// 设置头
			response.setDateHeader("Expires", 0);// 设置日期头
			workbook.write(out);
			out.flush();
			workbook.write(out);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				if (out != null) {
					out.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		return null;
	}
}
