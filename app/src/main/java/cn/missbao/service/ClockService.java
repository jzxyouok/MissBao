package cn.missbao.service;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

import cn.missbao.broadcast.DataChangeBroadcast;

/**
 * Role 闹钟的服务
 * Created by 冀泽阳 on 2016/5/10.
 */
public class ClockService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        /**
         * 开启服务后，就开启一个广播，
         * 用来监听系统时间改变
         * */
        DataChangeBroadcast receiver=new DataChangeBroadcast();
        registerReceiver(receiver,new IntentFilter(Intent.ACTION_TIME_TICK));
        super.onCreate();
    }
}
