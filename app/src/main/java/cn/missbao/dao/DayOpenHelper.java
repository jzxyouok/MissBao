package cn.missbao.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Role
 * Created by 冀泽阳 on 2016/5/1.
 */
public class DayOpenHelper extends SQLiteOpenHelper {

    public DayOpenHelper(Context context) {
        super(context, "day.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table day(id integer primary key autoincrement,month integer," +
                "number integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
