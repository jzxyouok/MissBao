package cn.missbao.utils;

import android.content.Context;
import android.content.SharedPreferences;

import cn.missbao.golbal.BaseApplication;

/**
 * Role
 * Created by 冀泽阳 on 2016/4/29.
 */
public class SPUtil {

    /** 背景选择器 */
    public static final String BG_COUNT = "bg_count";
    /** 闹钟开关 */
    public static final String SWITCH_CLOCK = "switch_clock";
    /** 闹钟时 */
    public static final String CLOCK_HOUR = "clock_hour";
    /** 闹钟分 */
    public static final String CLOCK_MINUTE = "clock_minute";
    /** 今天的号 */
    public static final String TODAY_NUMBER = "today_number";
    /** 是否存过单词 */
    public static final String FULL_WORD = "full_word";

    private static SharedPreferences mSharedPreferences = BaseApplication.context.
            getSharedPreferences("config.xml", Context.MODE_PRIVATE);


    /** 存一个boolean值 */
    public static void saveBoolean(String key,boolean defVelues){
        mSharedPreferences.edit().putBoolean(key,defVelues).commit();
    }

    /** 取一个boolean值，可以设置默认值 */
    public static boolean getBoolean(String key,boolean defVelues){
        return mSharedPreferences.getBoolean(key,defVelues);
    }

    /** 存一个boolean值，默认是falsh */
    public static boolean getBoolean(String key){
        return mSharedPreferences.getBoolean(key,false);
    }

    /** 存一个字符串类型的数据 */
    public static void saveString(String key,String defVelues){
        mSharedPreferences.edit().putString(key,defVelues).commit();
    }

    /** 取一个字符串类型的数据,默认是空 */
    public static String getString(String key){
        return mSharedPreferences.getString(key,null);
    }

    /** 取一个字符串类型的数据,可以设置默认的返回值 */
    public static String getString(String key,String defVelues){
        return mSharedPreferences.getString(key,defVelues);
    }

    /** 存一个数字 */
    public static void saveInt(String key,int defVelues){
        mSharedPreferences.edit().putInt(key,defVelues).commit();
    }

    public static int getInt(String key, int defVelues){
        return mSharedPreferences.getInt(key,defVelues);
    }


}
