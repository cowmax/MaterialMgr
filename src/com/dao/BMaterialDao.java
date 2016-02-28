package com.dao;

import java.util.List;
import java.util.Map;

import com.bean.BMaterial;
import com.bean.ExtraBMaterial;

public interface BMaterialDao {

	public abstract void save(BMaterial transientInstance);

	public abstract void delete(Integer mtlId);

	public abstract BMaterial findById(java.lang.Integer id);

	public abstract List findByExample(BMaterial instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByMtlName(Object mtlName);

	public abstract List findByMtlPrice(Object mtlPrice);

	public abstract List findByColorCount(Object colorCount);

	public abstract List findBySeason(Object season);

	public abstract List findByWidth(Object width);

	public abstract List findByWeigth(Object weigth);

	public abstract List findByShrinkW(Object shrinkW);

	public abstract List findByShrinkH(Object shrinkH);

	public abstract List findByAmount(Object amount);

	public abstract List findByMinOrder(Object minOrder);

	public abstract List findByPrdCycle(Object prdCycle);

	public abstract List findBySysUser(Object sysUser);

	public abstract List findByMtlNtxPrice(Object mtlNtxPrice);

	public abstract List findByMtlCode(Object mtlCode);

	public abstract List findAll();

	public abstract BMaterial merge(BMaterial detachedInstance);

	public abstract List<BMaterial> getMtlCodeByType(int mtlType);
	
	public abstract void saveFirst(BMaterial material);
	
	public abstract List<ExtraBMaterial> getMtlByOptions(Map<String, Object> options);
}