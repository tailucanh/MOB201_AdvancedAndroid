package vn.edu.poly.thimob201_27_10;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {

    public static String DB_NAME = "MyData";
    public static int DB_VERSION = 1;

    public MyDatabase(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String table = "CREATE TABLE  tbObj (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,title TEXT NOT NULL,description TEXT NOT NULL ,link TEXT NOT NULL );";
        db.execSQL(table);


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
