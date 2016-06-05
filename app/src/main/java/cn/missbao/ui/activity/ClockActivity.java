package cn.missbao.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import cn.missbao.service.ClockService;
import cn.missbao.utils.SPUtil;

/**
 * Role
 * Created by 鲍梦梦 on 2016/5/2.
 */
public class ClockActivity extends AppCompatActivity {

    private static int SWITCH_CLOCL = 0;

    private Context context;
    private ImageButton mSwitchBt;
    private RelativeLayout mBcakView;
    private TimePicker mTimePicker;
    private TextView mHourTx;
    private TextView mMinuteTx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        findView();
        initView();
        initListening();
    }

    private void findView(){
        ClockActivity.this.setTheme(android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
        context = this;
        mSwitchBt = (ImageButton) this.findViewById(R.id.switch_clock);
        mBcakView = (RelativeLayout) this.findViewById(R.id.back_iv_clock);
        mTimePicker = (TimePicker) this.findViewById(R.id.time_picker_clock);
        mHourTx = (TextView) this.findViewById(R.id.hour_clock);
        mMinuteTx = (TextView) this.findViewById(R.id.minute_clock);
    }

    private void initView(){
        SWITCH_CLOCL = SPUtil.getInt(SPUtil.SWITCH_CLOCK,0);
        switch (SWITCH_CLOCL){
            case 0:
                mSwitchBt.setBackgroundResource(R.drawable.off);
                break;
            case 1:
                mSwitchBt.setBackgroundResource(R.drawable.on);
                break;
        }
        mTimePicker.setIs24HourView(true);
        int hour = SPUtil.getInt(SPUtil.CLOCK_HOUR,0);
        int minute = SPUtil.getInt(SPUtil.CLOCK_MINUTE,0);
        if (hour/10 == 0){
            mHourTx.setText("0"+hour+"：");
        }else {
            mHourTx.setText(hour+"：");
        }
        if (minute/10 == 0){
            mMinuteTx.setText("0"+minute);
        }else {
            mMinuteTx.setText(minute+"");
        }
        mTimePicker.setCurrentHour(hour);
        mTimePicker.setCurrentMinute(minute);
    }

    private void initListening(){
        final Intent intent = new Intent(context, ClockService.class);
        mSwitchBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SWITCH_CLOCL == 0){//打开
                    mSwitchBt.setBackgroundResource(R.drawable.on);
                    SWITCH_CLOCL = 1;
                    startService(intent);
                    SPUtil.saveInt(SPUtil.SWITCH_CLOCK,1);
                }else if (SWITCH_CLOCL == 1){//关闭
                    mSwitchBt.setBackgroundResource(R.drawable.off);
                    SWITCH_CLOCL = 0;
                    stopService(intent);
                    SPUtil.saveInt(SPUtil.SWITCH_CLOCK,0);
                }
            }
        });

        mBcakView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                if (hourOfDay/10 == 0){
                    mHourTx.setText("0"+hourOfDay+"：");
                }else {
                    mHourTx.setText(hourOfDay+"：");
                }
                if (minute/10 == 0){
                    mMinuteTx.setText("0"+minute);
                }else {
                    mMinuteTx.setText(minute+"");
                }
                SPUtil.saveInt(SPUtil.CLOCK_HOUR,hourOfDay);
                SPUtil.saveInt(SPUtil.CLOCK_MINUTE,minute);
            }
        });
    }


}
