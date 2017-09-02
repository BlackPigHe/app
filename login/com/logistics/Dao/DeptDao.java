package com.logistics.Dao;


import java.util.List;

import com.logistics.pojo.Complain;
import com.logistics.pojo.Dept;
import com.logistics.pojo.User;
import com.logistics.util.Page;

public abstract class DeptDao extends BaseDao {
     public abstract  Dept   selectOneDeptById(int deptid) ;
	 public abstract  void   updateDept(Dept dept) ;
	 public abstract  void   deleteDept(int deptid) ;
	 public abstract  void   saveDept(Dept dept);
	 public abstract   int     Count (User user);
	 public abstract   Page<Dept> findlistDept(Dept dept,int PageNo,int PageSize);
	
		

}
