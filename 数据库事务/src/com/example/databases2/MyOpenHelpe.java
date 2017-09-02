package com.example.databases2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelpe extends SQLiteOpenHelper {

	public MyOpenHelpe(Context context) {
		super(context, "Account.db", null, 1);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table info(_id integer primary key autoincrement,name varchar(20),phone varchar(20),money varchar(20))");
		db.execSQL("insert into info (name,phone,money) values (?,?,?)",new Object[]{"张三","110","5000"});
		db.execSQL("insert into info (name,phone,money) values (?,?,?)",new Object[]{"李四","120","6000"});
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO 自动生成的方法存根

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
