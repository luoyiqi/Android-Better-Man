package net.liang.ui;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import net.liang.AppConfig;
import net.liang.AppContext;
import net.liang.AppManager;
import net.liang.R;
import net.liang.base.BaseAppCompatActivity;
import net.liang.bean.APPUser;

import org.kymjs.kjframe.utils.PreferenceHelper;

import java.util.List;

import butterknife.Bind;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseAppCompatActivity implements View.OnClickListener {


    @Bind(R.id.et_login_photoNumber)
    AutoCompleteTextView et_PhotoNumber;
    @Bind(R.id.et_password)
    EditText et_Password;
    @Bind(R.id.btn_register)
    Button btn_Register;
    @Bind(R.id.btn_sign_in)
    Button btn_SignIn;
    @Bind(R.id.login_view)
    CoordinatorLayout login_View;

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {

        btn_Register.setOnClickListener(this);
        btn_SignIn.setOnClickListener(this);
    }

    @Override
    protected void initTabs() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {

        closeInputMethod();//关闭输入法
        Intent intent;

        switch (v.getId()) {
            case R.id.btn_register:
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.putExtra("Login_photoNumber", et_PhotoNumber.getText().toString());
                startActivity(intent);
                break;

            case R.id.btn_sign_in:
                if (TextUtils.isEmpty(et_PhotoNumber.getText())) {
                    et_PhotoNumber.requestFocus();
                    et_PhotoNumber.setError("请输入手机号！");
                } else if (TextUtils.isEmpty(et_Password.getText())) {
                    et_Password.requestFocus();
                    et_Password.setError("请输入密码！");
                } else {
                    attemptLogin();
                }

                break;
        }
    }

    //登陆
    private void attemptLogin() {
        //登陆
        BmobQuery<APPUser> query = new BmobQuery<>();
        query.addWhereEqualTo("PhoneNumber", et_PhotoNumber.getText().toString());
        query.findObjects(new FindListener<APPUser>() {
            @Override
            public void done(List<APPUser> list, BmobException e) {

                if ((e == null && list.size() == 0) || (e != null && e.getErrorCode() == 101)) {
                    showSnackbar(login_View, "请先注册!");
                } else {
                    //登陆成功
                    if (list.get(0).getPassword().equals(et_Password.getText().toString())) {

                        PreferenceHelper.write(LoginActivity.this, AppConfig.AppPfFile,AppConfig.IS_LOGIN,true);

                        AppContext.setUser(list.get(0));
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);

                        //登陆失败
                    } else {
                        et_Password.requestFocus();
                        et_Password.setError("密码错误！");
                    }
                }
            }
        });

    }


}

