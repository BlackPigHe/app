package com.logistics.Dao;

import java.util.List;

import com.logistics.pojo.Function;
import com.logistics.pojo.Role;

public abstract class UtilDao extends BaseDao{

	public abstract List<Function> getFun(int roleId) ;

	public abstract Role getRole(int userId);
	public abstract List<Role> fromCompanynoGetRole(int companyno);
}
