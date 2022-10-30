package vn.edu.poly.thimob201_27_10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ObjectDAO {
    SQLiteDatabase sqLiteDatabase;
    MyDatabase myDatabase;

    public ObjectDAO(Context context) {
        myDatabase =new MyDatabase(context);
        sqLiteDatabase = myDatabase.getWritableDatabase();
    }

    public void close(){
        myDatabase.close();
    }

    public long insertItem(ObjectRss objectRss){
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", objectRss.getTitle());
        contentValues.put("description", objectRss.getDescription());
        contentValues.put("link", objectRss.getLink());
        long res = sqLiteDatabase.insert("tbObj", null, contentValues);
        return  res;
    }

    public ArrayList<ObjectRss> selectAll(){
        ArrayList<ObjectRss> lists = new ArrayList<>();
        String sqlSelect = "SELECT * FROM  tbObj";
        Cursor cursor = sqLiteDatabase.rawQuery(sqlSelect,null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()){
                ObjectRss objectRss = new ObjectRss();
                objectRss.setId(cursor.getInt(0));
                objectRss.setTitle(cursor.getString(1));
                objectRss.setDescription(cursor.getString(2));
                objectRss.setLink(cursor.getString(3));
                lists.add(objectRss);

                cursor.moveToNext();
            }
        }

        return lists;
    }


}
