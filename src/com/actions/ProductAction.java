package com.actions;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.bean.Product;
import com.opensymphony.xwork2.ActionSupport;
import com.serviceImpl.ProductServiceImpl;
import com.serviceImpl.UtilSupport;

public class ProductAction extends ActionSupport {

	private UtilSupport util;
	private ProductServiceImpl productBiz;
	private List<Product> allProducts;
	private Integer mtlId;
	
	private int offset;			//当前页
	private int pageSize=10;
	private int totalcount;		// 总记录数
	private int totalpage; 		// 总页数
	
	//查询条件字段
	private String productCd;	//产品编码
	private String spno;   		//产品定位
	private Timestamp jhdt;  	//上架时间
	private Timestamp xjdt;		//下架时间
	private String brad;		//品牌

	public ProductServiceImpl getProductBiz() {
		return productBiz;
	}

	public void setProductBiz(ProductServiceImpl productBiz) {
		this.productBiz = productBiz;
	}
	
	public List<Product> getAllProducts() {
		return allProducts;
	}

	public void setAllProducts(List<Product> allProducts) {
		this.allProducts = allProducts;
	}

	public Integer getMtlId() {
		return mtlId;
	}

	public void setMtlId(Integer mtlId) {
		this.mtlId = mtlId;
	}

	public String getProductCd() {
		return productCd;
	}

	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}

	public String getSpno() {
		return spno;
	}

	public void setSpno(String spno) {
		this.spno = spno;
	}

	public Timestamp getJhdt() {
		return jhdt;
	}

	public void setJhdt(Timestamp jhdt) {
		this.jhdt = jhdt;
	}

	public Timestamp getXjdt() {
		return xjdt;
	}

	public void setXjdt(Timestamp xjdt) {
		this.xjdt = xjdt;
	}

	public String getBrad() {
		return brad;
	}

	public void setBrad(String brad) {
		this.brad = brad;
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

	/**
	 * 获取所有产品信息
	 * @return
	 */
	public String getAllProduct(){
		HttpServletRequest request = ServletActionContext.getRequest();
 		StringBuffer sqlbf = new StringBuffer("select * from product where product_code not in " +
							"(select product_code from b_Material_Relate_Product where mtl_id="+mtlId+")");
		
		try {
			this.productCd = request.getParameter("productCd");
			if(productCd!=null&&!productCd.isEmpty()){
				productCd=new String(productCd.trim().getBytes("ISO-8859-1"),"UTF-8");
				sqlbf.append(" and product_code like '%"+productCd+"%'");
			}

			this.brad = request.getParameter("brad");
			if(brad!=null&&!brad.isEmpty()){
				brad=new String(brad.trim().getBytes("ISO-8859-1"),"UTF-8");
				sqlbf.append(" and brad = '"+productCd+"'");
			}

			this.spno = request.getParameter("spno");
			if(spno!=null&&!spno.isEmpty()){
				spno=new String(spno.trim().getBytes("ISO-8859-1"),"UTF-8");
				sqlbf.append(" and spno = '"+spno+"'");
			}

			String jhdate=request.getParameter("jhdt");
			String xjdate=request.getParameter("xjdt");
			Calendar cal = Calendar.getInstance();

			if(jhdate!=null&&!jhdate.isEmpty()){
				this.jhdt=Timestamp.valueOf(jhdate);
				if(xjdate!=null&&!xjdate.isEmpty()){
					this.xjdt=Timestamp.valueOf(xjdate);
				}else{
					cal.setTime(jhdt);
					int day =  cal.get(Calendar.DATE); 
					int month =cal.get(Calendar.MONTH); 
					int year = cal.get(Calendar.YEAR) ;

					cal.set(year+5, month, day);
					year = cal.get(Calendar.YEAR) ;
					this.xjdt=new Timestamp(cal.getTimeInMillis());
				}
				sqlbf.append(" and ((p.jhdt >= '"+jhdt+"' and p.xjdt <= '"+xjdt+"') " +
						" or( jhdt >= '"+jhdt+"' and ('"+xjdt+"' between jhdt and xjdt))" +
						" or(('"+jhdt+"' between jhdt and xjdt ) and ('"+xjdt+"' between jhdt and xjdt))" +
						" or(('"+jhdt+"' between jhdt and xjdt ) and xjdt <= '"+xjdt+"'))");
			}else{
				if(xjdate!=null&&!xjdate.isEmpty()){
					this.xjdt=Timestamp.valueOf(xjdate);

					cal.setTime(xjdt);
					int day =  cal.get(Calendar.DATE); 
					int month =cal.get(Calendar.MONTH); 
					int year = cal.get(Calendar.YEAR) ;

					cal.set(year-5, month, day);
					this.jhdt=new Timestamp(cal.getTimeInMillis());

					sqlbf.append(" and ((jhdt >= '"+jhdt+"' and xjdt <= '"+xjdt+"') " +
							" or( jhdt >= '"+jhdt+"' and ('"+xjdt+"' between jhdt and xjdt))" +
							" or(('"+jhdt+"' between jhdt and xjdt ) and ('"+xjdt+"' between jhdt and xjdt))" +
							" or(('"+jhdt+"' between jhdt and xjdt ) and xjdt <= '"+xjdt+"'))");
				}
			}


			String sql =sqlbf.toString();
			totalcount = productBiz.getTotalCount(sql);

			totalpage = totalcount % pageSize == 0 ? totalcount / pageSize
					: totalcount / pageSize + 1;

			offset = getPageOffset();

			allProducts = util.getPageListBySql(sql.toString(),String.valueOf(offset),String.valueOf(pageSize),new Class[]{Product.class});
		} catch (Exception e) {
			// TODO: handle exception
		}
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
	
}
