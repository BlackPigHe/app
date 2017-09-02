package org.lanqiao.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.lanqiao.entity.Compare;
import org.lanqiao.tools.DBConnection;

public class ComHelpModel {
	DBConnection dbc=new DBConnection();
	/**
	 * 查询需求统计分析
	 * @param name
	 * @return
	 */
	public ArrayList<Compare> selstu(String year){
		ArrayList<Compare> list=new ArrayList<Compare>();
		Compare com=null;
		Connection con=dbc.getCon();
		String sql="select comhelp.cname,comhelp.ccity,comhelp.need,SUM(comhelp.num) AS num, SUBSTRING(comhelp.ctime,1,4) as nf from comhelp " +
				"where (SUBSTRING(comhelp.ctime,1,4)) = '"+year+"'group by comhelp.cname,comhelp.ccity,comhelp.need,(SUBSTRING(comhelp.ctime,1,4))";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				String name=rs.getString(1);
				String city=rs.getString(2);
				String need=rs.getString(3);
				int num=rs.getInt(4);
				com=new Compare(name, city, need, num, year);
				list.add(com);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
