package org.lanqiao.proc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.lanqiao.entity.Compare;
import org.lanqiao.tools.DBConnection;

public class ProcCom {
	DBConnection dbc=new DBConnection();
	/**
	 * 添加公司
	 * @param com
	 * @return
	 */
	public boolean insert_com(Compare com){
		boolean flag=false;
		try {
			Connection con=dbc.getCon();
			// 创建存储过程的对象
			CallableStatement c = con.prepareCall("{call com_Insert(?,?,?)}");
			// 给存储过程的参数设置值
			//c.setInt(1, 100);
			c.setString(1, com.getName());
			c.setString(2, com.getCity());
			c.setString(3, com.getGrade());
			
			
			// 执行存储过程
			int count=c.executeUpdate();
			if(count>0){
				flag=true;
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * 修改公司信息
	 * @param stu 
	 * @return
	 */
	public boolean updatestu(Compare com){
		boolean flag=false;
		try {
			Connection con=dbc.getCon();
			// 创建存储过程的对象
			CallableStatement c = con.prepareCall("{call com_update(?,?,?)}");
			c.setString(1, com.getName());
			c.setString(2, com.getCity());
			c.setString(3, com.getGrade());
			
			
			int count=c.executeUpdate();
			if(count>0){
				flag=true;
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 删除公司
	 * @param stu
	 * @return
	 */
	public boolean detcom(String name){
		boolean flag=false;
		
		try {
			Connection con=dbc.getCon();
			// 创建存储过程的对象
			CallableStatement c = con.prepareCall("{call com_delete(?)}");
			c.setString(1, name);
			
			int count=c.executeUpdate();
			if(count>0){
				flag=true;
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
}
