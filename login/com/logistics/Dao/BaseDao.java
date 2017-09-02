package com.logistics.Dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.sun.accessibility.internal.resources.accessibility;

public abstract class BaseDao extends SqlSessionDaoSupport{
	
	
	
	
	
	
	/*
	 * 一下方法用来获取sqlSession
	 * */
	@Resource(name = "sqlSessionFactory")
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}



}
