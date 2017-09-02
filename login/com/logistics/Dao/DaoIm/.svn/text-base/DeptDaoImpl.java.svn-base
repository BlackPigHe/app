package com.logistics.Dao.DaoIm;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.logistics.Dao.DeptDao;
import com.logistics.pojo.Company;
import com.logistics.pojo.Dept;
import com.logistics.pojo.User;
import com.logistics.util.Page;


@Repository(value="DeptDaoImpl")
public class DeptDaoImpl extends DeptDao{

	@Override
	public Dept selectOneDeptById(int deptid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateDept(Dept dept) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDept(int deptid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveDept(Dept dept) {
		// TODO Auto-generated method stub
		this.getSqlSession().insert("Dept.insertDept", dept);
		
	}

	


	@Override
	public int Count(User user) {
		// TODO Auto-generated method stub
		int count=this.getSqlSession().selectOne("Dept.select_count",user);
			
		return count;
	}

	@Override
	public Page<Dept> findlistDept(Dept dept, int PageNo, int PageSize) {
		// TODO Auto-generated method stub
		Page<Dept> page = new Page(PageNo, PageSize);
		page.setParams(dept);
		List<Dept> list = this.getSqlSession().selectList("Dept.select-page",
				page);
		page.setDataList(list);
		return page;
	}

	

}
