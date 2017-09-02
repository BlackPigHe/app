package org.lanqiao.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.lanqiao.entity.Compare;
import org.lanqiao.proc.ProcComHelp;

public class add_com_help {
	JLabel jl_name,jl_city,jl_need,jl_num,jl_time,jl_msg;
	JTextField jt_name,jt_city,jt_need,jt_num,jt_time;
	JButton jb_send,jb_back;
	JPanel jp;
	JFrame jf;
	public add_com_help(){
		jl_name=new JLabel("企业名称：");
		jl_city=new JLabel("企业地点：");
		jl_need=new JLabel("招聘职位：");
		jl_num=new JLabel("招聘数量：");
		jl_time=new JLabel("发布时间：");
		jl_msg=new JLabel("");
		jt_name=new JTextField("");
		jt_city=new JTextField("");
		jt_need=new JTextField("");
		jt_num=new JTextField("");
		jt_time=new JTextField("2000-00-00");
		jb_send=new JButton("提交");
		jb_back=new JButton("返回");
		jp=new JPanel();
		jf=new JFrame();
		
		//事件
		jb_send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name=jt_name.getText();
				String city=jt_city.getText();
				String need=jt_need.getText();
				String snum=jt_num.getText();
				int num=Integer.valueOf(snum);
				String time=jt_time.getText();
				Compare com=new Compare(name, city, need, num, time);
				ProcComHelp pch=new ProcComHelp();
				if(pch.com_help_Insert(com)){
					jl_msg.setText("提交成功");
				}else{
					jl_msg.setText("提交失败！");
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
		
		
		jp.setLayout(null);
		jl_name.setBounds(40, 30, 80, 30);
		jt_name.setBounds(130, 30, 100, 30);
		jl_city.setBounds(40, 70, 80, 30);
		jt_city.setBounds(130, 70, 100, 30);
		jl_need.setBounds(40, 110, 80, 30);
		jt_need.setBounds(130, 110, 100, 30);
		jl_num.setBounds(40, 150, 80, 30);
		jt_num.setBounds(130, 150, 100, 30);
		jl_time.setBounds(40, 190, 100, 30);
		jt_time.setBounds(130, 190, 100, 30);
    	jb_send.setBounds(100, 230, 80, 40);
 		jb_back.setBounds(240, 230, 80, 40);
 		jl_msg.setBounds(200, 280, 80, 40);
		
		jp.add(jl_name);
		jp.add(jt_name);
		jp.add(jl_city);
		jp.add(jt_city);
		jp.add(jl_need);
		jp.add(jt_need);
		jp.add(jl_num);
		jp.add(jt_num);
		jp.add(jl_time);
		jp.add(jt_time);
		jp.add(jb_send);
		jp.add(jb_back);
		jp.add(jl_msg);
		jf.add(jp);
		
		jf.setTitle("企业需求发布");
		jf.setSize(500, 400);
		jf.setLocation(350, 150);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	public static void main(String[] args) {
		new add_com_help();
	}
}
