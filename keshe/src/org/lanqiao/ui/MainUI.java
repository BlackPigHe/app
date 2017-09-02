package org.lanqiao.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainUI {
	/**
	 * 功能：
	 *   添加大学生
	 *   添加企业
	 *   大学生信息查询
	 *   大学生人才信息检索
	 *   企业信息查询
	 *   企业需求发布
	 *   企业历年信息统计
	 *   数据恢复
	 */
	JButton jbt_add_stu,jbt_add_com,btn_mess_stu,btn_mess_com,btn_sel_stu,btn_sel_com,btn_cen_com,btn_rec;
	JFrame jf;
	JPanel jp;
	public MainUI(){
		jbt_add_stu=new JButton("添加大学生");
		jbt_add_com=new JButton("添加企业");
		btn_mess_stu=new JButton("大学生信息查询");
		btn_mess_com=new JButton("企业信息查询");
		btn_sel_stu=new JButton("人才信息检索");
		btn_sel_com=new JButton("企业需求发布");
		btn_cen_com=new JButton("企业历年信息统计");
		btn_rec=new JButton("数据备份与恢复");
		jp=new JPanel();
		jf=new JFrame("主页面");
		
		//事件
		//添加大学生
		jbt_add_stu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Add_stu();
				jf.dispose();
			}
		});
		//大学生信息查询
		btn_mess_stu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Sel_stu();
				jf.dispose();
			}
		});
		//添加企业信息
		jbt_add_com.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Add_Com();
				jf.dispose();
			}
		});
		//企业信息查询
		btn_mess_com.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new sel_com();
				jf.dispose();
			}
		});
		//企业历年信息统计
		btn_cen_com.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new sel_com_help();
				jf.dispose();
			}
		});
		btn_sel_com.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new add_com_help();
				jf.dispose();
			}
		});
		btn_sel_stu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new sel_stu_need();
				jf.dispose();
			}
		});
		//
		btn_rec.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 数据备份
				new stu_save();
				jf.dispose();
				
			}
		});
		
		//字体设置
		jbt_add_stu.setFont(new java.awt.Font("宋体", Font.BOLD,18));
		jbt_add_com.setFont(new java.awt.Font("宋体", Font.BOLD,18));
		btn_mess_stu.setFont(new java.awt.Font("宋体", Font.BOLD,18));
		btn_mess_com.setFont(new java.awt.Font("宋体", Font.BOLD,18));
		btn_sel_stu.setFont(new java.awt.Font("宋体", Font.BOLD,18));
		btn_sel_com.setFont(new java.awt.Font("宋体", Font.BOLD,18));
		btn_rec.setFont(new java.awt.Font("宋体", Font.BOLD,18));
		btn_cen_com.setFont(new java.awt.Font("宋体", Font.BOLD,18));
		
		//位置设置
		jp.setLayout(null);
		jbt_add_stu.setBounds(40, 20, 200, 80);
		jbt_add_com.setBounds(320, 20, 200, 80);
		btn_mess_stu.setBounds(40, 120, 200, 80);
		btn_mess_com.setBounds(320, 120, 200, 80);
		btn_sel_stu.setBounds(40, 220, 200, 80);
		btn_sel_com.setBounds(320, 220, 200, 80);
		btn_rec.setBounds(40, 320, 200, 80);
		btn_cen_com.setBounds(320, 320, 200, 80);
		
		//控件添加到面板里，面板添加到窗体里
		jp.add(jbt_add_stu);
		jp.add(jbt_add_com);
		jp.add(btn_mess_stu);
		jp.add(btn_mess_com);
		jp.add(btn_sel_stu);
		jp.add(btn_sel_com);
		jp.add(btn_cen_com);
		jp.add(btn_rec);
		jf.add(jp);
		
		
		
		
		//设置窗体属性
		jf.setSize(600, 500);
		jf.setLocation(400, 100);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		
		
	}
	public static void main(String[] args) {
		new MainUI();
	}
}
