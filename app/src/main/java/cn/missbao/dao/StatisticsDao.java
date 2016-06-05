package cn.missbao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.missbao.bean.Statistics;
import cn.missbao.utils.LogUtil;

/**
 * Role
 * Created by 冀泽阳 on 2016/5/1.
 */
public class StatisticsDao {

    private StatisticsOpenHelper openHelper;

    public StatisticsDao(Context context) {
        openHelper = new StatisticsOpenHelper(context);
    }

    /** 添加一个天 */
    public void addStatistics(Statistics statistics){
        SQLiteDatabase sql = openHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",statistics.name);
        values.put("month",statistics.month);
        values.put("day",statistics.day);
        values.put("_all",statistics.all);
        sql.insert("statistics", null, values);
        sql.close();
    }

    /** 修改合同 */
    public void updataStatistics(Statistics statistics){
        SQLiteDatabase sql = openHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        LogUtil.e("SQL里边的"+statistics.toString());
        values.put("month",statistics.month);
        values.put("day",statistics.day);
        values.put("_all",statistics.all);
        sql.update("statistics", values, "name=?", new String[]{statistics.name});
        sql.close();
    }

    /** 遍历合同 */
    public List<Statistics> queryAll(){
        SQLiteDatabase sql = openHelper.getReadableDatabase();
        Cursor cursor = sql.query("statistics", new String[]{"name","month","day","_all"},
                null, null, null, null, null);
        List<Statistics> days = new ArrayList<Statistics>();
        while (cursor.moveToNext()){
            Statistics statistics = new Statistics();
            statistics.name = cursor.getString(0);
            statistics.month = cursor.getInt(1);
            statistics.day = cursor.getInt(2);
            statistics.all = cursor.getInt(3);
            days.add(statistics);
        }
        cursor.close();
        sql.close();
        return days;
    }
}
