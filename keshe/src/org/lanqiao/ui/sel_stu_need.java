package org.lanqiao.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.lanqiao.entity.Student;
import org.lanqiao.model.StuModel;

public class sel_stu_need {
	JLabel jl_major,jl_msg;
	JTextField jt_major;
	JButton jb_send,jb_back;
	JPanel jp;
	JFrame jf;
	public sel_stu_need(){
		jl_major=new JLabel("请输入专业：");
		jl_msg=new JLabel();
		jt_major=new JTextField();
		jb_send=new JButton("查找");
		jb_back=new JButton("返回上一级");
		jp=new JPanel();
		jf=new JFrame();
		
		jb_send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StuModel sm=new StuModel();
				String major=jt_major.getText();
				ArrayList<Student> list=sm.selAllstu(major);
				if(list!=null){
					new mes_stu_need(list);
					jf.dispose();
				}else{
					jl_msg.setText("查找失败");
				}
			}
		});
		jb_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MainUI();
				jf.dispose();
			}
		});
		
		
		
		jl_major.setFont(new Font("宋体", Font.BOLD, 15));
		
		jp.setLayout(null);
		jl_major.setBounds(30, 20, 110, 25);
		jt_major.setBounds(140, 20, 150, 25);
		jb_send.setBounds(80, 120, 90, 50);
		jb_back.setBounds(200, 120, 110, 50);
		jl_msg.setBounds(120, 200, 90, 25);
		
		jp.add(jl_major);
		jp.add(jt_major);
		jp.add(jl_msg);
		jp.add(jb_send);
		jp.add(jb_back);
		jf.add(jp);
		
		
		jf.setTitle("人才信息查询");
		jf.setSize(400, 300);
		jf.setLocation(350, 150);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
	}
	public static void main(String[] args) {
		new sel_stu_need();
	}
}
