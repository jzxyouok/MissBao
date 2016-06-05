package cn.missbao.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Calendar;
import java.util.Random;

import cn.missbao.golbal.BaseApplication;
import cn.missbao.ui.activity.R;
import cn.missbao.ui.activity.SplashActivity;
import cn.missbao.utils.SPUtil;

/**
 * Role 监听系统时间改变的广播
 * Created by 冀泽阳 on 2016/5/10.
 */
public class DataChangeBroadcast extends BroadcastReceiver {



    /**
     * 系统时间的分没改变一次就会执行一次，也就是说每分钟都会执行一次
     * 然后判断这一分钟的时间是否和保存的闹钟的时间一致，如果一致的话 就出现学习的通知
     * */
    @Override
    public void onReceive(Context context, Intent intent) {
        if (isClockTime()){
            showNotification();
        }
    }

    /** 判断当前时间是否是闹钟设定的时间 */
    private boolean isClockTime(){
        boolean is_time;
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        if (hour == SPUtil.getInt(SPUtil.CLOCK_HOUR,0) && minute == SPUtil.getInt(SPUtil.CLOCK_MINUTE,0)){
            is_time = true;
        }else {
            is_time = false;
        }
        return is_time;
    }

    /** 展示Notification
     *  提示学习的通知
     * */
    private void showNotification(){
        NotificationManager manager = (NotificationManager) BaseApplication.context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(BaseApplication.context, SplashActivity.class);//点击跳转位置
        PendingIntent contentIntent = PendingIntent.getActivity(BaseApplication.context, 0, notificationIntent, 0);
        Notification notification = new Notification();
        notification.contentIntent = contentIntent;
        notification.icon  = R.drawable.icon_logo;
        notification.tickerText = getTicker();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        RemoteViews contentView = new RemoteViews(BaseApplication.context.getPackageName(),R.layout.layout_notification);
        notification.contentView = contentView;
        notification.defaults = Notification.DEFAULT_SOUND;
        manager.notify(0, notification);
    }

    /** 随机获取依据名言警句 */
    private String getTicker(){
        String[] tickers = new String[]{"学习如逆水行舟, 不日进, 则日退","黑发不知勤学早,白首方悔读书迟",
                "求学的三个条件是：多观察、多吃苦、多研究。——加菲劳 ","我的努力求学没有得到别的好处，只不过是愈来愈发觉自己的无知。——笛卡儿 ",
                "有教养的头脑的第一个标志就是善于提问。 ——普列汉诺夫"," 当你还不能对自己说今天学到了什么东西时，你就不要去睡觉。 ——利希顿堡 "};
        Random random = new Random();
        int count = random.nextInt(6);
        return tickers[count];
    }

}
