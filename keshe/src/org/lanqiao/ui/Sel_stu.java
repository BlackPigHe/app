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
import org.lanqiao.model.StuModel;

public class Sel_stu {
	JLabel jl_stuid,jl_msg;
	JTextField jt_stuid;
	JButton jb_send,jb_back;
	JPanel jp;
	JFrame jf;
	public Sel_stu(){
		jl_stuid=new JLabel("请输入学号：");
		jl_msg=new JLabel();
		jt_stuid=new JTextField();
		jb_send=new JButton("查找");
		jb_back=new JButton("返回上一级");
		jp=new JPanel();
		jf=new JFrame();
		
		jb_send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StuModel sm=new StuModel();
				String sid=jt_stuid.getText();
				int id=Integer.valueOf(sid);
				Student stu=new Student();
				stu=sm.selstu(id);
				if(stu!=null){
					new mes_stu(stu);
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
		
		
		
		jl_stuid.setFont(new Font("宋体", Font.BOLD, 15));
		
		jp.setLayout(null);
		jl_stuid.setBounds(30, 20, 110, 25);
		jt_stuid.setBounds(140, 20, 150, 25);
		jb_send.setBounds(80, 120, 90, 50);
		jb_back.setBounds(200, 120, 110, 50);
		jl_msg.setBounds(120, 200, 50, 25);
		
		jp.add(jl_stuid);
		jp.add(jt_stuid);
		jp.add(jl_msg);
		jp.add(jb_send);
		jp.add(jb_back);
		jf.add(jp);
		
		
		
		jf.setTitle("大学生信息查询");
		jf.setSize(400, 300);
		jf.setLocation(350, 150);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
	}
	public static void main(String[] args) {
		new Sel_stu();
	}
}
