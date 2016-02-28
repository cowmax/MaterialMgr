package com.dao;

import java.util.List;

import com.bean.Sysdiagrams;

public interface SysdiagramsDao {

	public abstract void save(Sysdiagrams transientInstance);

	public abstract void delete(Sysdiagrams persistentInstance);

	public abstract Sysdiagrams findById(java.lang.Integer id);

	public abstract List findByExample(Sysdiagrams instance);

	public abstract List findByProperty(String propertyName, Object value);

	public abstract List findByName(Object name);

	public abstract List findByPrincipalId(Object principalId);

	public abstract List findByVersion(Object version);

	public abstract List findByDefinition(Object definition);

	public abstract List findAll();

	public abstract Sysdiagrams merge(Sysdiagrams detachedInstance);

}