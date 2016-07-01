package net.liang;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import net.liang.bean.News;


/**
 * Created by lenovo on 2016/6/8.
 */
public class AppContext extends Application {

    private static RequestQueue volleyQueue;

    public static String NewsLink     =    "http://news-at.zhihu.com/api/4/news/latest";      //热文链接
    public static String NewsListLink =    "http://news.at.zhihu.com/api/4/news/before/";     //历史热文

    //获取对应新闻的评论数量,『赞』的数量...使用方式：NewInfo+"id"
    public static String NewInfo      =    "http://news-at.zhihu.com/api/4/story-extra/";

    //热文对应长评论查看                 使用方式：NewStory+"/id"+"/long-comments"
    //热文对应短评论查看                 使用方式：NewStory+"/id"+"/short-comments"
    public static String NewStory     =    "http://news-at.zhihu.com/api/4/story";

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
