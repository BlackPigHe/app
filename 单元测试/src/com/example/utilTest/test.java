package com.example.utilTest;

import android.test.AndroidTestCase;

public class test extends AndroidTestCase {

	/**
	 * @param args
	 */
	public static void AddTest(String[] args) {
		// TODO �Զ����ɵķ������
		Jiafa jiafa=new Jiafa();
		int result=jiafa.add(5, 3);
		//����
		assertEquals(8,result);
	}

}
