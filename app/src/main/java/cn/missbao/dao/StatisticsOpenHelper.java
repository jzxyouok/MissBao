package cn.missbao.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Role
 * Created by 冀泽阳 on 2016/5/1.
 */
public class StatisticsOpenHelper extends SQLiteOpenHelper {

    public StatisticsOpenHelper(Context context) {
        super(context, "statistics.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table statistics(id integer primary key autoincrement,name varchar(10),month integer," +
                "day integer,_all integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
