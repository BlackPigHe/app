package org.lanqiao.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.lanqiao.entity.Student;

public class mes_stu_need {
	JLabel jl_msg;
	JTextArea jta;
	JButton jb_back;
	JPanel jp;
	JFrame jf;
	public mes_stu_need(ArrayList<Student> list){
		jl_msg=new JLabel("��ѯ������£�");
		jta=new JTextArea();
		jta.setEditable(false);
		jta.setLineWrap(true);// �����Զ����й���
		jb_back=new JButton("����");
		jp=new JPanel();
		jf=new JFrame("�˲���Ϣ��ѯ�����");
		
		jta.setText("����\tѧ��\t�Ա�\t����\tרҵ\tѧУ\tѧ��\t��ҵʱ��\n");
		for(Student stu:list){
			jta.append(stu.toString()+"\n");
		}
		
		jb_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new sel_com_help();
				jf.dispose();
			}
		});
		
		jp.setLayout(null);
		jl_msg.setBounds(10, 5, 100, 20);
		jb_back.setBounds(700, 5, 80, 20);
		jta.setBounds(30, 30, 700, 400);
		
		jp.add(jl_msg);
		jp.add(jb_back);
		jf.add(jta);
		jf.add(jp);
		
		jf.setSize(800, 500);
		jf.setLocation(350, 100);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}
