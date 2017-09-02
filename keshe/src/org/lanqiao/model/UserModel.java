package org.lanqiao.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.lanqiao.entity.User;
import org.lanqiao.tools.DBConnection;

public class UserModel {
	DBConnection dbc=new DBConnection();
	
	/**
	 * ע��
	 * @param u ע��Ķ���
	 * @return ע��ɹ�����true
	 */
	public boolean regUser(User u){
		boolean flag=false;
		Connection con=dbc.getCon();
		String sql="insert into Users values(?,?)";
		try {
			//��ֹsqlע��
			PreparedStatement pst=con.prepareStatement(sql);
			pst.setString(1, u.getName());
			pst.setString(2, u.getPwd());
			
			int count=pst.executeUpdate();
			if(count>0){
				flag=true;
			}
			pst.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * ��½
	 * @param name �û���
	 * @param pwd ����
	 * @return ��½�ɹ�����true
	 */
	public boolean loginUser(String name,String pwd){
		boolean flag=false;
		Connection con=dbc.getCon();
		String sql="select count(id) from Users where name=? and pwd=? ";
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1,name);
			pst.setString(2, pwd);
			ResultSet rs=pst.executeQuery();
			if(rs.next()&&rs.getInt(1)>0){
				flag=true;
			}
			rs.close();
			pst.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
}
