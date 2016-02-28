package com.serviceImpl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.bean.BMaterial;
import com.bean.BMaterialType;
import com.bean.ExtraBMaterial;
import com.bean.ExtraType;
import com.dao.BMaterialDao;
import com.dao.BMaterialSupplierDao;
import com.dao.BMaterialTypeDao;

public class BMaterialServiceImpl {

	private BMaterialDao bmaterialDao;
	private BMaterialTypeDao bmaterialTypeDao;
	private BMaterialSupplierDao bmaterialSupplierDao; 
	private SessionFactory sessionFactory;

	public BMaterialTypeDao getBmaterialTypeDao() {
		return bmaterialTypeDao;
	}
	public void setBmaterialTypeDao(BMaterialTypeDao bmaterialTypeDao) {
		this.bmaterialTypeDao = bmaterialTypeDao;
	}
	public BMaterialDao getBmaterialDao() {
		return bmaterialDao;
	}

	public void setBmaterialDao(BMaterialDao bmaterialDao) {
		this.bmaterialDao = bmaterialDao;
	}

	public BMaterialSupplierDao getBmaterialSupplierDao() {
		return bmaterialSupplierDao;
	}
	
	public void setBmaterialSupplierDao(BMaterialSupplierDao bmaterialSupplierDao) {
		this.bmaterialSupplierDao = bmaterialSupplierDao;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
		/**
	 * 确认保存
	 */
	public void mergeBMaterial(BMaterial material){
		bmaterialDao.merge(material);
	}
	/**
	 * 添加面料草稿信息
	 */
	public void saveBMaterial(BMaterial material){
		bmaterialDao.save(material);
	}

	/**
	 * 删除面料信息（修改面料状态为0）
	 * @param mtlId
	 */
	public void deleteBMaterial(Integer mtlId){
		bmaterialDao.delete(mtlId);
	}
	
	/**
	 * 查询所有面料信息
	 * @return
	 */
	public List<BMaterial> bmaterialList(){
		return bmaterialDao.findAll();
	}

	/**
	 * 获取面料父类型
	 * @return
	 */
	public List<ExtraType> loadAllTypeList(){

		return bmaterialTypeDao.getAllTypeList();
	}
	
	public List<BMaterialType> allParentType(){
		return bmaterialTypeDao.loadParentTypeList();
	}

	/**
	 * 获取面料父类型下的子类型
	 * @return
	 */
	public List<BMaterialType> getMtlTypeListByPid(int pid){

		return bmaterialTypeDao.getMtlTypeListByPid(pid);
	}

	/**
	 * 获取所有面料子类型集合
	 * @return
	 */
	public List<ExtraType> getAllChildTypeList(){
		return bmaterialTypeDao.getAllChildTypeList();
	}
	
	/**
	 * 根据mtl_type获取面料信息截取mtl_code
	 */
	public String getMtlCodeByType(int mtlType){
		StringBuffer strb = new StringBuffer();
		List<BMaterial> mtlList = bmaterialDao.getMtlCodeByType(mtlType);
		List mtlNums = new ArrayList();
		for (int i = 0; i < mtlList.size(); i++) {
			String mtlCode = mtlList.get(i).getMtlCode().substring(4);
			int mtlCodeNum = Integer.parseInt(mtlCode);
			mtlNums.add(mtlCodeNum);
		}
		int maxNum = 0;
		if(mtlNums.size()>0){
			maxNum = Collections.max(mtlNums);
		}
		String str = new DecimalFormat("0000").format(maxNum+1);
		String mtlTypeStr = new DecimalFormat("00").format(mtlType);
		String yearLast = new SimpleDateFormat("yy",Locale.CHINESE).format(Calendar.getInstance().getTime());
		
		strb.append(yearLast);
		strb.append(mtlTypeStr);
		strb.append(str);
		return strb.toString();
	}
	
	/**
	 * 添加类型信息
	 * @param btype
	 */
	public void saveType(BMaterialType btype){
		bmaterialTypeDao.saveType(btype);
	}
	
	/**
	 * 删除类型信息
	 */
	public void deleteType(BMaterialType bmtype){
		bmaterialTypeDao.delete(bmtype);
	}
	
	public BMaterialType findMtlTypeByMtlType(Integer mtlType){
		BMaterialType bmtype = bmaterialTypeDao.findById(mtlType);
		return bmtype;
	}

	/**
	 * 导入Excel表格
	 */
	
	public boolean addOneBoat(List<BMaterial> list,int batchSize){ 
		//接收集合
		List<BMaterial> listExcle=list;
		
		boolean isImpSuccess = true;
		Session session = this.sessionFactory.getCurrentSession(); 
		session.beginTransaction();
		
		try {
			int count=listExcle.size()%batchSize==0?listExcle.size()/batchSize:listExcle.size()/batchSize+1;
			int insertCount=1;
			int maxCount;
			for (int i = 0; i < count; i++) {
				maxCount=insertCount*batchSize;
				StringBuffer sql=new StringBuffer("insert into b_Material (mtl_id,mtl_code，mtl_name,mtl_type,season,color_count,width,weigth,shrink_w,shrink_h,amount,mtl_price,mtl_ntx_price,min_order,prd_cycle,sys_dt,sys_user,mtl_unit) values ");
				if(maxCount>listExcle.size()){
					maxCount=listExcle.size();
				}
				for (int j = (insertCount-1)*batchSize; j < maxCount; j++) {
					if(insertCount==count){
						if((count-1)*batchSize<=j&&j<listExcle.size()-1){
							sql.append("(dbo.fn_get_next_mtl_id(),'"+listExcle.get(j).getMtlCode()+"','"+listExcle.get(j).getMtlName()+"','"+listExcle.get(j).getBMaterialType().getMtlType()+"',"
									+ "'"+listExcle.get(j).getSeason()+"',"+listExcle.get(j).getColorCount()+","+listExcle.get(j).getWidth()+","+listExcle.get(j).getWeigth()+","
										+ ""+listExcle.get(j).getShrinkW()+","+listExcle.get(j).getShrinkH()+","+listExcle.get(j).getAmount()+","+listExcle.get(j).getMtlPrice()+","+listExcle.get(j).getMtlNtxPrice()+","
											+""+listExcle.get(j).getMinOrder()+",'"+listExcle.get(j).getPrdCycle()+"','"+listExcle.get(j).getSysDt()+"','"+listExcle.get(j).getSysUser()+"','"+listExcle.get(j).getMtlUnit()+"'),");
						}else{
							sql.append("(dbo.fn_get_next_mtl_id(),'"+listExcle.get(j).getMtlCode()+"','"+listExcle.get(j).getMtlName()+"','"+listExcle.get(j).getBMaterialType().getMtlType()+"',"
									+ "'"+listExcle.get(j).getSeason()+"',"+listExcle.get(j).getColorCount()+","+listExcle.get(j).getWidth()+","+listExcle.get(j).getWeigth()+","
										+ ""+listExcle.get(j).getShrinkW()+","+listExcle.get(j).getShrinkH()+","+listExcle.get(j).getAmount()+","+listExcle.get(j).getMtlPrice()+","+listExcle.get(j).getMtlNtxPrice()+","
											+""+listExcle.get(j).getMinOrder()+",'"+listExcle.get(j).getPrdCycle()+"','"+listExcle.get(j).getSysDt()+"','"+listExcle.get(j).getSysUser()+"','"+listExcle.get(j).getMtlUnit()+"');");
						}
					}else{
						if((insertCount-1)*batchSize-1<=j&&j<insertCount*batchSize-1){
							sql.append("(dbo.fn_get_next_mtl_id(),'"+listExcle.get(j).getMtlCode()+"','"+listExcle.get(j).getMtlName()+"','"+listExcle.get(j).getBMaterialType().getMtlType()+"',"
									+ "'"+listExcle.get(j).getSeason()+"',"+listExcle.get(j).getColorCount()+","+listExcle.get(j).getWidth()+","+listExcle.get(j).getWeigth()+","
										+ ""+listExcle.get(j).getShrinkW()+","+listExcle.get(j).getShrinkH()+","+listExcle.get(j).getAmount()+","+listExcle.get(j).getMtlPrice()+","+listExcle.get(j).getMtlNtxPrice()+","
											+""+listExcle.get(j).getMinOrder()+",'"+listExcle.get(j).getPrdCycle()+"','"+listExcle.get(j).getSysDt()+"','"+listExcle.get(j).getSysUser()+"','"+listExcle.get(j).getMtlUnit()+"'),");
						}else{
							sql.append("(dbo.fn_get_next_mtl_id(),'"+listExcle.get(j).getMtlCode()+"','"+listExcle.get(j).getMtlName()+"','"+listExcle.get(j).getBMaterialType().getMtlType()+"',"
									+ "'"+listExcle.get(j).getSeason()+"',"+listExcle.get(j).getColorCount()+","+listExcle.get(j).getWidth()+","+listExcle.get(j).getWeigth()+","
										+ ""+listExcle.get(j).getShrinkW()+","+listExcle.get(j).getShrinkH()+","+listExcle.get(j).getAmount()+","+listExcle.get(j).getMtlPrice()+","+listExcle.get(j).getMtlNtxPrice()+","
											+""+listExcle.get(j).getMinOrder()+",'"+listExcle.get(j).getPrdCycle()+"','"+listExcle.get(j).getSysDt()+"','"+listExcle.get(j).getSysUser()+"','"+listExcle.get(j).getMtlUnit()+"');");
						}
					}
				}
				
				Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql.toString());
				query.executeUpdate(); 
				insertCount++;
			}
				//提交事物
				session.getTransaction().commit();
				session.flush(); 
				isImpSuccess = true;
		} catch (Exception e) {
			isImpSuccess = false;
			session.getTransaction().rollback();
		}
		return isImpSuccess;
	}
	
	/**
	 * 查找b_material_type中所有的type
	 */
	public  List typeList(){
		String sql="SELECT mtl_type FROM b_Material_Type ";
		Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
		List list=query.list(); 
		return list;
	}
	
	/**
	 * 根据面料Id获取面料信息
	 * @param mtlId
	 * @return
	 */
	public BMaterial findByMtlId(Integer mtlId){
		return bmaterialDao.findById(mtlId);
	}
	
	/**
	 * 根据条件获取面料信息集合
	 * @param options
	 * @return
	 */
	public List<ExtraBMaterial> getMtlByOptions(Map<String, Object> options){
		return bmaterialDao.getMtlByOptions(options);
	}
}
