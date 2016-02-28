package com.serviceImpl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;

@SuppressWarnings("rawtypes")
public class UtilSupport{
	private SessionFactory sessionFactory;
	private List list;
	private Query query;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	// String hql  ： 结果集查询话句
	// String page ： 结果集对应的页面码
	// String rows ： 结果集页面数据记录行数
	// 2015-11-9 Revised by JSL: 
	// 计算返回结果集时,计算公式从 (currentpage-1) * pagesize 改为 (currentpage) * pagesize
	public List getPageList(String hql, String page, String rows) {  
		//当为缺省值的时候进行赋值  
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页  
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行
		List list = this.sessionFactory.getCurrentSession().createQuery(hql)  
				.setFirstResult((currentpage) * pagesize).setMaxResults(pagesize).list();  
		return list;  
	} 

	public class PageInfo{
		public List pageRows;
		public int pageIndex;
		public int pageSize;
		public int totalRows;
		public int totalPages;
	}

	// String hql  ： 结果集查询话句
	// String pageIndex ： 结果集对应的页面码
	// String pageSize ： 结果集页面数据记录行数
	// 2015-11-9 Revised by JSL: 
	// 
	public PageInfo getPageListEx(String hql, String pageIndex, String pageSize) {  

		PageInfo pi  = new PageInfo();
		pi.pageSize  = (pageSize == null)? 10 : Integer.parseInt(pageSize);
		pi.pageIndex = Math.abs((pageIndex == null)? 0 : Integer.parseInt(pageIndex));

		Query qry = this.sessionFactory.getCurrentSession().createQuery(hql);
		List tmpList = qry.list();

		pi.totalRows  = tmpList.size();
		pi.totalPages = (int)Math.ceil((float)pi.totalRows/pi.pageSize);
		pi.pageIndex  = Math.min(pi.totalPages-1, pi.pageIndex);  // 修正页索引值

		int fromIndex = pi.pageIndex * pi.pageSize;
		int toIndex = Math.min(fromIndex + pi.pageSize, pi.totalRows);

		pi.pageRows = tmpList.subList(fromIndex, toIndex);

		return pi;  
	} 

	// String sql   ： 数据库查询语句，函数使用此 SQL 查询数据集
	// String nPage ：指定函数返回数据集的第 n 页数据
	// String rows  ：指定定每数据页的记录数
	// 2015-11-9 Revised by JSL: 
	// 
	public List getPageListBySql(String sql, String pageIndex, String pageSize, Class[] resultSetTypes) {  
		//当为缺省值的时候进行赋值  

		int psz  = (pageSize == null)? 10 : Integer.parseInt(pageSize);
		int idx = (pageIndex == null)? 0 : Integer.parseInt(pageIndex);

		SQLQuery query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		for(int i = 0; i < resultSetTypes.length; i++){
			query.addEntity(resultSetTypes[i]);
		}

		List tmpList = query.list();

		int totalRows = tmpList.size();
		int totalPages = (int)Math.ceil((float)totalRows/psz);
		idx = Math.min(totalPages-1, idx);

		int fromIndex = idx * psz;
		int toIndex = Math.min(fromIndex + psz, totalRows);

		if(tmpList.size()>0){
			list = tmpList.subList(fromIndex, toIndex);
		}else{
			list=new ArrayList();
		}
		return list;  
	}

	// 统计一共有多少数据  
	public int getTotalCount(String sql) throws Exception {  
		int count= this.sessionFactory.getCurrentSession().createSQLQuery(sql).list().size(); 
		return count;
	}  

	/**
	 * 根据条件查询
	 * @param className
	 * @param parms
	 * @return
	 */
	public List getLisByOptions(String className,String page, String rows,Map<String, String> parms,String order) throws Exception{
		int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页  
		int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行
		StringBuffer hql=new StringBuffer("from "+className+" where 0 = 0 ");
		Iterator it = parms.entrySet().iterator() ; 
		while (it.hasNext()) 
		{ 
			Map.Entry entry = (Map.Entry) it.next() ; 
			String key = entry.getKey().toString() ; 
			hql.append(" and "+key+" like ?");
		}
		query=this.sessionFactory.getCurrentSession().createQuery(hql.toString());
		if(order!=null||order!=""){
			hql.append(order);
		}
		it = parms.entrySet().iterator() ; 
		int i=0;
		while (it.hasNext()) 
		{ 
			Map.Entry entry = (Map.Entry) it.next() ; 
			String val = entry.getValue().toString() ; 
			query.setParameter(i, "%"+val+"%",Hibernate.STRING);
			i=i+1;
		}
		list=query.setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();
		return list;
	}

	/**
	 * 导入模板
	 */
	public String getTemplate(String[] Header,String name) throws Exception{
		/*
		 * 设置表头：对Excel每列取名(必须根据你取的数据编写)
		 */
		String[] tableHeader = Header;

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
		cell0.setCellValue(name+"管理表");  
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
					+ "template.xlsx");// filename是下载的xls的名，建议最好用英文
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
	 * 判断是否是中文乱码
	 */
	
	private boolean isChinese(char c) {  
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);  
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION  
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {  
            return true;  
        }  
        return false;  
    } 
	
	/**
	 * 判断是否乱码
	 * @param strName
	 * @return
	 */
	public boolean isMessyCode(String strName) {  
        Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");  
        Matcher m = p.matcher(strName);  
        String after = m.replaceAll("");  
        String temp = after.replaceAll("\\p{P}", "");  
        char[] ch = temp.trim().toCharArray();  
        float chLength = 0 ;  
        float count = 0;  
        for (int i = 0; i < ch.length; i++) {  
            char c = ch[i];  
            if (!Character.isLetterOrDigit(c)) {  
                if (!isChinese(c)) {  
                    count = count + 1;  
                }  
                chLength++;   
            }  
        }  
        float result = count / chLength ;  
        if (result > 0.4) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
}