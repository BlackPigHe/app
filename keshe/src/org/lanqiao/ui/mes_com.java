package org.lanqiao.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.lanqiao.entity.Compare;
import org.lanqiao.proc.ProcCom;

public class mes_com {
	JLabel jl_name,jl_city,jl_grade,jl_msg;
	JTextField jt_name,jt_city,jt_grade;
	JButton jb_send,jb_del,jb_back;
	JPanel jp;
	JFrame jf;
	public mes_com(Compare com){
		jl_name=new JLabel("企业名称：");
		jl_city=new JLabel("企业地点：");
		jl_grade=new JLabel("企业级别：");
		jl_msg=new JLabel("");
		jt_name=new JTextField(com.getName());
		jt_city=new JTextField(com.getCity());
		jt_grade=new JTextField(com.getGrade());
		jb_send=new JButton("更新");
		jb_del=new JButton("删除");
		jb_back=new JButton("返回");
		jp=new JPanel();
		jf=new JFrame();
		
		//事件
		jb_send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name=jt_name.getText();
				String city=jt_city.getText();
				String grade=jt_grade.getText();
				Compare com=new Compare(name, city, grade);
				ProcCom pc=new ProcCom();
				if(pc.updatestu(com)){
					jl_msg.setText("更新成功");
				}else{
					jl_msg.setText("更新失败！");
				}
			}
		});
		jb_del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name=jt_name.getText();
				ProcCom pc=new ProcCom();
				if(pc.detcom(name)){
					jl_msg.setText("删除成功");
					new sel_com();
					jf.dispose();
				}else{
					jl_msg.setText("删除失败");
				}
			}
		});
		jb_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new sel_com();
				jf.dispose();
			}
		});
		
		
		jp.setLayout(null);
		jl_name.setBounds(40, 30, 80, 30);
		jt_name.setBounds(130, 30, 100, 30);
		jl_city.setBounds(40, 70, 80, 30);
		jt_city.setBounds(130, 70, 100, 30);
		jl_grade.setBounds(40, 110, 80, 30);
		jt_grade.setBounds(130, 110, 100, 30);
		jb_send.setBounds(60, 160, 70, 40);
		jb_del.setBounds(140, 160, 70, 40);
		jb_back.setBounds(220, 160, 70, 40);
		jl_msg.setBounds(200, 220, 80, 40);
		
		jp.add(jl_name);
		jp.add(jt_name);
		jp.add(jl_city);
		jp.add(jt_city);
		jp.add(jl_grade);
		jp.add(jt_grade);
		jp.add(jb_send);
		jp.add(jb_back);
		jp.add(jb_del);
		jp.add(jl_msg);
		jf.add(jp);
		
		jf.setTitle("企业信息添加");
		jf.setSize(400, 300);
		jf.setLocation(350, 150);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	public static void main(String[] args) {
		//new Sel_com();
	}
}
