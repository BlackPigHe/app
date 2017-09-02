package com.example.utilTest;

import android.test.AndroidTestCase;

public class test extends AndroidTestCase {

	/**
	 * @param args
	 */
	public static void AddTest(String[] args) {
		// TODO 自动生成的方法存根
		Jiafa jiafa=new Jiafa();
		int result=jiafa.add(5, 3);
		//断言
		assertEquals(8,result);
	}

}
