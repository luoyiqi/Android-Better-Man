package net.liang.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.orhanobut.logger.Logger;

import net.liang.AppManager;
import net.liang.R;

import butterknife.ButterKnife;

/**
 * Created by lenovo on 2016/5/25.
 * AppCompat风格
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {

    private long mExitTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);     //注解注册
        Logger.init();
        AppManager.getAppManager().addActivity(this);

        initData();
        initView();
        initTabs();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);   //注解注销
    }

    public void showSnackbar(View view, String s) {
        Snackbar sb =  Snackbar.make(view, s, Snackbar.LENGTH_SHORT);
        sb.getView().setBackgroundColor(getResources().getColor(R.color.day_colorPrimary));
//        sb.getView().setBackgroundColor(getResources().getColor(R.color.night_colorPrimary));
        sb.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 判断两次点击的时间间隔（默认设置为2秒）
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
               // showSnackbar(null,"再按一次退出程序");
                mExitTime = System.currentTimeMillis();
            } else {
                //关闭所有的activity
                AppManager.getAppManager().finishAllActivity();
                super.onBackPressed();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

/*
    @Override
    public void onBackPressed() {

        // 判断两次点击的时间间隔（默认设置为2秒）
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            // showSnackbar(null,"再按一次退出程序");
            mExitTime = System.currentTimeMillis();
        } else {
            //关闭所有的activity
            AppManager.getAppManager().finishAllActivity();
            super.onBackPressed();
        }
    }
*/

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initTabs();

    //绑定布局
    protected abstract int getLayoutId();
}
