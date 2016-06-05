package cn.missbao.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.missbao.bean.Word;
import cn.missbao.dao.WordDao;
import cn.missbao.holder.ChildView;
import cn.missbao.holder.ParentView;
import cn.missbao.utils.LogUtil;

/**
 * Role
 * Created by 鲍梦梦 on 2016/5/2.
 */
public class HistoryActivity extends AppCompatActivity {

    private Context context;
    private ImageButton mBackButton;
    private ExpandableListView mListView;


    private List<String> parentTitle = new ArrayList<String>();
    private Map<String , List<Word>> map = new HashMap<String , List<Word>>();
    private List<Word> toDayWord = new ArrayList<Word>();
    private List<Word> oneDayWord = new ArrayList<Word>();
    private List<Word> twoDayWord = new ArrayList<Word>();
    private List<Word> threeDayWord = new ArrayList<Word>();
    private List<Word> fourDayWord = new ArrayList<Word>();
    private List<Word> fiveDayWord = new ArrayList<Word>();
    private List<Word> sixDayWord = new ArrayList<Word>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        findView();
        initData();
        initView();
        initListening();
    }

    private void findView(){
        context = this;
        mBackButton = (ImageButton) this.findViewById(R.id.back_iv_history);
        mListView = (ExpandableListView) this.findViewById(R.id.expandableListView_history);
    }

    private void initData() {
        WordDao dao = new WordDao(context);
        toDayWord = dao.queryAll("today");
        oneDayWord = dao.queryAll("one_day");
        twoDayWord = dao.queryAll("tow_day");
        threeDayWord = dao.queryAll("three_day");
        fourDayWord = dao.queryAll("four_day");
        fiveDayWord = dao.queryAll("five_day");
        sixDayWord = dao.queryAll("six_day");
        LogUtil.e("当天"+toDayWord.size()+"个单词");
        LogUtil.e("前一天"+oneDayWord.size()+"个单词");
        LogUtil.e("前二天"+twoDayWord.size()+"个单词");
        LogUtil.e("前三天"+threeDayWord.size()+"个单词");
        LogUtil.e("前四天"+fourDayWord.size()+"个单词");
        LogUtil.e("前五天"+fiveDayWord.size()+"个单词");
        LogUtil.e("前六天"+sixDayWord.size()+"个单词");
    }

    private void initView(){
        initListData();
        mListView.setAdapter(new Adapter());
    }

    /** 初始化ListView的标题数据 */
    private void initListData(){
        parentTitle.add("记忆周期第一天");
        parentTitle.add("记忆周期第二天");
        parentTitle.add("记忆周期第三天");
        parentTitle.add("记忆周期第四天");
        parentTitle.add("记忆周期第五天");
        parentTitle.add("记忆周期第六天");
        parentTitle.add("记忆周期第七天");
        map.put("记忆周期第一天",toDayWord);
        map.put("记忆周期第二天",oneDayWord);
        map.put("记忆周期第三天",twoDayWord);
        map.put("记忆周期第四天",threeDayWord);
        map.put("记忆周期第五天",fourDayWord);
        map.put("记忆周期第六天",fiveDayWord);
        map.put("记忆周期第七天",sixDayWord);
    }

    private void initListening(){
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class Adapter extends BaseExpandableListAdapter{

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            String key = parentTitle.get(groupPosition);
            return (map.get(key)).get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildView holder;
            String key = parentTitle.get(groupPosition);
            Word word = map.get(key).get(childPosition);
            if (convertView == null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.sub_child_word,null);
                holder = new ChildView();
                holder.wordView = (TextView) convertView.findViewById(R.id.word_textView_sub);
                holder.soundmarkView = (TextView) convertView.findViewById(R.id.soundmark_textView_sub);
                holder.translateView = (TextView) convertView.findViewById(R.id.translate_textView_sub);
                convertView.setTag(holder);
            }else {
                holder = (ChildView) convertView.getTag();
            }
            LogUtil.e(word.toString()+"!!!!!!!!!!!!!!!!!!!");
            holder.wordView.setText(word.word);
            holder.soundmarkView.setText(word.soundmark);
            holder.translateView.setText(word.translate);
            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            String key = parentTitle.get(groupPosition);
            int size = map.get(key).size();
            return size;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return parentTitle.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return parentTitle.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            ParentView holder;
            if (convertView == null){
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.sub_parent_word,null);
                holder = new ParentView();
                holder.paentView = (TextView) convertView.findViewById(R.id.parent_textView_sub);
                convertView.setTag(holder);
            }else {
                holder = (ParentView) convertView.getTag();
            }
            holder.paentView.setText(parentTitle.get(groupPosition));
            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }

}

