package com.logistics.serve.serveIm;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.logistics.Dao.FunDao;
import com.logistics.pojo.Function;
import com.logistics.serve.FunctionServe;
@Service(value="funServe")
public class FunctionServeIm implements FunctionServe{
	@Resource(name="funDao")
	FunDao dao;
	@Override
	public List<Function> getFunction() {
		return dao.getAllFun();
	}

}
