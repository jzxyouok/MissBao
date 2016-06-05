package cn.missbao.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import cn.missbao.bean.User;
import cn.missbao.dao.UserDao;
import cn.missbao.golbal.BaseApplication;
import cn.missbao.utils.LogUtil;
import cn.missbao.utils.ToastUtil;

/**
 * Role 登录界面
 * Created by 鲍梦梦 on 2016/5/2.
 */
public class LogoActivity extends AppCompatActivity {

    private Context context;

    private EditText mUserEditText;
    private EditText mPasswordEditText;
    private Button mLogoButton;
    private Button mRegisterButton;

    /** 记录两次按返回键之间的间隔时间 */
    private static long startTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        findView();
        initListening();
    }
    /** 初始化控件 */
    private void findView(){
        context = this;
        mUserEditText = (EditText) this.findViewById(R.id.user_et_logo);
        mPasswordEditText = (EditText) this.findViewById(R.id.password_et_logo);
        mLogoButton = (Button) this.findViewById(R.id.logo_bt_logo);
        mRegisterButton = (Button) this.findViewById(R.id.register_bt_logo);

    }

    /** 初始化控件的监听事件 */
    private void initListening(){
        mLogoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mUserEditText.getText().toString().trim();
                String password = mPasswordEditText.getText().toString().trim();
                if (user.equals("") || password.equals("")){
                    ToastUtil.makeShort("输入的信息不能为空！");
                    return;
                }
                if (isUser(user,password)) {
                    startActivity(new Intent(context, MainActivity.class));
                    finish();
                } else {
                    ToastUtil.makeShort("账号或密码错误，请重新输入！");
                    mUserEditText.setText("");
                    mPasswordEditText.setText("");
                }
            }
        });

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, RegisterActivity.class));
            }
        });

    }

    /** 判断用户输入的账户是否存在
     *  如果存在则返回true
     *      如果不存在则返回flash
     * */
    private Boolean isUser(String user,String password){
        UserDao dao = new UserDao(BaseApplication.context);
        List<User> users = new ArrayList<>();
        users = dao.queryAll();
        LogUtil.e(users.size() + "!!!!!!!!!!!!!!!!!!!");
        Boolean b = false;
        for (int i=0;i<users.size();i++){
            LogUtil.e(users.get(i).user+"========"+users.get(i).password);
            if (users.get(i).user.equals(user)){
                if (users.get(i).password.equals(password)){
                    b = true;
                }
            }

        }
        return b;
    }

    /** 重写方法，监听返回键，防止按了返回直接退出 */
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
}
