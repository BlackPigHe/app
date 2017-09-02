package com.logistics.serve.serveIm;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.logistics.Dao.CompDao;
import com.logistics.pojo.Complain;
import com.logistics.pojo.User;
import com.logistics.serve.ComplainServe;
import com.logistics.serve.UtilServer;
import com.logistics.util.Page;


@Service(value="Complainservice")
public class ComplainServeIm implements ComplainServe{

	@Resource(name="ComDaoimpl")
	public CompDao ComDaoimpl;
	
	
	@Resource(name="UtilService")
	public UtilServer utilServer;



	@Override
	public Page selectPage(User user, int page, int rows) {
		int count = ComDaoimpl.count(user);
		Page pag = new Page(page,rows,count);
		pag.setParams(user);
		
			List<Complain> list = ComDaoimpl.findlist(pag);
			pag.setDataList(list);
			return pag;
		
	}



	@Override
	public int saveComp(Complain complain) {
		try{
			ComDaoimpl.saveComp(complain);
			return 1;
		}catch(Exception e){
			return 2;
		}
		
	}



	@Override
	public Complain selectById(int complainid) {
		Complain comp = ComDaoimpl.findById(complainid);
		
		return comp;
	}



	@Override
	public int updateComp(Complain complain) {
		try{
			ComDaoimpl.updateComp(complain);
			return 1;
		}catch(Exception e){
			return 0;
		}
		
		
	}



	@Override
	public int deleteComp(String str) {
		String[] strArr = str.split(",");
		try{
			this.ComDaoimpl.deleteComp(strArr);
			return 1;
		}catch(Exception e){
			return 0;
		}
		
	}



	
	
	
	


}
