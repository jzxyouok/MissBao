package cn.missbao.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import cn.missbao.adapter.GridTitleAdapter;
import cn.missbao.bean.Day;
import cn.missbao.bean.Statistics;
import cn.missbao.bean.Word;
import cn.missbao.dao.DayDao;
import cn.missbao.dao.StatisticsDao;
import cn.missbao.dao.WordDao;
import cn.missbao.utils.LogUtil;
import cn.missbao.utils.SPUtil;
import cn.missbao.utils.ToastUtil;

/**
 * Role 主界面。即学习情况界面
 * Created by 鲍梦梦 on 2016/5/2.
 */
public class MainActivity extends ActionBarActivity {


    private Context context;

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private LinearLayout mPeelingLayout;
    private LinearLayout mClockingLayout;
    private LinearLayout mLogoffLayout;
    private LinearLayout mExitLayout;
    private GridView mTitleGridView;
    private GridView mDayGridView;
    private TextView mMonthTextView;
    private TextView mCoundWordTextView;
    private TextView mCoundDayTextView;
    private TextView mAllWordTextView;

    private RadioButton mRadioButton;
    private Button mButton02;
    private Button mButton03;

    private ActionBarDrawerToggle mDrawerToggle;


    private List<Day> days = new ArrayList<Day>();
    private Adapter mAdapter;

    private static long startTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        initData();
        initView();
        initListening();
    }

    private void findView(){
        context = this;
        mToolbar = (Toolbar) this.findViewById(R.id.too_bar_main);
        mDrawerLayout = (DrawerLayout) this.findViewById(R.id.drawerLayout_main);
        mPeelingLayout = (LinearLayout) this.findViewById(R.id.peeling_layout_menu);
        mClockingLayout = (LinearLayout) this.findViewById(R.id.clocking_layout_menu);
        mRadioButton = (RadioButton) this.findViewById(R.id.radio_01_main);
        mButton02 = (Button) this.findViewById(R.id.radio_02_main);
        mButton03 = (Button) this.findViewById(R.id.radio_03_main);
        mTitleGridView = (GridView) this.findViewById(R.id.gridView_title);
        mDayGridView = (GridView) this.findViewById(R.id.gridView_day);
        mLogoffLayout = (LinearLayout) this.findViewById(R.id.logoff_menu);
        mExitLayout = (LinearLayout) this.findViewById(R.id.exit_menu);
        mMonthTextView = (TextView) this.findViewById(R.id.month_text_main);
        mCoundWordTextView = (TextView) this.findViewById(R.id.count_word_main);
        mCoundDayTextView = (TextView) this.findViewById(R.id.count_day_main);
        mAllWordTextView = (TextView) this.findViewById(R.id.all_word_main);
    }

    /** 初始化学习签到情况数据 */
    private void initData(){
        DayDao dayDao = new DayDao(context);
        days = dayDao.queryAll();
        LogUtil.e(days.toString());
    }

    private void initView(){
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(Color.parseColor("#FAFAFA"));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, 0, 0);
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mRadioButton.setChecked(true);
        mTitleGridView.setAdapter(new GridTitleAdapter());
        mAdapter = new Adapter();
        mDayGridView.setAdapter(mAdapter);

        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH)+1;
        mMonthTextView.setText(month + "月学习情况签到表：");
        initStatisics();
    }

    private void initListening(){

        mPeelingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, PeelingActivity.class));
            }
        });

        mClockingLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, ClockActivity.class));
            }
        });

        mLogoffLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, LogoActivity.class));
                finish();
            }
        });

        mExitLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mButton02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * 判断是否是今天第一次学习 单词
                 * */
                if (isNewday()) {
                    upDay();//跟新签到数据
                    upWord();//跟新单词
                    updataStatisics();
                }
                startActivity(new Intent(context, StudyActivity.class));
            }
        });

        mButton03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, HistoryActivity.class));
            }
        });

    }

    /** 判断是不是今天第一次学习 */
    private boolean isNewday(){
        int number = SPUtil.getInt(SPUtil.TODAY_NUMBER, 0);
        Calendar calendar = Calendar.getInstance();
        int newNumber = calendar.get(Calendar.DAY_OF_MONTH);
        boolean b;
        if (newNumber == number){
            b = false;
        }else {
            b = true;
            SPUtil.saveInt(SPUtil.TODAY_NUMBER,newNumber);
        }
        return b;
    }

    /** 跟新数据统计 */
    private void updataStatisics(){

        StatisticsDao dao = new StatisticsDao(context);
        List<Statistics> statisticses = new ArrayList<Statistics>();
        statisticses = dao.queryAll();

        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH)+1;
        Statistics statistics;
        statistics  = statisticses.get(0);
        if ( month != statistics.month ){
            statistics.month = month;
            statistics.day = 0;
        }
        int day = statistics.day;
        int all = statistics.all;
        statistics.day = day+1;
        statistics.all = all+1;
        LogUtil.e("updataStatisics()===========" + statistics.toString());
        StatisticsDao d = new StatisticsDao(context);
        d.updataStatistics(statistics);

        initStatisics();
    }

    /** 显示数据统计 */
    private void initStatisics(){
        StatisticsDao dao = new StatisticsDao(context);
        List<Statistics> statisticses = new ArrayList<Statistics>();
        statisticses = dao.queryAll();
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH)+1;

        Statistics statistics;
        if (statisticses.size() == 0){
            statistics = new Statistics();
            statistics.name = "aaa";
            statistics.month = month;
            statistics.day = 0;
            statistics.all = 0;
            dao.addStatistics(statistics);
        }else {
            statistics  = statisticses.get(0);
        }
        if ( month != statistics.month ){
            statistics.month = month;
            statistics.day = 0;
        }
        LogUtil.e("initStatisics()===========" + statistics.toString());
        mCoundWordTextView.setText("本月一个学习" + statistics.day * 10 + "单词");
        mCoundDayTextView.setText("本月学习了" + statistics.day + "天");
        mAllWordTextView.setText("总共学习了" + statistics.all * 10 + "个单词");
    }

    /** 跟新天 */
    private void upDay(){
        DayDao dayDao = new DayDao(context);
        Day day = new Day();
        Calendar calendar = Calendar.getInstance();
        day.month = calendar.get(Calendar.MONTH) + 1;
        day.number = calendar.get(Calendar.DAY_OF_MONTH);
        dayDao.addDay(day);
        days.add(day);
        mAdapter.getDays();
        mAdapter.notifyDataSetChanged();
    }

    /** 跟新单词
     * 记忆周期里的7天 一共有七个数据表
     * 如果要跟新第一天的话，
     * 是先把第7天的删除，第六天的存到第七天中
     *      吧第六天的再删除，第五天的存到第六天中
     *          吧第五天的删除，第四天的存到第五天中
     *              第四天的删除，第三天的存到第四天中
     *                  第三天的删除，第二天的存到第三天中
     *                      第二天的删除，第一天的存到第二天中
     *                          第一天的删除，重新添加新的单词
     * */
    private void upWord(){

        final WordDao dao = new WordDao(context);

        final List<Word> list = new ArrayList<>();
        for (int i=0;i<10;i++){
            Random random = new Random();
            int k = random.nextInt(100);
            Word word = dao.queryWord(k + "");
            list.add(word);
        }
        dao.deleteAll(context,"todayfull");
        dao.addWordList("todayfull",list);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Word> words = new ArrayList<Word>();

                words = dao.queryAll("five_day");
                dao.deleteAll(context,"six_day");
                dao.addWordList("six_day", words);
                words.clear();

                words = dao.queryAll("four_day");
                dao.deleteAll(context,"five_day");
                dao.addWordList("five_day", words);
                words.clear();

                words = dao.queryAll("three_day");
                dao.deleteAll(context,"four_day");
                dao.addWordList("four_day", words);
                words.clear();

                words = dao.queryAll("tow_day");
                dao.deleteAll(context,"three_day");
                dao.addWordList("three_day", words);
                words.clear();

                words = dao.queryAll("one_day");
                dao.deleteAll(context,"tow_day");
                dao.addWordList("tow_day", words);
                words.clear();

                words = dao.queryAll("today");
                dao.deleteAll(context,"one_day");
                dao.addWordList("one_day", words);
                words.clear();

                dao.deleteAll(context, "today");
                dao.addWordList("today",list);
            }
        });
        thread.start();


    }

    public void onBackPressed(){
        if (startTime == 0){
            ToastUtil.makeShort("再按一次退出");
            startTime = System.currentTimeMillis();
        }else {
            if ((System.currentTimeMillis() - startTime)>3000){
                ToastUtil.makeShort("再按一次退出");
                startTime = 0;
            }else {
                finish();
            }
        }
    }

    private class Adapter extends BaseAdapter{

        private int size;
        private int month;
        private int notDay;
        private int today;

        private List<Day> dd = new ArrayList<>();

        public Adapter() {
            Calendar calendar = Calendar.getInstance();
            today = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH)+1;
            switch (month){
                case 5:
                    size = 31;
                    notDay = 0;
                    break;
                case 6:
                    size = 33;
                    notDay = 3;
                    break;
                case 7:
                    size = 36;
                    notDay = 5;
                    break;
            }
           getDays();
        }

        @Override
        public int getCount() {
            return size;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view  = View.inflate(context, R.layout.sub_day_grid, null);
            if (position < notDay){
                return view;
            }
            TextView textView = (TextView) view.findViewById(R.id.day_text_item);
            textView.setText(position-notDay+1+"");
            if (position == today+notDay-1){
                ImageView imageView1 = (ImageView) view.findViewById(R.id.new_iv_item);
                imageView1.setVisibility(View.VISIBLE);
            }
            for (Day d:dd){
                if ((d.number+notDay) == (position+1)){
                    ImageView imageView2 = (ImageView) view.findViewById(R.id.gou_iv_item);
                    imageView2.setVisibility(View.VISIBLE);
                }
            }
            return view;
        }


        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        private void getDays(){
            for (Day d:days){
                if (d.month == month){
                    dd.add(d);
                }
            }
        }
    }

}
