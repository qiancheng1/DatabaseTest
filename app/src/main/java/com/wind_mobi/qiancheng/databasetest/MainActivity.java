package com.wind_mobi.qiancheng.databasetest;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {

    public MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        myDatabaseHelper = new MyDatabaseHelper(this, "book.db", null, 2);
        Button createbook = (Button) findViewById(R.id.create_book);
        createbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDatabaseHelper.getWritableDatabase();
            }
        });

        Button addData = (Button)findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("author", "Davinci");
                contentValues.put("price", 16.04);
                contentValues.put("page", 900);
                sqLiteDatabase.insert("book", null, contentValues);
                contentValues.clear();

                contentValues.put("author", "Brown");
                contentValues.put("price", 99.04);
                contentValues.put("page", 900);
                sqLiteDatabase.insert("book", null, contentValues);
                contentValues.clear();
            }
        });

        Button upgradeData = (Button) findViewById(R.id.upgrade_data);
        upgradeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
                sqLiteDatabase.beginTransaction();
                boolean flag = false;
                try {
                    while (flag) {
                        throw new NullPointerException();
                    }
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("price", 10.99);
                    sqLiteDatabase.update("book", contentValues, "author=", new String[]{"Davinci"});
                    sqLiteDatabase.setTransactionSuccessful();
                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    flag = true;
                    sqLiteDatabase.endTransaction();
                }
            }{
            }
        });

        Button deleteData = (Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
                sqLiteDatabase.delete("book", "page>", new String[]{"899"});
            }
        });

        Button changeText = (Button) findViewById(R.id.change_text);
        changeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
