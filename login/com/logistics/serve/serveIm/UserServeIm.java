package com.logistics.serve.serveIm;

import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.logistics.Dao.UserDao;
import com.logistics.pojo.User;
import com.logistics.serve.UserServe;

@Service(value="userService")
public class UserServeIm implements UserServe{
 
	@Resource(name="userDao")
	public UserDao userDao;

	
	@Override
	public int login(User user ,HttpServletRequest req) {
		String sta = userDao.findExitacc(user);
		if("aaa".equals(sta)){
			return 2;
		}else{
			String pwd= userDao.findpwd(user);
			
			if( pwd.equals(user.getPwd())){
				User userA = userDao.finduser(user);
				req.getSession().setAttribute("user", userA);
				return 1;
			}else if( !(pwd.equals(user.getPwd()))){
				return 3;
			}else{
				return 0; 
			}
		}
		
	}


	@Override
	public int updatePwd(User user,String oldPwd,String newPwd1) {
		String pwd= userDao.findpwd(user);
		
		//System.out.println("1111111pwd====="+pwd);
		
		System.out.println("pwd====="+pwd);
		System.out.println("oldPwd====="+oldPwd);
		System.out.println("newPwd1====="+newPwd1);
		
		if(!(pwd.equals(oldPwd))){
			return 1;
		}else{
			if(oldPwd.equals(newPwd1)){
				return 2;
			}else if(!(oldPwd.equals(newPwd1))){
				user.setPwd(newPwd1);
				userDao.update(user);
				return 0;
			}else{
				return 3;
			}
		}
	}


	

}
