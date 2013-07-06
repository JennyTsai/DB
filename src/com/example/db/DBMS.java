package com.example.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBMS {	
	
	private static final String Study = "Study.db"; //��Ʈw�W��
	private static final int version = 1; //��Ʈw����
	public static final String Student="student",Target="target",Study_stu="tar_stu_stu"; //Table name
	public static final String SNO="sno", Password="password", Start_time="start_time"; //Student item
	public static final String LNO="lno", MNO="mno", mapNO="mapno", M_URL="m_url", map_RUL="map_url"; //Target name
	private SQLiteDatabase sqlitedatabase; 
	private Context context;
	private DBHelper dbHelper;

	private static final String stu = //�ǥ͸�ƪ�
			"CREATE TABLE "+ Student +"( "
					  +SNO +"INTEGER NOT NULL," 
					  +Password +"CHAR(10) NOT NULL," 
					  +Start_time +"DATETIME NOT NULL"
					  +"PRIMARY KEY(SNO));"; 
	private static final String tar = //�Ъ���ƪ�
			"CREATE TABLE" + Target +"("
					  +LNO +"INTEGER NOT NULL PRIMARY KEY,"
					  +MNO +"CHAR(10) NOT NULL," 
					  +mapNO +"CHAR(10) NOT NULL,"
					  +M_URL +"VARCHAR(50) NOT NULL," 
					  +map_RUL +"VARCHAR(50) NOT NULL);";
	private static final String study = //�ǲ����Y��ƪ�
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

	public DBMS openToRead() throws android.database.SQLException{ //�}�Ҹ�Ʈw��Ū
		
		dbHelper = new DBHelper(context,Study,null,version);
		sqlitedatabase = dbHelper.getReadableDatabase();
		
		return this;
	}
	
	public void close(){  //������Ʈw
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
	
	public long insert(String v1,String v2,String v3){ //�s�W
	
		ContentValues contentvalues = new ContentValues();
		contentvalues.put("SNO", v1);
		contentvalues.put("Password", v2);
		contentvalues.put("Start_time", v3);
		return sqlitedatabase.insert(Student, null, contentvalues);
	}
    
	public long delete(String v){ //�R��
		
		String[] delv = new String[] {v};
		return sqlitedatabase.delete(Student, SNO+ "=?", delv);
	}
	
	
	public long update(String newv,String oldv){ //�ק�
		
		ContentValues contentvalues = new ContentValues();
		contentvalues.put(Password, newv);
		String[] oldvelue = new String[] {oldv};
		return sqlitedatabase.update(Student, contentvalues, SNO+ "=?", oldvelue);
	}

	public class DBHelper extends SQLiteOpenHelper{
		
		public DBHelper(Context context, String name,CursorFactory factory, int version){ //DBHelper�غc�l
			super(context,Study,null,version); //��Ʈw�W��=Study�A�ثe����=1
		}
		@Override
		public void onCreate(SQLiteDatabase db){ //�Ĥ@���䤣��A�إ�"�s���"
			
			db.execSQL(stu); //�إ�student
			db.execSQL(tar); //�إ�target
			db.execSQL(study); //�إ�study
		}
		public void onUpgrade(SQLiteDatabase db,int oldv,int newV){ 
		}
	}
}



