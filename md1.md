#Client端資料庫

*   Client端資料庫分成 **2個實體(user、target)** 和 **1個關連(study)** 。
*   分成兩個Java檔, 分別：ClientDBHelper.java、ClientDBProvider.java
*   **ClientDBHelper.java**
    
    +包含 _SQLiteOpenHelper_ 的 __onCreate()__、 __onUpgrade__ 方法。

        public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
    	db.execSQL(us); //建立student
		db.execSQL(tar); //建立target
		db.execSQL(sstudy); //建立study
        }
        -------------------------------------------------------------------------------
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//oldVersion=舊的資料庫版本；newVersion=新的資料庫版本
		// TODO Auto-generated method stub
        }


    +資料庫的 *開啟並寫入* ： __openToWrite()__ 、 *開啟並讀* ： __openToRead()__ 、
    	      *開啟* ： __onOpen(db)__、  *關閉* ： __close()__ 。
    
    +第一個+為最開始資料庫不存在時,會自動run的程式方法,使用者不會使用到；第二個   +的4個方法,直接呼叫方法名稱+()即可使用。

*   **ClientDBProvider.java**
    
    +包含資料庫的4大功能方法。
    
## 功能

1.  __新增__

    * 個別新增改3張表(user、target、study), 所以會有3個新增方法。
    * **user 新增** 
    
        `public long user_insert(String v1,String v2,String v3,String v4) { 
    	ContentValues contentvalues = new ContentValues();
		contentvalues.put("UID", v1);
		contentvalues.put("UName", v2);
		contentvalues.put("ULogged_no", v3);
		contentvalues.put("In_Learn_Time", v4);
		return sqlitedatabase.insert("user", null, contentvalues);
        }`
    
    * **target 新增**
    
        `public long target_insert(String v1,String v2,String v3,String v4,String v5){ 
    	ContentValues contentvalues = new ContentValues();
		contentvalues.put("TID", v1);
		contentvalues.put("MapID", v2);
		contentvalues.put("Map_Url", v3);
		contentvalues.put("MaterialID", v4);
		contentvalues.put("Material_Url", v5);
		return sqlitedatabase.insert("target", null, contentvalues);
	}`
    
    * **study 新增**
    
        `public long study_insert(String v1,String v2,String v3,String v4,String v5,String v6,String v7,String v8){
    	ContentValues contentvalues = new ContentValues();
		contentvalues.put("TID", v1);
		contentvalues.put("UID", v2);
		contentvalues.put("QID", v3);
		contentvalues.put("Answer", v4);
		contentvalues.put("Answer_Time", v5);
		contentvalues.put("In_TargetTime", v6);
		contentvalues.put("Out_TargetTime", v7);
		contentvalues.put("TCheck", v8);
		return sqlitedatabase.insert("study", null, contentvalues);
	}`
    
2.  __修改__

3.  __刪除__

4.  __查詢__
