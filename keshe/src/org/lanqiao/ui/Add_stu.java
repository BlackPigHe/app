package org.lanqiao.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.lanqiao.entity.Student;
import org.lanqiao.proc.ProcStu;

public class Add_stu {
	/**
	 * ���ѧ����Ϣ
	 * ������ѧ�ţ��Ա����䣬רҵ��ѧУ��ѧ������ҵʱ��
	 */
	JLabel jl_name,jl_id,jl_sex,jl_age,jl_major,jl_school,jl_grade,jl_time,lbl_msg;
	JTextField jt_name,jt_id,jt_sex,jt_age,jt_major,jt_school,jt_grade,jt_time;
	JButton jb_send,jb_back;
	JPanel jp;
	JFrame jf;
	public Add_stu(){
		jl_name=new JLabel("������");
		jl_id=new JLabel("ѧ�ţ�");
		jl_sex=new JLabel("�Ա�");
		jl_age=new JLabel("���䣺");
		jl_major=new JLabel("רҵ��");
		jl_school=new JLabel("ѧУ��");
		jl_grade=new JLabel("ѧ����");
		jl_time=new JLabel("��ҵʱ�䣺");
		lbl_msg=new JLabel("");
		
		jt_name=new JTextField("");
		jt_id=new JTextField("");
		jt_sex=new JTextField("");
		jt_age=new JTextField("");
		jt_major=new JTextField("");
		jt_school=new JTextField("");
		jt_grade=new JTextField("");
		jt_time=new JTextField("2000-00-00<Ĭ�ϸ�ʽ>");
		jb_back=new JButton("������һ��");
		jb_send=new JButton("�ύ");
		jp=new JPanel();
		jf=new JFrame();
		
		//�¼�
		jb_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainUI();
				jf.dispose();
			}
		});
		jb_send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sname=jt_name.getText();
				String id=jt_id.getText();
				int sid=Integer.valueOf(id);
				String ssex=jt_sex.getText();
				String age=jt_age.getText();
				int sage=Integer.valueOf(age);
				String smajor=jt_major.getText();
				String sschool=jt_school.getText();
				String sgrade=jt_grade.getText();
				String stime=jt_time.getText();
				Student stu=new Student(sname, sid, smajor, ssex, sgrade, sage, sschool, stime);
				ProcStu ps=new ProcStu();
				if(ps.insertstu(stu)){
					lbl_msg.setText("�ύ�ɹ�");
				}else{
					lbl_msg.setText("�ύʧ�ܣ������ԣ�");
				}
				
			}
		});
		
		jl_name.setFont(new Font("����", Font.BOLD, 25));
		jl_id.setFont(new Font("����", Font.BOLD, 25));
		jl_sex.setFont(new Font("����", Font.BOLD, 25));
		jl_age.setFont(new Font("����", Font.BOLD, 25));
		jl_major.setFont(new Font("����", Font.BOLD, 25));
		jl_school.setFont(new Font("����", Font.BOLD, 25));
		jl_grade.setFont(new Font("����", Font.BOLD, 25));
		jl_time.setFont(new Font("����", Font.BOLD, 25));
		
		jp.setLayout(null);
		jl_name.setBounds(40, 25, 80, 25);
		jt_name.setBounds(120, 25, 200, 25);
		jl_id.setBounds(40, 60, 80, 25);
		jt_id.setBounds(120, 60, 200, 25);
		jl_sex.setBounds(40, 95, 80, 25);
		jt_sex.setBounds(120, 95, 200, 25);
		jl_age.setBounds(40, 130, 80, 25);
		jt_age.setBounds(120, 130, 200, 25);
		jl_major.setBounds(40, 165, 80, 25);
		jt_major.setBounds(120, 165, 200, 25);
		jl_school.setBounds(40, 200, 80, 25);
		jt_school.setBounds(120, 200, 200, 25);
		jl_grade.setBounds(40, 235, 80, 25);
		jt_grade.setBounds(120, 235, 200, 25);
		jl_time.setBounds(40, 270, 130, 25);
		jt_time.setBounds(170, 270, 200, 25);
		jb_send.setBounds(60, 310, 80, 40);
		jb_back.setBounds(300, 310, 120, 40);
		lbl_msg.setBounds(150, 360, 200, 25);
		
		jp.add(jb_back);
		jp.add(jb_send);
		jp.add(jl_sex);
		jp.add(jl_age);
		jp.add(jl_major);
		jp.add(jl_school);
		jp.add(jl_grade);
		jp.add(jl_time);
		jp.add(lbl_msg);
		jp.add(jl_name);
		jp.add(jl_id);
		jp.add(jt_name);
		jp.add(jt_id);
		jp.add(jt_sex);
		jp.add(jt_age);
		jp.add(jt_major);
		jp.add(jt_school);
		jp.add(jt_grade);
		jp.add(jt_time);
		jf.add(jp);
		
		
		
		jf.setTitle("��Ӵ�ѧ��");
		jf.setSize(600, 500);
		jf.setLocation(400, 100);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
	}
	public static void main(String[] args) {
		new Add_stu();
	}
	
}
