package net.liang.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.orhanobut.logger.Logger;

import net.liang.AppContext;
import net.liang.AppManager;
import net.liang.R;

import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;
import de.greenrobot.event.EventBus;

/**
 * Created by lenovo on 2016/5/25.
 * AppCompat风格
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {

    private EventBus eventBus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        AppManager.getAppManager().addActivity(this);

        ButterKnife.bind(this);     //注解注册

        //注册EventBus
       // eventBus = EventBus.getDefault();
       // eventBus.register(this);

        /**------------------------- Bmob start -------------------------*/
        //第一：默认初始化
        Bmob.initialize(this, AppContext.BmobAppID);
        //第二：自v3.4.7版本开始,设置BmobConfig,允许设置请求超时时间、文件分片上传时每片的大小、文件的过期时间(单位为秒)，
        BmobConfig config =new BmobConfig.Builder(this)
        .setApplicationId(AppContext.BmobAppID)
        //请求超时时间（单位为秒）：默认15s
        .setConnectTimeout(15)
        //文件分片上传时每片的大小（单位字节），默认512*1024
        .setUploadBlockSize(512*1024)
        //文件的过期时间(单位为秒)：默认1800s
        .setFileExpiration(1800)
        .build();
        Bmob.initialize(config);
        /**------------------------- Bmob  end  -------------------------*/


        /**------------------------- toolbar start -------------------------*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /**------------------------- toolbar  end  -------------------------*/

        initView();
        initData();
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

        //注销
        //eventBus.unregister(this);
    }

    /** Snackbar 提示 */
    public void showSnackbar(View view, String s) {
        Snackbar sb =  Snackbar.make(view, s, Snackbar.LENGTH_SHORT);
        sb.getView().setBackgroundColor(getResources().getColor(R.color.day_colorPrimary));
        sb.show();
    }

    /**隐藏输入法*/
    public void closeInputMethod() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();
        if (isOpen) {
            // imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);//没有显示则显示
            imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
        }
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initTabs();

    //绑定布局
    protected abstract int getLayoutId();
}
