package org.lanqiao.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainUI {
	/**
	 * ���ܣ�
	 *   ��Ӵ�ѧ��
	 *   �����ҵ
	 *   ��ѧ����Ϣ��ѯ
	 *   ��ѧ���˲���Ϣ����
	 *   ��ҵ��Ϣ��ѯ
	 *   ��ҵ���󷢲�
	 *   ��ҵ������Ϣͳ��
	 *   ���ݻָ�
	 */
	JButton jbt_add_stu,jbt_add_com,btn_mess_stu,btn_mess_com,btn_sel_stu,btn_sel_com,btn_cen_com,btn_rec;
	JFrame jf;
	JPanel jp;
	public MainUI(){
		jbt_add_stu=new JButton("��Ӵ�ѧ��");
		jbt_add_com=new JButton("�����ҵ");
		btn_mess_stu=new JButton("��ѧ����Ϣ��ѯ");
		btn_mess_com=new JButton("��ҵ��Ϣ��ѯ");
		btn_sel_stu=new JButton("�˲���Ϣ����");
		btn_sel_com=new JButton("��ҵ���󷢲�");
		btn_cen_com=new JButton("��ҵ������Ϣͳ��");
		btn_rec=new JButton("���ݱ�����ָ�");
		jp=new JPanel();
		jf=new JFrame("��ҳ��");
		
		//�¼�
		//��Ӵ�ѧ��
		jbt_add_stu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Add_stu();
				jf.dispose();
			}
		});
		//��ѧ����Ϣ��ѯ
		btn_mess_stu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Sel_stu();
				jf.dispose();
			}
		});
		//�����ҵ��Ϣ
		jbt_add_com.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Add_Com();
				jf.dispose();
			}
		});
		//��ҵ��Ϣ��ѯ
		btn_mess_com.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new sel_com();
				jf.dispose();
			}
		});
		//��ҵ������Ϣͳ��
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
				// TODO ���ݱ���
				new stu_save();
				jf.dispose();
				
			}
		});
		
		//��������
		jbt_add_stu.setFont(new java.awt.Font("����", Font.BOLD,18));
		jbt_add_com.setFont(new java.awt.Font("����", Font.BOLD,18));
		btn_mess_stu.setFont(new java.awt.Font("����", Font.BOLD,18));
		btn_mess_com.setFont(new java.awt.Font("����", Font.BOLD,18));
		btn_sel_stu.setFont(new java.awt.Font("����", Font.BOLD,18));
		btn_sel_com.setFont(new java.awt.Font("����", Font.BOLD,18));
		btn_rec.setFont(new java.awt.Font("����", Font.BOLD,18));
		btn_cen_com.setFont(new java.awt.Font("����", Font.BOLD,18));
		
		//λ������
		jp.setLayout(null);
		jbt_add_stu.setBounds(40, 20, 200, 80);
		jbt_add_com.setBounds(320, 20, 200, 80);
		btn_mess_stu.setBounds(40, 120, 200, 80);
		btn_mess_com.setBounds(320, 120, 200, 80);
		btn_sel_stu.setBounds(40, 220, 200, 80);
		btn_sel_com.setBounds(320, 220, 200, 80);
		btn_rec.setBounds(40, 320, 200, 80);
		btn_cen_com.setBounds(320, 320, 200, 80);
		
		//�ؼ���ӵ����������ӵ�������
		jp.add(jbt_add_stu);
		jp.add(jbt_add_com);
		jp.add(btn_mess_stu);
		jp.add(btn_mess_com);
		jp.add(btn_sel_stu);
		jp.add(btn_sel_com);
		jp.add(btn_cen_com);
		jp.add(btn_rec);
		jf.add(jp);
		
		
		
		
		//���ô�������
		jf.setSize(600, 500);
		jf.setLocation(400, 100);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		
		
	}
	public static void main(String[] args) {
		new MainUI();
	}
}
