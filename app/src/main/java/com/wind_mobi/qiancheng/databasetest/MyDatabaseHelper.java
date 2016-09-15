package com.wind_mobi.qiancheng.databasetest;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by qiancheng on 2016/8/25.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {


    /*public static final String CREATE_BOOK = "create table book("
            + "id integer primary key autoincrement ;"
            + "author text ;"
            + "price real ;"
            + "page integer )";

    private Context mContext;
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }*/

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*sqLiteDatabase.execSQL(CREATE_BOOK);
        Toast.makeText(mContext,"execSQL successed",Toast.LENGTH_SHORT).show();*/
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("test.db", Context.MODE_PRIVATE, null);
        db.execSQL("DROP TABLE IF EXIST PERSION");
        db.execSQL("CREATE TABLE persion(_id INTEGER primary key, name VARCHAR, age SMALLINT)");
        Persion persion = new Persion();
        persion.name = "wang";
        persion.age = "12";
        db.execSQL("INSERT INTO persion VALUES(null,?,?)", new String[]{persion.name, persion.age});

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", persion.name);
        contentValues.put("age", persion.age);
        db.insert("persion", null, contentValues);

        contentValues = new ContentValues();
        contentValues.put("age", 35);
        db.update("persion", contentValues, "name = ?", new String[]{"33"});

        Cursor cursor = db.query("SELETE * FROM persion WHERE age > ?" new String[]{"33"});
        while (cursor.moveToNext()) {
            int _id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("_id"));
            int age = cursor.getInt(cursor.getColumnIndex("age"));
            cursor.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVwesion) {
        sqLiteDatabase.execSQL("drop table if exists Book");
        sqLiteDatabase.execSQL("drop table if exists Category");
        onCreate(sqLiteDatabase);
    }
}
