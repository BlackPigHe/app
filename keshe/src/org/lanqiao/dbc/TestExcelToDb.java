package org.lanqiao.dbc;

import java.io.File;
import java.util.ArrayList;

import org.lanqiao.entity.Student;
import org.lanqiao.model.StuModel;
import org.lanqiao.proc.ProcStu;

public class TestExcelToDb {
	public boolean stu_rec() {
		boolean flag = false;
		// 得到表格中所有的数据
		StuModel sm = new StuModel();
		ProcStu ps = new ProcStu();
		String fileName = "F://stu.xls";
		File file = new File(fileName);
		//判断文件是否存在
		if (!file.exists()) {
			return flag;
		}
		ArrayList<Student> listExcel = StuModel.getAllByExcel(fileName);
		
		if(listExcel!=null){
			sm.deleteStu();
			for (Student stu : listExcel) {
				ps.insertstu(stu);
				if(stu!=null)
					flag=true;
			}
		}
		return flag;
	}
	public static void main(String[] args) {
		new TestExcelToDb().stu_rec();
	}
}