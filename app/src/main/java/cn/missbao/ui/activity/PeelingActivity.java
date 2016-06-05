package cn.missbao.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import cn.missbao.utils.LogUtil;
import cn.missbao.utils.SPUtil;

/**
 * Role 背景换肤界面
 * Created by 鲍梦梦 on 2016/5/2.
 */
public class PeelingActivity extends AppCompatActivity {

    private RelativeLayout mBcakView;
    private ImageButton mIb01;
    private ImageButton mIb02;
    private ImageButton mIb03;
    private ImageView mIv01;
    private ImageView mIv02;
    private ImageView mIv03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peeling);
        findView();
        initView();
        initListening();
    }
    /** 初始化控件 */
    private void findView(){
        mBcakView = (RelativeLayout) this.findViewById(R.id.back_iv_peeling);
        mIv01 = (ImageView) this.findViewById(R.id.iv_01_peeling);
        mIv02 = (ImageView) this.findViewById(R.id.iv_02_peeling);
        mIv03 = (ImageView) this.findViewById(R.id.iv_03_peeling);
        mIb01 = (ImageButton) this.findViewById(R.id.bg_iv_01_peeling);
        mIb02 = (ImageButton) this.findViewById(R.id.bg_iv_02_peeling);
        mIb03 = (ImageButton) this.findViewById(R.id.bg_iv_03_peeling);
    }

    /** 初始化布局 */
    private void initView(){
        notShowIV();
        /**
         * 判断选中的图片是哪个，并且显示哪个图片是的勾
         * */
        int count = SPUtil.getInt(SPUtil.BG_COUNT,1);
        LogUtil.e(count+"!!!!!!!!!!!!!!!!!!!!!!!!!!");
        switch (count){
            case 1:
                mIv01.setVisibility(View.VISIBLE);
                break;
            case 2:
                mIv02.setVisibility(View.VISIBLE);
                break;
            case 3:
                mIv03.setVisibility(View.VISIBLE);
                break;
        }
    }

    /** 初始化控件监听事件 */
    private void initListening(){

        mBcakView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //每个图片按钮都有保存的功能，吧选在的图片的序号保存在SP中
        mIb01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notShowIV();
                mIv01.setVisibility(View.VISIBLE);
                SPUtil.saveInt(SPUtil.BG_COUNT, 1);
            }
        });

        mIb02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notShowIV();
                mIv02.setVisibility(View.VISIBLE);
                SPUtil.saveInt(SPUtil.BG_COUNT, 2);
            }
        });

        mIb03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notShowIV();
                mIv03.setVisibility(View.VISIBLE);
                SPUtil.saveInt(SPUtil.BG_COUNT, 3);
            }
        });
    }

    /** 显示当前勾选中的图片 */
    private void notShowIV(){
        mIv01.setVisibility(View.GONE);
        mIv02.setVisibility(View.GONE);
        mIv03.setVisibility(View.GONE);
    }

}
