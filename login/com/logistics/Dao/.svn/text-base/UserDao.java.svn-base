package com.logistics.Dao;

import com.logistics.pojo.User;
import com.logistics.util.Page;

public abstract class UserDao extends BaseDao {

	public abstract String findExitacc(User user);  //查询账号
	public abstract String findpwd(User user);  //根据账号查询密码
	public abstract User finduser(User user);     //查询账户信息
	public abstract void update(User user);   //更新密码
	
    public abstract  Page<User>  SelectlistUser(User user,int PageNo,int PageSize);
    public abstract  User    SelectOneUserById(int userid );
    public abstract  void    UpdateUser(User user);
    public abstract  void    deleteUser(int userid);
    public abstract  User   saveUser(User user);
	
	
	
}
