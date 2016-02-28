package com.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.bean.BMaterial;
import com.bean.BMaterialSupplier;
import com.bean.Suin;
import com.opensymphony.xwork2.ActionSupport;
import com.serviceImpl.BMaterialSupplierSerivceImpl;
import com.serviceImpl.SuinServiceImpl;
import com.serviceImpl.UtilSupport;

@SuppressWarnings("serial")
public class BMaterialSupplierAction extends ActionSupport {
	private UtilSupport util;		//公用类
	private BMaterialSupplierSerivceImpl bmaterialSupplierBiz;
	private SuinServiceImpl suinBiz;
	private List<BMaterialSupplier> allSupplierList;//获取所有供应商信息
	private List<BMaterialSupplier> getSupListByOption;//根据条件获取所有供应商信息
	private BMaterialSupplier bmtlSupplier;
	private BMaterialSupplier editSupplier;
	private HttpServletRequest request;
	private boolean flag;
	private Map<String, Object> jsonResult;
	private List<Suin> allSuin;
	
	private int offset;			//当前页
	private int pageSize=10;	//每页显示条数
	private int totalcount;		// 总记录数
	private int totalpage; 		// 总页数
	
	//查询条件
	private String qsupplierName;	//供应商名称
	private String qmtlName;		//面料名称
	private String qmtlCode;		//面料编码
	//供应方颜色数量区间
	private Integer qminColorCount;	//供应方颜色数量
	private Integer qmaxColorCount;	//供应方颜色数量
	
	//参数
	private Integer qmtsId;
	private String psuid;
	private Integer mtlIdCode;
	
	public BMaterialSupplierAction() {
		allSupplierList = new ArrayList<BMaterialSupplier>();
		getSupListByOption = new ArrayList<BMaterialSupplier>();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public UtilSupport getUtil() {
		return util;
	}
	
	public void setUtil(UtilSupport util) {
		this.util = util;
	}
	
	public BMaterialSupplierSerivceImpl getBmaterialSupplierBiz() {
		return bmaterialSupplierBiz;
	}

	public void setBmaterialSupplierBiz(
			BMaterialSupplierSerivceImpl bmaterialSupplierBiz) {
		this.bmaterialSupplierBiz = bmaterialSupplierBiz;
	}

	public SuinServiceImpl getSuinBiz() {
		return suinBiz;
	}

	public void setSuinBiz(SuinServiceImpl suinBiz) {
		this.suinBiz = suinBiz;
	}

	public List<BMaterialSupplier> getAllSupplierList() {
		return allSupplierList;
	}
	
	public void setAllSupplierList(List<BMaterialSupplier> allSupplierList) {
		this.allSupplierList = allSupplierList;
	}
	
	public List<BMaterialSupplier> getGetSupListByOption() {
		return getSupListByOption;
	}

	public void setGetSupListByOption(List<BMaterialSupplier> getSupListByOption) {
		this.getSupListByOption = getSupListByOption;
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
	
	public String getQsupplierName() {
		return qsupplierName;
	}

	public void setQsupplierName(String qsupplierName) {
		this.qsupplierName = qsupplierName;
	}

	public String getQmtlName() {
		return qmtlName;
	}

	public void setQmtlName(String qmtlName) {
		this.qmtlName = qmtlName;
	}

	public String getQmtlCode() {
		return qmtlCode;
	}

	public void setQmtlCode(String qmtlCode) {
		this.qmtlCode = qmtlCode;
	}

	public Integer getQminColorCount() {
		return qminColorCount;
	}

	public void setQminColorCount(Integer qminColorCount) {
		this.qminColorCount = qminColorCount;
	}

	public Integer getQmaxColorCount() {
		return qmaxColorCount;
	}

	public void setQmaxColorCount(Integer qmaxColorCount) {
		this.qmaxColorCount = qmaxColorCount;
	}

	public BMaterialSupplier getBmtlSupplier() {
		return bmtlSupplier;
	}

	public void setBmtlSupplier(BMaterialSupplier bmtlSupplier) {
		this.bmtlSupplier = bmtlSupplier;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public Integer getQmtsId() {
		return qmtsId;
	}

	public void setQmtsId(Integer qmtsId) {
		this.qmtsId = qmtsId;
	}

	public Integer getMtlIdCode() {
		return mtlIdCode;
	}

	public void setMtlIdCode(Integer mtlIdCode) {
		this.mtlIdCode = mtlIdCode;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Map<String, Object> getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(Map<String, Object> jsonResult) {
		this.jsonResult = jsonResult;
	}

	public List<Suin> getAllSuin() {
		return allSuin;
	}

	public void setAllSuin(List<Suin> allSuin) {
		this.allSuin = allSuin;
	}

	public BMaterialSupplier getEditSupplier() {
		return editSupplier;
	}

	public void setEditSupplier(BMaterialSupplier editSupplier) {
		this.editSupplier = editSupplier;
	}

	public String getPsuid() {
		return psuid;
	}

	public void setPsuid(String psuid) {
		this.psuid = psuid;
	}

	// 填充 PGroupUser 对像 List
	private void fillSupList(List<Object[]> resultSet) {
		getSupListByOption.clear();
		if(resultSet.size()>0){
			for (Object[] r : resultSet) {
				BMaterialSupplier supplier = (BMaterialSupplier)r[0];
				BMaterial bm = (BMaterial)r[1];
				Suin suin = (Suin)r[2];
				supplier.setBMaterial(bm);
				supplier.setSuin(suin);
				getSupListByOption.add(supplier);
			}
		}
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
	
	private void getSuppers(){
		try {
			request = ServletActionContext.getRequest();
			allSuin = suinBiz.getAllSuin();	//获取所有供应商信息
			
			StringBuffer sql=new StringBuffer("select * from b_Material_Supplier ms " +
						"inner join b_Material m on ms.mtl_id = m.mtl_id " +
						"inner join Suin s on s.suid=ms.suid where 0 = 0 ");
			
			sql.append(" and m.mtl_id = "+mtlIdCode+" ");
			
			this.qsupplierName = request.getParameter("qsupplierName");
			if(qsupplierName!=null&&!qsupplierName.isEmpty()){
				qsupplierName=new String(qsupplierName.trim().getBytes("ISO-8859-1"),"UTF-8");
				sql.append(" and s.suna = '"+qsupplierName+"'");
			}
			
			this.qmtlCode = request.getParameter("qmtlCode");
			if(qmtlCode!=null&&!qmtlCode.isEmpty()){
				qmtlCode=new String(qmtlCode.trim().getBytes("ISO-8859-1"),"UTF-8");
				sql.append(" and ms.mts_code like '%"+qmtlCode+"%'");
			}
			
			this.qmtlName = request.getParameter("qmtlName");
			if(qmtlName!=null&&!qmtlName.isEmpty()){
				qmtlName=new String(qmtlName.trim().getBytes("ISO-8859-1"),"UTF-8");
				sql.append(" and ms.mts_name like '%"+qmtlName+"%'");
			}
			
			String minColorCount = request.getParameter("qminColorCount");
			String maxColorCount = request.getParameter("qmaxColorCount");
			
			if(minColorCount!=null&&!minColorCount.isEmpty()){
				this.qminColorCount = Integer.parseInt(minColorCount);
				if(maxColorCount!=null&&!maxColorCount.isEmpty()){
					this.qmaxColorCount = Integer.parseInt(maxColorCount);
					sql.append(" and ms.mts_color_count between "+qminColorCount+" and "+qmaxColorCount+"");
				}else{
					sql.append(" and ms.mts_color_count > "+qminColorCount+"");
				}
			}else{
				if(maxColorCount!=null&&!maxColorCount.isEmpty()){
					sql.append(" and ms.mts_color_count < "+qmaxColorCount+"");
				}
			}
			
			totalcount = util.getTotalCount(sql.toString());		//获取总条数

			totalpage = totalcount % pageSize == 0 ? totalcount / pageSize 
					: totalcount / pageSize + 1;		//获取总页数

			offset = getPageOffset();

			List<Object[]> resultSet = util.getPageListBySql(sql.toString(),String.valueOf(offset),String.valueOf(pageSize),
					new Class[]{BMaterialSupplier.class,BMaterial.class,Suin.class});
			fillSupList(resultSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取面料对应供应方信息
	 * @return
	 * @throws Exception
	 */
	public String loadAllSupperList(){
		getSuppers();
		return "editSupOperation";
	}
	
	public String getSupperDetail(){
		getSuppers();
		return "detailSupOperation";
	}
	
	/**
	 * 删除供应商
	 * @return
	 */
	public String delSupplier(){
		try {
			jsonResult = new HashMap<String, Object>();
			//根据供应商ID获取供应商信息
			BMaterialSupplier supplier = bmaterialSupplierBiz.findSupplierById(qmtsId);
			//删除供应商信息
			bmaterialSupplierBiz.delSupplier(supplier);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		jsonResult.put("flag", flag);
		jsonResult.put("mtlId", mtlIdCode);
		return "getSuppliers";
	}
	
	/**
	 * 添加供应商信息
	 * @return
	 */
	public String addSupplier() throws Exception{
		jsonResult = new HashMap<String, Object>();
		try {
			bmtlSupplier = new BMaterialSupplier();
			flag = true;
			HttpServletRequest request = ServletActionContext.getRequest();
			String suin = request.getParameter("suid");  
			String suna = request.getParameter("suna"); 
			String mtsName = request.getParameter("mtsName");
			Integer mtsColorCount = null;
			String colorCountStr = request.getParameter("mtsColorCount");
			if(colorCountStr!=null&&colorCountStr!=""){
				mtsColorCount = Integer.parseInt(colorCountStr); 
			}
			String mtsCode = request.getParameter("mtsCode");  
			
			bmtlSupplier.setSuin(new Suin(suin,suna));
			bmtlSupplier.setBMaterial(new BMaterial(mtlIdCode));
			bmtlSupplier.setMtsCode(mtsCode);
			bmtlSupplier.setMtsName(mtsName);
			bmtlSupplier.setMtsColorCount(mtsColorCount);
			
			bmaterialSupplierBiz.saveSupplier(bmtlSupplier);
			
		} catch (Exception e) {
			flag = false;
		}
		jsonResult.put("flag", flag);
		jsonResult.put("mtlId", mtlIdCode);
		return "getSuppliers";
	}
	
	/**
	 * 供应商修改操作
	 * @return
	 */
	public String editSupplierOpt(){
		editSupplier = bmaterialSupplierBiz.findSupplierById(qmtsId);
		return "editSuppliers";
	}
	
	/**
	 * 修改供应商信息
	 * @return
	 */
	public String mergerSupplier(){
		try {
			bmaterialSupplierBiz.mergerSupplier(editSupplier);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return SUCCESS;
	}

}
