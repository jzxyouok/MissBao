package cn.missbao.utils;

import android.widget.Toast;

import cn.missbao.golbal.BaseApplication;

/**
 * Role
 * Created by 冀泽阳 on 2016/4/29.
 */
public class ToastUtil {

    public static void makeShort(String text){
        Toast.makeText(BaseApplication.context, text,Toast.LENGTH_SHORT).show();
    }

    public static void makeLong(String text){
        Toast.makeText(BaseApplication.context, text,Toast.LENGTH_LONG).show();
    }

}
