package com.logistics.Dao;

import java.util.List;

import com.logistics.pojo.Company;
import com.logistics.util.Page;

public abstract  class CompanyDao extends BaseDao {
	

	 public abstract  Company   selectlistCompanyById(int companyno) ;
	 public abstract  void   updateCompany(Company companyno) ;
	 public abstract  void   deleteCompany(int companyno) ;
	 public abstract  void   saveCompany(Company comp);
	 public abstract Page<Company> selectlistCompany(Company comp, int PageNo,
				int PageSize) ;
		
	 public abstract  Company   selectOneCompany(int comid) ;

}
