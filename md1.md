#Server端資料庫

*   分成兩個Java檔, 分別：ClientDBHelper.java、ClientDBProvider.java
*   ClientDBHelper.java    
    +包含 _SQLiteOpenHelper_ 的 __onCreate()__、 __onUpgrade__ 方法

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
    
    +第一個+為最開始資料庫不存在時,會自動run的程式方法,使用者不會使用到；第二個+的4個方法,直接呼叫方法名稱+()即可使用。
*   ClientDBProvider.java
    
    
## 功能

1.  __新增__


2.  __修改__

3.  __刪除__

4.  __查詢__
