package cn.missbao.utils;

import android.util.Log;

/**
 * Role
 * Created by 冀泽阳 on 2016/4/29.
 */
public class LogUtil {

    private static boolean is_debug = true;
    private static String TAG = "MissBao =>=>=>=>=>=>=>=> ";

    public static void d(String msg) {
        if (is_debug) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (is_debug) {
            Log.e(TAG, msg);
        }
    }

}
