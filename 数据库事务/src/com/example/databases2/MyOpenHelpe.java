package com.example.databases2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelpe extends SQLiteOpenHelper {

	public MyOpenHelpe(Context context) {
		super(context, "Account.db", null, 1);
		// TODO �Զ����ɵĹ��캯�����
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table info(_id integer primary key autoincrement,name varchar(20),phone varchar(20),money varchar(20))");
		db.execSQL("insert into info (name,phone,money) values (?,?,?)",new Object[]{"����","110","5000"});
		db.execSQL("insert into info (name,phone,money) values (?,?,?)",new Object[]{"����","120","6000"});
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO �Զ����ɵķ������

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������

	}

}
