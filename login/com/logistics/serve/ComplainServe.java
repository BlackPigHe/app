package com.logistics.serve;


import com.logistics.pojo.Complain;
import com.logistics.pojo.User;
import com.logistics.util.Page;

public interface ComplainServe {

	public Page selectPage(User user,int page,int rows);
	public int saveComp(Complain complain);
	public Complain selectById(int complainid);
	public int updateComp(Complain complain);
	public int deleteComp(String str);
}
