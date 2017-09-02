package com.logistics.pojo;

public class Role {
	private int roleid;
	private String rolename;
	private String roledesc;
	private int comidfk;
	private Function[] fun;

	/*
	 * 角色ID	roleid	int
角色名称	rolename	Varchar(50)
角色描述	roledesc	Varchar(100)
公司ID	comidfk	int

	 * 
	 */
	
	public Role(){
		
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRoledesc() {
		return roledesc;
	}

	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
	}

	public int getComidfk() {
		return comidfk;
	}

	public void setComidfk(int comidfk) {
		this.comidfk = comidfk;
	}

	public Function[] getFun() {
		return fun;
	}

	public void setFun(Function[] fun) {
		this.fun = fun;
	}
	
	
	
}
