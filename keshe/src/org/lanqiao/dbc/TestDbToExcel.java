package org.lanqiao.dbc;

import java.io.File;
import java.util.ArrayList;

import org.lanqiao.entity.Student;
import org.lanqiao.model.StuModel;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class TestDbToExcel {

	public boolean stu_save(){
		boolean flag=false;
		try {
			WritableWorkbook wwb = null;
			StuModel sm=new StuModel();

			// 创建可写入的Excel工作簿
			String fileName = "F://stu.xls";
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			// 以fileName为文件名来创建一个Workbook
			wwb = Workbook.createWorkbook(file);

			// 创建工作表
			WritableSheet ws = wwb.createSheet("Test Shee1", 0);

			// 查询数据库中所有的数据
			ArrayList<Student> list = sm.select_all_atu();
			//要插入到的Excel表格的行号，默认从0开始
			Label labelName = new Label(0, 0, "姓名(name)");
			Label labelId = new Label(1, 0, "学号(id)");
			Label labelSex = new Label(2, 0, "性别(sex)");
			Label labelAge = new Label(3, 0, "年龄(age)");
			Label labelMajor=new Label(4, 0, "专业(major)");
			Label labelSchool=new Label(5, 0, "学校(school)");
			Label labelGrade=new Label(6, 0, "学历(grade)");
			Label labelTime=new Label(7, 0, "毕业时间(time)");

			ws.addCell(labelId);
			ws.addCell(labelName);
			ws.addCell(labelSex);
			ws.addCell(labelAge);
			ws.addCell(labelMajor);
			ws.addCell(labelSchool);
			ws.addCell(labelGrade);
			ws.addCell(labelTime);
			for (int i = 0; i < list.size(); i++) {

				Label  labelName_i= new Label(0, i + 1, list.get(i).getName());
				Label labelId_i = new Label(1, i + 1, ""+list.get(i).getId());
				Label labelSex_i = new Label(2, i + 1, list.get(i).getSex());
				Label labelAge_i = new Label(3, i + 1, ""+list.get(i).getAge());
				Label labelMajor_i=new Label(4, i + 1, list.get(i).getMajor());
				Label labelSchool_i=new Label(5, i + 1, list.get(i).getSchool());
				Label labelGrade_i=new Label(6, i + 1, list.get(i).getGrade());
				Label labelTime_i=new Label(7, i + 1, list.get(i).getTime());
				
				ws.addCell(labelName_i);
				ws.addCell(labelId_i);
				ws.addCell(labelSex_i);
				ws.addCell(labelAge_i);
				ws.addCell(labelMajor_i);
				ws.addCell(labelSchool_i);
				ws.addCell(labelGrade_i);
				ws.addCell(labelTime_i);
			}

			// 写进文档
			wwb.write();
			// 关闭Excel工作簿对象
			wwb.close();
			flag=true;
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	public static void main(String[] args) {
		new TestDbToExcel().stu_save();
	}
		
}