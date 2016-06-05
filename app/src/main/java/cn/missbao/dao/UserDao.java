package cn.missbao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.missbao.bean.User;

/**
 * Role
 * Created by 冀泽阳 on 2016/4/13.
 */
public class UserDao {

    private UserOpenHelper helper;

    public UserDao(Context context) {
        helper = new UserOpenHelper(context);
    }

    /** 添加一个合同 */
    public void addUser(User user){
        SQLiteDatabase sql = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("user",user.user);
        values.put("password",user.password);
        values.put("userName",user.userName);
        sql.insert("user",null,values );
        sql.close();
    }


    /** 遍历合同 */
    public List<User> queryAll(){
        SQLiteDatabase sql = helper.getReadableDatabase();
        Cursor cursor = sql.query("user", new String[]{"user", "password","userName"},
                null, null, null, null, null);
        List<User> users = new ArrayList<User>();
        while (cursor.moveToNext()){
            User user = new User();
            user.user = cursor.getString(0);
            user.password = cursor.getString(1);
            user.userName = cursor.getString(2);
            users.add(user);
        }
        cursor.close();
        sql.close();
        return users;
    }

}
