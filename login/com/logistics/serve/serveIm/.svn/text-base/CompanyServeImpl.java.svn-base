package com.logistics.serve.serveIm;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.logistics.Dao.CompanyDao;
import com.logistics.pojo.Company;
import com.logistics.serve.CompanyServe;
import com.logistics.util.Page;
@Service(value="CompanyServe")
public class CompanyServeImpl  implements CompanyServe{
	@Resource(name="CompanyDaoImpl")
	CompanyDao dao;
	
	


	@Override
	public Company selectOneCompanyById(int companyno) {
		// TODO Auto-generated method stub
		Company company= dao.selectlistCompanyById(companyno);
		return company;
		
	}

	@Override
	public void updateCompany(Company companyno) {
		// TODO Auto-generated method stub
		dao.updateCompany(companyno);
		
	}

	@Override
	public void deleteCompany(int companyno) {
		// TODO Auto-generated method stub
		dao.deleteCompany(companyno);
	}

	@Override
	public void saveCompany(Company comp) {
		// TODO Auto-generated method stub
		dao.saveCompany(comp);
	}

	@Override
	public Page<Company> selectlistCompany(Company comp, int PageNo,
			int PageSize) {
		// TODO Auto-generated method stub
	
		return this.dao.selectlistCompany(comp, PageNo, PageSize);
		
	}

	@Override
	public Company selectOneCompany(int comid) {
		// TODO Auto-generated method stub
		Company comp=dao.selectOneCompany(comid);
		return comp;
	}
		


}
