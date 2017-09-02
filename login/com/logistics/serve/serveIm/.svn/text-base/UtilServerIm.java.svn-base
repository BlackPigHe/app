package com.logistics.serve.serveIm;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.logistics.Dao.UtilDao;
import com.logistics.pojo.Function;
import com.logistics.pojo.Role;
import com.logistics.pojo.User;
import com.logistics.serve.UtilServer;
@Service("UtilService")
public class UtilServerIm implements UtilServer{
	@Resource(name="utilDao")
	UtilDao dao;
	@Override
	public List<Function> getFun(int roleId) {
		List<Function> funList=dao.getFun(roleId);
		return funList;
	}
	@Override
	public Role getRole(int userId) {
		Role role=dao.getRole(userId);
		return role;
	}
	@Override
	public List<Role> fromCompanynoGetRole(int companyno) {
		List<Role> roleList=dao.fromCompanynoGetRole(companyno);
		return roleList;
	}
	@Override
	public List<Function> userIdGetFun(int userId){
		return getFun(getRole(userId).getRoleid());
	}
	@Override
	public boolean haveFun(int userId,int functionId){
		List<Function> funList=userIdGetFun(userId);
		for(Function fun:funList){
			if(fun.getFunid()==functionId){
				return true;
			}
		}
		return false;	
	}

}
