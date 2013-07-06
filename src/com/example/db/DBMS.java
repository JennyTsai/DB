package com.example.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBMS {	
	
	private static final String Study = "Study.db"; //資料庫名稱
	private static final int version = 1; //資料庫版本
	public static final String Student="student",Target="target",Study_stu="tar_stu_stu"; //Table name
	public static final String SNO="sno", Password="password", Start_time="start_time"; //Student item
	public static final String LNO="lno", MNO="mno", mapNO="mapno", M_URL="m_url", map_RUL="map_url"; //Target name
	private SQLiteDatabase sqlitedatabase; 
	private Context context;
	private DBHelper dbHelper;

	private static final String stu = //學生資料表
			"CREATE TABLE "+ Student +"( "
					  +SNO +"INTEGER NOT NULL," 
					  +Password +"CHAR(10) NOT NULL," 
					  +Start_time +"DATETIME NOT NULL"
					  +"PRIMARY KEY(SNO));"; 
	private static final String tar = //標的資料表
			"CREATE TABLE" + Target +"("
					  +LNO +"INTEGER NOT NULL PRIMARY KEY,"
					  +MNO +"CHAR(10) NOT NULL," 
					  +mapNO +"CHAR(10) NOT NULL,"
					  +M_URL +"VARCHAR(50) NOT NULL," 
					  +map_RUL +"VARCHAR(50) NOT NULL);";
	private static final String study = //學習關係資料表
			"CREATE TABLE [Study_stu] ("+
					  "[SSNO] INTEGER NOT NULL CONSTRAINT [SSNO] REFERENCES [Student]([SNO]),"+ 
					  "[LLNO] INTEGER NOT NULL CONSTRAINT [LLNO] REFERENCES [target]([LNO]),"+ 
					  "[NNNO] INTEGER NOT NULL,"+ 
					  "[solution] [CHAR(10)],"+ 
					  "[ans_time] DATETIME,"+ 
					  "[in_time] DATETIME NOT NULL,"+ 
					  "[out_time] DATETIME,"+ 
					  "[check] [CHAR(10)] NOT NULL);";

	public DBMS openToWrite() throws android.database.SQLException{
		
		dbHelper = new DBHelper(context,Study,null,version);
		sqlitedatabase = dbHelper.getWritableDatabase();
		
		return this;
	}
	public  DBMS(Context c){
		context = c;
	}

	public DBMS openToRead() throws android.database.SQLException{ //開啟資料庫並讀
		
		dbHelper = new DBHelper(context,Study,null,version);
		sqlitedatabase = dbHelper.getReadableDatabase();
		
		return this;
	}
	
	public void close(){  //關閉資料庫
		dbHelper.close();
	}
	
	public String All(){ 
		String[] columns = new String[]{SNO,Password,Start_time};
		Cursor cursor = sqlitedatabase.query(Study, columns, null, null, null, null, null, null);
		String result = "";
		for(cursor.moveToFirst();!(cursor.isAfterLast());cursor.moveToNext()){
			result = result + cursor.getString(0) +":: " 
					+ cursor.getString(1) + 
					" : : " + cursor.getString(2) + "\n";
		}
		return result;
	}
	
	public long insert(String v1,String v2,String v3){ //新增
	
		ContentValues contentvalues = new ContentValues();
		contentvalues.put("SNO", v1);
		contentvalues.put("Password", v2);
		contentvalues.put("Start_time", v3);
		return sqlitedatabase.insert(Student, null, contentvalues);
	}
    
	public long delete(String v){ //刪除
		
		String[] delv = new String[] {v};
		return sqlitedatabase.delete(Student, SNO+ "=?", delv);
	}
	
	
	public long update(String newv,String oldv){ //修改
		
		ContentValues contentvalues = new ContentValues();
		contentvalues.put(Password, newv);
		String[] oldvelue = new String[] {oldv};
		return sqlitedatabase.update(Student, contentvalues, SNO+ "=?", oldvelue);
	}

	public class DBHelper extends SQLiteOpenHelper{
		
		public DBHelper(Context context, String name,CursorFactory factory, int version){ //DBHelper建構子
			super(context,Study,null,version); //資料庫名稱=Study，目前版本=1
		}
		@Override
		public void onCreate(SQLiteDatabase db){ //第一次找不到，建立"新資料"
			
			db.execSQL(stu); //建立student
			db.execSQL(tar); //建立target
			db.execSQL(study); //建立study
		}
		public void onUpgrade(SQLiteDatabase db,int oldv,int newV){ 
		}
	}
}



