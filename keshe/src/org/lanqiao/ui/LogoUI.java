package org.lanqiao.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.lanqiao.model.UserModel;

public class LogoUI {
	JLabel lbl_name,lbl_pwd,lbl_msg;
	JTextField jtf_name;
	JPasswordField jpf_pwd;
	JButton btn_login;
	JFrame f;
	MainUI mu;
	
	
	public LogoUI(){
		lbl_name=new JLabel("账号：");
		lbl_pwd=new JLabel("密码：");
		lbl_msg=new JLabel();
		jtf_name=new JTextField();
		jpf_pwd=new JPasswordField();
		btn_login=new JButton("登陆");
		JPanel p=new JPanel();
		f=new JFrame();
		
		btn_login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String uname=jtf_name.getText();
				String upwd=new String(jpf_pwd.getPassword());
				UserModel um=new UserModel();
				if(um.loginUser(uname, upwd)){
					lbl_msg.setText("登陆成功");
					new MainUI();
					f.dispose();
				}else{
					lbl_msg.setText("用户名或密码错误");
				}
			}
		});
		
		p.setLayout(null);
		lbl_name.setBounds(30, 30, 60, 30);
		lbl_pwd.setBounds(30, 70, 60, 30);
		jtf_name.setBounds(100, 30, 120, 30);
		jpf_pwd.setBounds(100, 70, 120, 30);
		btn_login.setBounds(100, 120, 80, 30);
		lbl_msg.setBounds(50, 160, 200, 30);
		
		p.add(lbl_name);
		p.add(jtf_name);
		p.add(lbl_pwd);
		p.add(jpf_pwd);
		p.add(btn_login);
		p.add(lbl_msg);
		f.add(p);
		
		f.setTitle("管理员登陆");
		f.setSize(300, 300);
		f.setLocation(350, 150);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	public static void main(String[] args) {
		new LogoUI();
	}
}
