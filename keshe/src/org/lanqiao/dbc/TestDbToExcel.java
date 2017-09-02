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

			// ������д���Excel������
			String fileName = "F://stu.xls";
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			// ��fileNameΪ�ļ���������һ��Workbook
			wwb = Workbook.createWorkbook(file);

			// ����������
			WritableSheet ws = wwb.createSheet("Test Shee1", 0);

			// ��ѯ���ݿ������е�����
			ArrayList<Student> list = sm.select_all_atu();
			//Ҫ���뵽��Excel�����кţ�Ĭ�ϴ�0��ʼ
			Label labelName = new Label(0, 0, "����(name)");
			Label labelId = new Label(1, 0, "ѧ��(id)");
			Label labelSex = new Label(2, 0, "�Ա�(sex)");
			Label labelAge = new Label(3, 0, "����(age)");
			Label labelMajor=new Label(4, 0, "רҵ(major)");
			Label labelSchool=new Label(5, 0, "ѧУ(school)");
			Label labelGrade=new Label(6, 0, "ѧ��(grade)");
			Label labelTime=new Label(7, 0, "��ҵʱ��(time)");

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

			// д���ĵ�
			wwb.write();
			// �ر�Excel����������
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