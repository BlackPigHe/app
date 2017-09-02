package com.example.Magneto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelpe extends SQLiteOpenHelper {

	public MyOpenHelpe(Context context) {
		super(context, "login_db.db", null, 1);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table info(_id integer primary key autoincrement,userName varchar(20),password varchar(20))");
		db.execSQL("insert into info (userName,password) values (?,?)",new Object[]{"admin","root"});
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
