package com.logistics.serve;

import com.logistics.pojo.Dept;
import com.logistics.pojo.User;
import com.logistics.util.Page;

public interface DeptSever {
	
     public   Dept   selectOneDeptById(int deptid) ;
	 public   void   updateDept(Dept dept) ;
	 public   void   deleteDept(int deptid) ;
	 public   void   saveDept(Dept dept);
	 public   Page<Dept>   selectlistDept(Dept dept,int PageNo,int PageSize) ;

}
