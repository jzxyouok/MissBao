package cn.missbao.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

import cn.missbao.bean.Word;
import cn.missbao.bean.WordList;
import cn.missbao.dao.WordDao;
import cn.missbao.utils.LogUtil;
import cn.missbao.utils.SPUtil;


/**
 * Role 欢迎界面
 * Created by 鲍梦梦 on 2016/5/2.
 */

public class SplashActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        findView();
        initWord();
        initTask();
    }

    /** 初始化控件 */
    private void findView(){
        context = this;
    }

    /** 初始化单词
     *  判断是否是第一次运行该程序，
     *      如果是就在创建单词数据库，添加单词到数据库中
     *          如果不是就直接跳过
     * */
    private void initWord() {
        //判断是否是第一次使用程序
        if (SPUtil.getBoolean(SPUtil.FULL_WORD,false)){
            //不是
            return;
        }else {
            //是
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    WordList wordList = new WordList();
                    WordDao dao = new WordDao(context);
                    for(int i=0;i<100;i++){
                        Word word = new Word();
                        word._id = i+"";
                        word.word = wordList.word[i];
                        word.soundmark = wordList.soundmark[i];
                        word.translate = wordList.translate[i];
                        dao.addWord("word",word);
                        int j = i+1;
                        LogUtil.e("正在存第"+j+"个单词");
                        SPUtil.saveBoolean(SPUtil.FULL_WORD,true);
                    }
                }
            });
            thread.start();
        }
    }

    /** 延时跳转
     *  延时3秒跳转到主界面
     * */
    private void initTask(){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(context,LogoActivity.class));
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask,3000);
    }

}
