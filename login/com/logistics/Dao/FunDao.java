package com.logistics.Dao;

import java.util.List;

import com.logistics.pojo.Function;

public abstract class FunDao extends BaseDao{
	public abstract List<Function> getAllFun();
}
