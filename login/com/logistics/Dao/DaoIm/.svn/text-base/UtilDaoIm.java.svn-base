package com.logistics.Dao.DaoIm;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.logistics.Dao.UtilDao;
import com.logistics.pojo.Function;
import com.logistics.pojo.Role;
@Repository("utilDao")
public class UtilDaoIm extends UtilDao{
	@Override
	public List<Function> getFun(int roleId) {
		System.out.println(123);
		List<Function> l=getSqlSession().selectList("roleFun.getFun",roleId);
		return l;
	}

	@Override
	public Role getRole(int userid) {
		Role role=getSqlSession().selectOne("roleFun.getRole",userid);
		return role;
	}
	
	@Override
	public List<Role> fromCompanynoGetRole(int companyno) {
		List<Role> roleList=getSqlSession().selectList("roleFun.fromCompanyIdGetRole",companyno);
		return roleList;
	}
}
