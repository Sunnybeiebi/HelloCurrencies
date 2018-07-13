package com.example.administrator.hellocurrencies;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2018/7/11.
 */

public class MyDBHelper extends SQLiteOpenHelper {
    //建表语句
    public static final String sql="create table userData("+
            "id interger primary key autoincrement,"
            +"name text,"
            +"password text)";
    private Context mContext;
    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory,int version){
        super(context,name,cursorFactory,version);
        mContext=context;
    }
    //创建数据库
    public void onCreate(SQLiteDatabase db){
        db.execSQL(sql);
    }
    //用于升级软件时更新数据库表结构
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
}
