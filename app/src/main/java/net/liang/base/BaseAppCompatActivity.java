package net.liang.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;

import com.orhanobut.logger.Logger;

import net.liang.AppContext;
import net.liang.AppManager;
import net.liang.R;

import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

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

        //第一：默认初始化
        Bmob.initialize(this, AppContext.BmobAppID);

        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        BmobConfig config =new BmobConfig.Builder(this)
        ////设置appkey
        .setApplicationId(AppContext.BmobAppID)
        //请求超时时间（单位为秒）：默认15s
        .setConnectTimeout(15)
        //文件分片上传时每片的大小（单位字节），默认512*1024
        .setUploadBlockSize(512*1024)
        //文件的过期时间(单位为秒)：默认1800s
        .setFileExpiration(1800)
        .build();
        Bmob.initialize(config);

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
