package net.liang;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/**
 * Created by lenovo on 2016/6/8.
 */
public class AppContext extends Application {

    private static RequestQueue volleyQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    /**
     * 初始化
     * */
    private void init() {
        // 建立Volley的Http请求队列
        volleyQueue = Volley.newRequestQueue(getApplicationContext());
    }

    //获取volley队列
    public static RequestQueue getRequestQueue() {
        return volleyQueue;
    }
}
