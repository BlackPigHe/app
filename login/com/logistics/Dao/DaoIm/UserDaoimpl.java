package com.logistics.Dao.DaoIm;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.logistics.Dao.UserDao;
import com.logistics.pojo.Company;
import com.logistics.pojo.User;
import com.logistics.util.Page;

@Repository(value="userDao")
public class UserDaoimpl extends UserDao{

	@Override
	public String findExitacc(User user) {
		System.out.println("0000000000000000000000");
		User use = this.getSqlSession().selectOne("User.select_user", user);
		if(use==null){
			return "aaa";
		}else{
			System.out.println("1111111111111="+use.getAccount());
			return "bbb";
		}			
	}
	
	@Override
	public String findpwd(User user) {
		String pwd= this.getSqlSession().selectOne("User.select_login", user);
		System.out.println("ioioioioioio===="+pwd);
		return pwd;
	}

	@Override
	public User finduser(User user) {
		User use= this.getSqlSession().selectOne("User.select_all", user);
		return use;
	}

	@Override
	public void update(User user) {
		try {
			System.out.println("tx----"+ this.getSqlSession().getConnection().getAutoCommit());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getSqlSession().update("User.update_pwd", user);
	}

	
	
	//用户管理
	@Override
	public Page<User> SelectlistUser(User user, int PageNo, int PageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User SelectOneUserById(int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void UpdateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(int userid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}




}
