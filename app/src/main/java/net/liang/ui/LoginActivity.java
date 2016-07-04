package net.liang.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import net.liang.R;
import net.liang.base.BaseAppCompatActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseAppCompatActivity implements View.OnClickListener {


    @Bind(R.id.et_login_content) AutoCompleteTextView et_LoginContent;
    @Bind(R.id.et_password) EditText et_Password;
    @Bind(R.id.btn_register) Button btn_Register;
    @Bind(R.id.btn_sign_in) Button btn_SignIn;
    @Bind(R.id.login_view) RelativeLayout login_View;

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
        switch (v.getId()) {
            case R.id.btn_register:
                showSnackbar(login_View, et_LoginContent.getText().toString());


                break;

            case R.id.btn_sign_in:

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

