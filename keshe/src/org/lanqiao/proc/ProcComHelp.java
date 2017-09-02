package org.lanqiao.proc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.lanqiao.entity.Compare;
import org.lanqiao.tools.DBConnection;

public class ProcComHelp {
		DBConnection dbc=new DBConnection();
		/**
		 * 添加公司需求信息
		 * @param com
		 * @return
		 */
		public boolean com_help_Insert(Compare com){
			boolean flag=false;
			try {
				Connection con=dbc.getCon();
				// 创建存储过程的对象
				CallableStatement c = con.prepareCall("{call com_help_Insert(?,?,?,?,?)}");
				// 给存储过程的参数设置值
				//c.setInt(1, 100);
				c.setString(1, com.getName());
				c.setString(2, com.getCity());
				c.setString(3, com.getNeed());
				c.setInt(4, com.getNum());
				c.setString(5, com.getTime());
				
				
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
		public boolean com_help_update(Compare com){
			boolean flag=false;
			try {
				Connection con=dbc.getCon();
				// 创建存储过程的对象
				CallableStatement c = con.prepareCall("{call com_help_update(?,?,?,?,?,?,?,?)}");
				c.setString(1, com.getName());
				c.setString(2, com.getCity());
				c.setString(3, com.getNeed());
				c.setInt(4, com.getNum());
				c.setString(5, com.getTime());
				
				
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
