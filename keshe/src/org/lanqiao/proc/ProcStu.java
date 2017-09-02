package org.lanqiao.proc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.lanqiao.entity.Student;
import org.lanqiao.tools.DBConnection;

public class ProcStu {
	DBConnection dbc=new DBConnection();
	/**
	 * ���ѧ����Ϣ
	 * @param stu
	 * @return
	 */
	public boolean insertstu(Student stu){
		boolean flag=false;
		try {
			Connection con=dbc.getCon();
			// �����洢���̵Ķ���
			CallableStatement c = con.prepareCall("{call stu_Insert(?,?,?,?,?,?,?,?)}");
			// ���洢���̵Ĳ�������ֵ
			//c.setInt(1, 100);
			c.setString(1, stu.getName());
			c.setInt(2, stu.getId());
			c.setString(3, stu.getMajor());
			c.setString(4, stu.getSex());
			c.setString(5, stu.getGrade());
			c.setInt(6, stu.getAge());
			c.setString(7, stu.getSchool());
			c.setString(8, stu.getTime());
			
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
	 * �޸�ѧ����Ϣ
	 * @param stu 
	 * @return
	 */
	public boolean updatestu(Student stu){
		boolean flag=false;
		try {
			Connection con=dbc.getCon();
			// �����洢���̵Ķ���
			CallableStatement c = con.prepareCall("{call stu_update(?,?,?,?,?,?,?,?)}");
			c.setString(1, stu.getName());
			c.setInt(2, stu.getId());
			c.setString(3, stu.getMajor());
			c.setString(4, stu.getSex());
			c.setString(5, stu.getGrade());
			c.setInt(6, stu.getAge());
			c.setString(7, stu.getSchool());
			c.setString(8, stu.getTime());
			
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
	 * ɾ��ѧ��
	 * @param stu
	 * @return
	 */
	public boolean detStu(int id){
		boolean flag=false;
		
		try {
			Connection con=dbc.getCon();
			// �����洢���̵Ķ���
			CallableStatement c = con.prepareCall("{call stu_delete(?)}");
			c.setInt(1, id);
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