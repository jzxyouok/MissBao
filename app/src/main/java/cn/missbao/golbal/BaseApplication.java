package cn.missbao.golbal;

import android.app.Application;
import android.content.Context;

/**
 * Role
 * Created by 冀泽阳 on 2016/4/29.
 */
public class BaseApplication extends Application{

    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

}
