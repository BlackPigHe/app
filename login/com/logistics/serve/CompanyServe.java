package com.logistics.serve;

import java.util.List;

import com.logistics.pojo.Company;
import com.logistics.util.Page;

public interface CompanyServe {
	
	
	 public   Company   selectOneCompanyById(int companyno) ;
	 public   void   updateCompany(Company companyno) ;
	 public   void   deleteCompany(int companyno) ;
	 public   void   saveCompany(Company comp) ;
	 public   Page<Company>   selectlistCompany(Company comp ,int PageNo,int PageSize);
	 public   Company   selectOneCompany(int comid) ;
}
