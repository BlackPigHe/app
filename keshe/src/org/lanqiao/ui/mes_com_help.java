package org.lanqiao.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.lanqiao.entity.Compare;

public class mes_com_help {
	JLabel jl_msg;
	JTextArea jta;
	JButton jb_back;
	JPanel jp;
	JFrame jf;
	public mes_com_help(ArrayList<Compare> list){
		jl_msg=new JLabel("查询结果如下：");
		jta=new JTextArea();
		jta.setEditable(false);
		jta.setLineWrap(true);// 激活自动换行功能
		jb_back=new JButton("返回");
		jp=new JPanel();
		jf=new JFrame("企业需求统计分析");
		
		jta.setText("名称\t地点\t职位\t数量\t时间\n");
		for(Compare com:list){
			//String str=com.toString();
			jta.append(com.toString()+"\n");
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
		jb_back.setBounds(500, 5, 80, 20);
		jta.setBounds(30, 30, 500, 400);
		
		jp.add(jl_msg);
		jp.add(jb_back);
		jf.add(jta);
		jf.add(jp);
		
		jf.setSize(600, 500);
		jf.setLocation(350, 100);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}
