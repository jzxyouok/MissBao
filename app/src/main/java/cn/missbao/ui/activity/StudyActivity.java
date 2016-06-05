package cn.missbao.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.missbao.bean.Word;
import cn.missbao.dao.WordDao;
import cn.missbao.utils.LogUtil;
import cn.missbao.utils.SPUtil;

/**
 * Role
 * Created by 鲍梦梦 on 2016/5/2.
 */
public class StudyActivity extends AppCompatActivity {

    private Context context;

    private RelativeLayout mRelativeLayout;
    private ImageView mLeftIV;
    private ImageView mRightIV;
    private ViewPager mViewPager;

    private List<Word> words = new ArrayList<Word>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        context = this;
        findView();
        initData();
        initView();
        initListening();
    }

    private void findView(){
        mRelativeLayout = (RelativeLayout) this.findViewById(R.id.relativeLayout_main);
        mLeftIV = (ImageView) this.findViewById(R.id.left_iv_study);
        mRightIV = (ImageView) this.findViewById(R.id.right_iv_study);
        mViewPager = (ViewPager) this.findViewById(R.id.viewpager_study);
    }

    private void initView(){
        initBlackGround();
        StudyAdapter adapter = new StudyAdapter();
        mViewPager.setAdapter(adapter);
    }

    private void initListening(){
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) { }
            @Override
            public void onPageScrollStateChanged(int state) {  }
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0){
                    mLeftIV.setVisibility(View.GONE);
                }else {
                    mLeftIV.setVisibility(View.VISIBLE);
                }
                if (position == 9){
                    mRightIV.setVisibility(View.GONE);
                }else {
                    mRightIV.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    /** 设置背景图片 */
    private void initBlackGround(){
        int count = SPUtil.getInt(SPUtil.BG_COUNT,1);
        switch (count){
            case 1:
                mRelativeLayout.setBackgroundResource(R.drawable.bg_01);
                break;
            case 2:
                mRelativeLayout.setBackgroundResource(R.drawable.bg_02);
                break;
            case 3:
                mRelativeLayout.setBackgroundResource(R.drawable.bg_03);
                break;
        }
    }

    private void initData(){
        WordDao dao = new WordDao(context);
        words = dao.queryAll("todayfull");
        LogUtil.e(words.size() + "个单词");
        for (int i=0;i<words.size();i++){
            Word word = new Word();
            word = words.get(i);
            LogUtil.e(word.toString());
        }
    }

    private class StudyAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Word word = new Word();
            word = words.get(position);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view  = inflater.inflate(R.layout.item_study, null);
            TextView wordView = (TextView) view.findViewById(R.id.word_textView_study);
            TextView soundmarkView = (TextView) view.findViewById(R.id.soundmark_textView_study);
            TextView translateView = (TextView) view.findViewById(R.id.translate_textView_study);
            wordView.setText(word.word);
            soundmarkView.setText(word.soundmark);
            translateView.setText(word.translate);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
