package com.logistics.Dao.DaoIm;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.logistics.Dao.FunDao;
import com.logistics.pojo.Function;
@Repository(value="funDao")
public class FunDaoIm extends FunDao{

	@Override
	public List<Function> getAllFun() {
		return getSqlSession().selectList("Function.getAll");
	}

}
