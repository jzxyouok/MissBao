package cn.missbao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.missbao.bean.Day;

/**
 * Role
 * Created by 冀泽阳 on 2016/5/1.
 */
public class DayDao {

    private DayOpenHelper openHelper;

    public DayDao(Context context) {
        openHelper = new DayOpenHelper(context);
    }

    /** 添加一个天 */
    public void addDay(Day day){
        SQLiteDatabase sql = openHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("month",day.month);
        values.put("number",day.number);
        sql.insert("day", null, values);
        sql.close();
    }

    /** 遍历合同 */
    public List<Day> queryAll(){
        SQLiteDatabase sql = openHelper.getReadableDatabase();
        Cursor cursor = sql.query("day", new String[]{"month","number"},
                null, null, null, null, null);
        List<Day> days = new ArrayList<Day>();
        while (cursor.moveToNext()){
            Day day = new Day();
            day.month = cursor.getInt(0);
            day.number = cursor.getInt(1);
            days.add(day);
        }
        cursor.close();
        sql.close();
        return days;
    }
}
