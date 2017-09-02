package org.lanqiao.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.lanqiao.entity.Compare;
import org.lanqiao.model.ComModel;

public class sel_com {
	JLabel jl_name,jl_msg;
	JTextField jt_name;
	JButton jb_send,jb_back;
	JPanel jp;
	JFrame jf;
	public sel_com(){
		jl_name=new JLabel("请输入名称：");
		jl_msg=new JLabel();
		jt_name=new JTextField();
		jb_send=new JButton("查找");
		jb_back=new JButton("返回上一级");
		jp=new JPanel();
		jf=new JFrame();
		
		jb_send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ComModel cm=new ComModel();
				String name=jt_name.getText();
				Compare com=cm.selstu(name);
				if(com!=null){
					new mes_com(com);
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
		
		
		
		jl_name.setFont(new Font("宋体", Font.BOLD, 15));
		
		jp.setLayout(null);
		jl_name.setBounds(30, 20, 110, 25);
		jt_name.setBounds(140, 20, 150, 25);
		jb_send.setBounds(80, 120, 90, 50);
		jb_back.setBounds(200, 120, 110, 50);
		jl_msg.setBounds(120, 200, 90, 25);
		
		jp.add(jl_name);
		jp.add(jt_name);
		jp.add(jl_msg);
		jp.add(jb_send);
		jp.add(jb_back);
		jf.add(jp);
		
		
		jf.setTitle("企业信息查询");
		jf.setSize(400, 300);
		jf.setLocation(350, 150);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
	}
	public static void main(String[] args) {
		new sel_com();
	}
}
