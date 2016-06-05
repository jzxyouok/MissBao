package cn.missbao.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cn.missbao.golbal.BaseApplication;
import cn.missbao.ui.activity.R;

/**
 * Role
 * Created by 冀泽阳 on 2016/5/1.
 */
public class GridTitleAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view  = View.inflate(BaseApplication.context, R.layout.sub_title_grid,null);
        TextView textView = (TextView) view.findViewById(R.id.title_text_item);
        textView.setBackgroundResource(R.drawable.bg_week);
        String string = null;
        switch (position){
            case 0:
                string = "星期日";
                textView.setBackgroundResource(R.drawable.bg_zhoumo);
                break;
            case 1:
                string = "星期一";
                break;
            case 2:
                string = "星期二";
                break;
            case 3:
                string = "星期三";
                break;
            case 4:
                string = "星期四";
                break;
            case 5:
                string = "星期五";
                break;
            case 6:
                string = "星期六";
                textView.setBackgroundResource(R.drawable.bg_zhoumo);
                break;
        }
        textView.setText(string);
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
}
