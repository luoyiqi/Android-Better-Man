package net.liang.ui;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.text.TextUtils;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.liang.AppContext;
import net.liang.AppManager;
import net.liang.R;
import net.liang.base.BaseAppCompatActivity;
import net.liang.bean.APPUser;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends BaseAppCompatActivity {

    Intent intent;

    @Bind(R.id.register_view) CoordinatorLayout register_view;
    @Bind(R.id.et_register_username) AutoCompleteTextView et_Username;
    @Bind(R.id.et_register_phoneNumber) AutoCompleteTextView et_PhoneNumber;
    @Bind(R.id.et_register_Password) EditText et_Password;
    @Bind(R.id.et_register_ConfirmPassword) EditText et_ConfirmPassword;

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        et_PhoneNumber.setText(getIntent().getStringExtra("Login_photoNumber"));
    }

    @Override
    protected void initTabs() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @OnClick(R.id.btn_register)
    public void onClick() {

        if(TextUtils.isEmpty(et_Username.getText())){
            et_Username.requestFocus();
            et_Username.setError("请输入用户名！");
        }else if(TextUtils.isEmpty(et_PhoneNumber.getText())){
            et_PhoneNumber.requestFocus();
            et_PhoneNumber.setError("请输入手机号！");
        }else if(TextUtils.isEmpty(et_Password.getText())){
            et_Password.requestFocus();
            et_Password.setError("请输入密码！");
        }else if(!TextUtils.equals(et_Password.getText().toString(),et_ConfirmPassword.getText().toString())){
            et_Password.requestFocus();
            et_Password.setError("密码不一致！");
        }else{
            //判断用户名是否注册
            isRegisterUsername(et_PhoneNumber.getText().toString());
        }
    }

    /** 提交注册*/
    void register(){
        AppContext.getUser().setUsername(et_Username.getText().toString());
        AppContext.getUser().setPhoneNumber(et_PhoneNumber.getText().toString());
        AppContext.getUser().setPassword(et_Password.getText().toString());
        AppContext.getUser().save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null){
                    intent = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void isRegisterUsername(final String PhoneNumber){
        BmobQuery<APPUser> query = new BmobQuery<APPUser>();
        query.addWhereEqualTo("PhoneNumber",PhoneNumber);
        query.findObjects(new FindListener<APPUser>() {
            @Override
            public void done(List<APPUser> list, BmobException e) {
                if ((e==null && list.size() == 0)||(e != null && e.getErrorCode() ==101)) {
                    register();
                }else{
                    et_PhoneNumber.requestFocus();
                    et_PhoneNumber.setError("已注册");
                }
            }
        });
    }
}
