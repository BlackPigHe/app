package org.lanqiao.model;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jxl.Sheet;
import jxl.Workbook;


import org.lanqiao.entity.Student;
import org.lanqiao.tools.DBConnection;

public class StuModel {
	DBConnection dbc=new DBConnection();

	/**
	 * 查找单个学生对象
	 * @param id 学生学号
	 * @param url 学生表
	 * @return
	 */
	public Student selstu(int id){
		Student stu=null;
		Connection con=dbc.getCon();
		String sql="select * from Student where stuid="+id;
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				String name=rs.getString("name");
				
				String major=rs.getString("major");
				String sex=rs.getString("sex");
				String grade=rs.getString("grade");
				int age=rs.getInt("age");
				String school=rs.getString("school");
				String time=rs.getString("gratime");
				stu=new Student(name, id, major, sex, grade, age, school, time);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stu;
	}
	
	/**
	 * 查询该专业学生数据
	 * @param major
	 * @return
	 */
	public ArrayList<Student> selAllstu(String major){
		ArrayList<Student> list=new ArrayList<Student>();
		Connection con=dbc.getCon();
		String sql="select * from Student where major='"+major+"'";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				String name=rs.getString("name");
				
				int id=rs.getInt("stuid");
				String sex=rs.getString("sex");
				String grade=rs.getString("grade");
				int age=rs.getInt("age");
				String school=rs.getString("school");
				String time=rs.getString("gratime");
				Student stu=new Student(name, id, major, sex, grade, age, school, time);
				list.add(stu);
				
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * 列出所有学生数据
	 * @return
	 */
	public ArrayList<Student> select_all_atu(){
		ArrayList<Student> list=new ArrayList<Student>();
		Connection con=dbc.getCon();
		String sql="select * from Student";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()){
				String name=rs.getString("name");
				
				int id=rs.getInt("stuid");
				String sex=rs.getString("sex");
				String major=rs.getString("major");
				String grade=rs.getString("grade");
				int age=rs.getInt("age");
				String school=rs.getString("school");
				String time=rs.getString("gratime");
				Student stu=new Student(name, id, major, sex, grade, age, school, time);
				list.add(stu);
				
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 清空学生
	 * @param stu
	 * @return
	 */
	public boolean deleteStu(){
		boolean flag=false;
		
		try {
			Connection con=dbc.getCon();
			String sql="truncate table student";
			Statement st=con.createStatement();
			int count=st.executeUpdate(sql);
			if(count>0){
				flag=true;
			}
			st.close();
			con.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 查询表格中数据
	 * @param file
	 * @return
	 */
	public static ArrayList<Student> getAllByExcel(String file){
		ArrayList<Student> list=new ArrayList<Student>();
        try {
            Workbook rwb=Workbook.getWorkbook(new File(file));
            Sheet rs=rwb.getSheet("Test Shee1");//或者rwb.getSheet(0)
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
            
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                	String name=rs.getCell(j++, i).getContents();
                    String id=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
                    String sex=rs.getCell(j++, i).getContents();
                    String age=rs.getCell(j++, i).getContents();
                    String major=rs.getCell(j++, i).getContents();
                    String school=rs.getCell(j++, i).getContents();
                    String grade=rs.getCell(j++, i).getContents();
                    String time=rs.getCell(j++, i).getContents();
                    
                    list.add(new Student(name, Integer.parseInt(id), major, sex, grade, Integer.parseInt(age), school, time));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return list;
        
    }
}
