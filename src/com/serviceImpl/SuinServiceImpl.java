package com.serviceImpl;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.List;

import org.hibernate.SessionFactory;

import com.bean.Suin;
import com.dao.SuinDao;

public class SuinServiceImpl {

	private SuinDao suinDao;
	private SessionFactory sessionFactory;
	private List<Suin> allSuinList;
	private Suin mergeSuin;

	public SuinDao getSuinDao() {
		return suinDao;
	}

	public void setSuinDao(SuinDao suinDao) {
		this.suinDao = suinDao;
	}

	public List<Suin> getAllSuinList() {
		return allSuinList;
	}

	public void setAllSuinList(List<Suin> allSuinList) {
		this.allSuinList = allSuinList;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Suin getMergeSuin() {
		return mergeSuin;
	}

	public void setMergeSuin(Suin mergeSuin) {
		this.mergeSuin = mergeSuin;
	}

	/**
	 * 获取所有供应商信息
	 * @return
	 */
	public List<Suin> getAllSuin(){
		return suinDao.findAll();
	}
	
	/**
	 * 获取新的suid
	 * @return
	 */
	public String getNewSuid(){
		String procdure = "{Call [dbo].[sp_get_next_suid]}";  
		CallableStatement cs;
		String newSuid = null;

		try {
			cs = this.sessionFactory.getCurrentSession().connection().prepareCall(procdure);
			ResultSet rs=cs.executeQuery();
			while (rs.next()) {
				newSuid = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return newSuid;
	}
	
	/**
	 * 添加供应商信息
	 * @param suin
	 */
	public void saveSuin(Suin suin){
		suinDao.save(suin);
	}
	
	/**
	 * 删除供应商信息
	 * @param suin
	 */
	public void delSuin(String suid){
		Suin suin = suinDao.findById(suid);
		suinDao.delete(suin);
	}
	
	/**
	 * 修改供应商信息
	 * @param suin
	 * @return
	 */
	public Suin mergeSuin(Suin suin){
		mergeSuin = suinDao.merge(suin);
		return mergeSuin;
	}
	
	/**
	 * 根据suid获取suin信息
	 * @return
	 */
	public Suin findSuinBySuid(String suid){
		return suinDao.findById(suid);
	}
}
