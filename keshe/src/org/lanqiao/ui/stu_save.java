package org.lanqiao.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.lanqiao.dbc.TestDbToExcel;
import org.lanqiao.dbc.TestExcelToDb;

public class stu_save {
	JLabel jl_msg;
	JButton jb_save,jb_rec,jb_back;
	JPanel jp;
	JFrame jf;
	public stu_save(){
		jb_save=new JButton("数据备份");
		jb_rec=new JButton("数据恢复");
		jb_back=new JButton("<——");
		jl_msg=new JLabel("");
		jp=new JPanel();
		jf=new JFrame("数据恢复与备份");
		
		jb_save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(new TestDbToExcel().stu_save()){
					jl_msg.setText("备份成功");
				}else{
					jl_msg.setText("备份失败");
				}
			}
		});
		jb_rec.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(new TestExcelToDb().stu_rec()){
					jl_msg.setText("恢复成功");
				}else{
					jl_msg.setText("恢复失败");
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
		jb_save.setBounds(50, 100, 100, 40);
		jb_rec.setBounds(180, 100, 100, 40);
		jb_back.setBounds(300, 10, 80, 30);
		jl_msg.setBounds(100, 150, 80, 40);
		
		jp.add(jb_rec);
		jp.add(jb_save);
		jp.add(jb_back);
		jp.add(jl_msg);
		jf.add(jp);
		
		
		jf.setSize(400, 300);
		jf.setLocation(350, 100);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	public static void main(String[] args) {
		new stu_save();
	}
}	
