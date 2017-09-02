package com.logistics.pojo;

public class Function {

	/*
	 * 功能ID	funid	Int
功能名称	funname	Varchar(50)
功能Pid	funpid	int
功能层次	funlevel	int
功能描述	fundesc	Varchar(100)
链接	href	Varchar(100)

	 */
	
	private int funid;
	private String funname;
	private int funpid;
	private int funlevel;
	private String fundesc;
	private String href;
	
	public Function(){
		
	}

	public int getFunid() {
		return funid;
	}

	public void setFunid(int funid) {
		this.funid = funid;
	}

	public String getFunname() {
		return funname;
	}

	public void setFunname(String funname) {
		this.funname = funname;
	}

	public int getFunpid() {
		return funpid;
	}

	public void setFunpid(int funpid) {
		this.funpid = funpid;
	}

	public int getFunlevel() {
		return funlevel;
	}

	public void setFunlevel(int funlevel) {
		this.funlevel = funlevel;
	}

	public String getFundesc() {
		return fundesc;
	}

	public void setFundesc(String fundesc) {
		this.fundesc = fundesc;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
	
	
	
	

}
