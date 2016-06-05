package cn.missbao.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.missbao.bean.User;
import cn.missbao.dao.UserDao;
import cn.missbao.utils.ToastUtil;

/**
 * Role 注册界面
 * Created by 鲍梦梦 on 2016/5/2.
 */
public class RegisterActivity extends AppCompatActivity {

    private Context context;
    private ImageView mBackImageView;
    private EditText mUserEditText;
    private EditText mPasswordEditText;
    private EditText mNameEditText;
    private ImageButton mRegisterButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findView();
        initListening();
    }

    private void findView(){
        context = this;
        mBackImageView = (ImageView) this.findViewById(R.id.back_ib_register);
        mUserEditText = (EditText) this.findViewById(R.id.user_et_register);
        mPasswordEditText = (EditText) this.findViewById(R.id.password_et_register);
        mNameEditText = (EditText) this.findViewById(R.id.name_et_register);
        mRegisterButton = (ImageButton) this.findViewById(R.id.register_bt_register);

    }

    private void initListening(){

        mBackImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mUserEditText.getText().toString().trim();
                String password = mPasswordEditText.getText().toString().trim();
                String name = mNameEditText.getText().toString().trim();
                if (user.equals("") || password.equals("") || name.equals("")){
                    ToastUtil.makeShort("输入的信息不能为空");
                    return;
                }
                if (isUser(user)){
                    ToastUtil.makeShort("该用户已存在！");
                    mUserEditText.setText("");
                    mPasswordEditText.setText("");
                    return;
                }else {
                    User uu = new User();
                    uu.user = user;
                    uu.password = password;
                    uu.userName = name;
                    UserDao dao = new UserDao(context);
                    dao.addUser(uu);
                    ToastUtil.makeShort("注册成功！");
                    finish();
                }
            }
        });
    }

    /**
     * 判断用户是否已经存在
     * */
    private boolean isUser(String user){
        UserDao dao = new UserDao(context);
        List<User> users = new ArrayList<>();
        users = dao.queryAll();
        boolean b = false;
        for (User u:users){
            if (u.user.equals(user)){
                b = true;
            }
        }
        return b;
    }


}
