package org.lanqiao.proc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.lanqiao.entity.Compare;
import org.lanqiao.tools.DBConnection;

public class ProcCom {
	DBConnection dbc=new DBConnection();
	/**
	 * ��ӹ�˾
	 * @param com
	 * @return
	 */
	public boolean insert_com(Compare com){
		boolean flag=false;
		try {
			Connection con=dbc.getCon();
			// �����洢���̵Ķ���
			CallableStatement c = con.prepareCall("{call com_Insert(?,?,?)}");
			// ���洢���̵Ĳ�������ֵ
			//c.setInt(1, 100);
			c.setString(1, com.getName());
			c.setString(2, com.getCity());
			c.setString(3, com.getGrade());
			
			
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
	public boolean updatestu(Compare com){
		boolean flag=false;
		try {
			Connection con=dbc.getCon();
			// �����洢���̵Ķ���
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
	 * ɾ����˾
	 * @param stu
	 * @return
	 */
	public boolean detcom(String name){
		boolean flag=false;
		
		try {
			Connection con=dbc.getCon();
			// �����洢���̵Ķ���
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
