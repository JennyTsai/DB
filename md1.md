#Client 端資料庫

*   Client端資料庫分成 **2個實體(user、target)** 和 **1個關連(study)** 。
*   分成兩個Java檔, 分別：ClientDBHelper.java、ClientDBProvider.java
*   **ClientDBHelper.java**
    
    + 包含 _SQLiteOpenHelper_ 的 __onCreate()__、 __onUpgrade__ 方法。

        `public void onCreate(SQLiteDatabase db)`

        `public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)`


    + 資料庫的 *開啟並寫入* ： __openToWrite()__ 、 *開啟並讀* ： __openToRead()__ 、
    	      *開啟* ： __onOpen(db)__、  *關閉* ： __close()__ 。
    
    + onCreate和onUpgrade是在資料庫不存在or版本更改時,會自動run的程式方法,使用者不會使用到；至於其他4個方法, _直接呼叫方法名稱+()_ 即可使用。

*   **ClientDBProvider.java**
    
    + 包含資料庫的4大功能方法。
    
## 功能

1.  __新增__
    * 個別新增改3張表(user、target、study), 所以會有3個新增方法。
    * __<新增> 的3個方法, 只要在()內加入正確對應的參數即可使用!!__
    * **user 新增**
    
     `public long user_insert(String v1,String v2,String v3,String v4)`
    
        + v1 → UID             (使用者帳號)
        + v2 → UNickname       (暱稱)
        + v3 → ULogged_code    (登入碼)
        + v4 → In_Learn_Time   (開始學習時間)
    * **target 新增**

     `target_insert(String v1,String v2,String v3,String v4,String v5)`
     
        + v1 → TID             (標的編號)
        + v2 → MapID           (地圖編號)
        + v3 → Map_Url         (地圖url)
        + v4 → MaterialID      (教材編號)
        + v5 → Material_Url    (教材url)
    * **study 新增**

        `study_insert(String v1,String v2,String v3,String v4,String v5,String v6,String v7,String v8)`
    
        + v1 → TID             (標的編號)
        + v2 → UID             (使用者帳號)
        + v3 → QID             (答題編號)
        + v4 → Answer          (答題對錯)
        + v5 → Answer_Time     (回答時間)
        + v6 → In_TargetTime   (進入標的時間)
        + v7 → Out_TargetTime  (離開標的時間)
        + v8 → TCheck          (有無正確到學習點)
        
    
2.  __修改__
    * 分成2種修改方法：update()、study_update()
    * __這2個方法()中只要加上 table名稱、要修改的內容、WHERE條件字串等參數即可使用。__
    * **user、target 修改**
    
        `update(String user_table,String newv1,String newv2,String where_string)`
        + user 參數
            + user_table → table名稱
            + newv1 → ULogged_code
            + newv2 → In_Learn_Time
            + where_string → WHERE條件
        + target 參數
            + user_table → table名稱
            + newv1 → Map_Url
            + newv2 → Material_Url
            + where_string → WHERE條件
    * **study 修改**
        
        `study_update(String user_table,String newv1,String newv2,String newv3,String newv4,String where_string)`


            + user_table → table名稱


        
3.  __刪除__
    * 此刪除方法會先判斷撰寫者要閃哪個 **Table** ,在做刪除動作。
        
        `delete(String where_string,String user_table)`

        + where_string → WHERE條件
        + user_table → 表格名稱


4.  __查詢__
