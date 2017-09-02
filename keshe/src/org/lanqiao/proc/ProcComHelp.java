package org.lanqiao.proc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.lanqiao.entity.Compare;
import org.lanqiao.tools.DBConnection;

public class ProcComHelp {
		DBConnection dbc=new DBConnection();
		/**
		 * ��ӹ�˾������Ϣ
		 * @param com
		 * @return
		 */
		public boolean com_help_Insert(Compare com){
			boolean flag=false;
			try {
				Connection con=dbc.getCon();
				// �����洢���̵Ķ���
				CallableStatement c = con.prepareCall("{call com_help_Insert(?,?,?,?,?)}");
				// ���洢���̵Ĳ�������ֵ
				//c.setInt(1, 100);
				c.setString(1, com.getName());
				c.setString(2, com.getCity());
				c.setString(3, com.getNeed());
				c.setInt(4, com.getNum());
				c.setString(5, com.getTime());
				
				
				// ִ�д洢����
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
		 * �޸Ĺ�˾��Ϣ
		 * @param stu 
		 * @return
		 */
		public boolean com_help_update(Compare com){
			boolean flag=false;
			try {
				Connection con=dbc.getCon();
				// �����洢���̵Ķ���
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
