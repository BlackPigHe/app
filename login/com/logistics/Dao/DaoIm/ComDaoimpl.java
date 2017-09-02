package com.logistics.Dao.DaoIm;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.logistics.Dao.CompDao;
import com.logistics.pojo.Complain;
import com.logistics.pojo.User;
import com.logistics.util.Page;

@Repository(value="ComDaoimpl")
public class ComDaoimpl extends CompDao{


	public int count(User user) {    //总记录数 
		int count = this.getSqlSession().selectOne("Comp.select_count",user);
		return count;
	}

	@Override
	public List<Complain> findlist(Page page) {
		List<Complain> list = this.getSqlSession().selectList("Comp.select_all", page);
		return list;
	}

	@Override
	public void saveComp(Complain complain) {
		this.getSqlSession().insert("Comp.insert_comp", complain);
	}

	@Override
	public Complain findById(int complainid) {
		Complain comp = this.getSqlSession().selectOne("Comp.select_byid", complainid);
		return comp;
	}

	@Override
	public void updateComp(Complain complain) {
		this.getSqlSession().update("Comp.update_complain", complain);
	}

	@Override
	public void deleteComp(String[] strArr) {
		this.getSqlSession().delete("Comp.delete_comp", strArr);
	}

	
	


	
}
