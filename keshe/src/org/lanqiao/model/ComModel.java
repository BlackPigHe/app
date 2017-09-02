package org.lanqiao.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.lanqiao.entity.Compare;
import org.lanqiao.tools.DBConnection;

public class ComModel {
	DBConnection dbc=new DBConnection();
	/**
	 * 查询单个公司信息
	 * @param name
	 * @return
	 */
	public Compare selstu(String name){
		Compare com=null;
		Connection con=dbc.getCon();
		String sql="select * from compary where name='"+name+"'";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				String city=rs.getString("city");
				String grade=rs.getString("grade");
				com=new Compare(name, city, grade);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return com;
	}
}
