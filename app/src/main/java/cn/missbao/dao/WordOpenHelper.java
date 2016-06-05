package cn.missbao.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Role
 * Created by 冀泽阳 on 2016/4/13.
 */
public class WordOpenHelper extends SQLiteOpenHelper {

    public WordOpenHelper(Context context) {
        super(context, "word.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table word(id integer primary key autoincrement,_id varchar(20),word varchar(20)," +
                "soundmark varchar(40),translate varchar(20))");
        db.execSQL("create table todayfull(id integer primary key autoincrement,_id varchar(20),word varchar(20)," +
                "soundmark varchar(40),translate varchar(20))");
        db.execSQL("create table today(id integer primary key autoincrement,_id varchar(20),word varchar(20)," +
                "soundmark varchar(40),translate varchar(20))");
        db.execSQL("create table one_day(id integer primary key autoincrement,_id varchar(20),word varchar(20)," +
                "soundmark varchar(40),translate varchar(20))");
        db.execSQL("create table tow_day(id integer primary key autoincrement,_id varchar(20),word varchar(20)," +
                "soundmark varchar(40),translate varchar(20))");
        db.execSQL("create table three_day(id integer primary key autoincrement,_id varchar(20),word varchar(20)," +
                "soundmark varchar(40),translate varchar(20))");
        db.execSQL("create table four_day(id integer primary key autoincrement,_id varchar(20),word varchar(20)," +
                "soundmark varchar(40),translate varchar(20))");
        db.execSQL("create table five_day(id integer primary key autoincrement,_id varchar(20),word varchar(20)," +
                "soundmark varchar(40),translate varchar(20))");
        db.execSQL("create table six_day(id integer primary key autoincrement,_id varchar(20),word varchar(20)," +
                "soundmark varchar(40),translate varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
