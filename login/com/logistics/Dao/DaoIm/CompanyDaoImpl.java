package com.logistics.Dao.DaoIm;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.logistics.Dao.CompanyDao;
import com.logistics.pojo.Company;
import com.logistics.util.Page;



@Repository(value="CompanyDaoImpl")
public class CompanyDaoImpl extends CompanyDao {


	@Override
	public Company selectlistCompanyById(int  companyno) {
		// TODO Auto-generated method stub
		
		Company comp=this.getSqlSession().selectOne("Company.select2",  companyno);
		return comp;
	}

	@Override
	public void updateCompany(Company   comp) {
		// TODO Auto-generated method stub	
		this.getSqlSession().update("Company.update1", comp);
		
	}

	@Override
	public void deleteCompany( int companyno) {
		// TODO Auto-generated method stub
		this.getSqlSession().delete("Company.delete1",companyno );
		
	}

	@Override
	public void saveCompany(Company comp) {
		// TODO Auto-generated method stub
		System.out.println(comp.getCommanager());
		this.getSqlSession().insert("Company.insert1",comp);
	}

	@Override
	public Page<Company> selectlistCompany(Company comp, int PageNo,
			int PageSize) {
		// TODO Auto-generated method stub
		System.out.println("pageNo======"+PageNo+"===============PageSize"+PageSize);
		   Page<Company>  page = new Page(PageNo,PageSize);	
	       page.setParams(comp);		  
		   List<Company>  list = this.getSqlSession().selectList("Company.select-page", page);
		   page.setDataList(list);
		   return page;
	
	}

	@Override
	public Company selectOneCompany(int comid) {
		// TODO Auto-generated method stub
		Company comp=this.getSqlSession().selectOne("Company.selectone", comid);
		return comp;
	}



}
