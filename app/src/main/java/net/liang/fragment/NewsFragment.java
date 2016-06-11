package net.liang.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import net.liang.AppConstant;
import net.liang.AppContext;
import net.liang.R;
import net.liang.adapter.NewsAdapter;
import net.liang.base.BaseRecyclerLaodMoreListener;
import net.liang.bean.News;
import net.liang.ui.MainActivity;
import net.liang.utils.XMLRequest;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * 综合->资讯
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private String TAG = "NewsFragment";
    private int pageIndex = 0;  //页数（从0开始）
    private int pageSize = 20;  //每页条数
    private int catalog = 1;    //类别

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        swipeRefresh = (SwipeRefreshLayout) view.findViewById(R.id.swiprefresh);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);

        /** 下拉刷新颜色 */
        swipeRefresh.setColorSchemeResources(
                R.color.swiperefresh_color1, R.color.swiperefresh_color2,
                R.color.swiperefresh_color3, R.color.swiperefresh_color4);
        //下拉刷新
        swipeRefresh.setOnRefreshListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        newsAdapter = new NewsAdapter(getContext());
        recyclerView.setAdapter(newsAdapter);

        //上拉加载
        recyclerView.setOnScrollListener(new BaseRecyclerLaodMoreListener(
                    new LinearLayoutManager(getActivity())) {
            @Override
            public void onLoadMore(int currentPage) {
                listLoadMore();
            }
        });
    }


    @Override
    public void onRefresh() {
        //更新数据
        pageIndex = 0;
        newsAdapter.clearItems();
        upDataList();

    }

    public void listLoadMore(){
        //更新数据
        pageIndex++;
        upDataList();
    }

    void upDataList() {
        XMLRequest xmlRequest = new XMLRequest(Request.Method.GET, AppConstant.NEWS_HOST +
                        "&pageIndex=" + pageIndex +
                        "&catalog=" + catalog +
                        "&pageSize=" + pageSize,
                new Response.Listener<XmlPullParser>() {
                    @Override
                    public void onResponse(XmlPullParser response) {

                        //获取到数据后把加载动画取消显示
                        swipeRefresh.setRefreshing(false);

                        //解析 XmlPullParser
                        try {
                            int type = response.getEventType();//此时返回0，也就是在START_DOCUMENT
                            boolean isGetXmlData = false;
                            int i = 0;
                            News news = null;

                            while (type!= XmlPullParser.END_DOCUMENT){


                                //资讯实体类
                                switch (type){
                                    case XmlPullParser.START_DOCUMENT:
                                        break;

                                    case XmlPullParser.START_TAG:
                                        switch (response.getName()){
                                            case "id":
                                                //初始化资讯实体类
                                                news = new News();
                                                news.setID(response.nextText());
                                                break;
                                            case "title":
                                                if (news != null) {
                                                    news.setTitle(response.nextText());
                                                }
                                                break;
                                            case "body":
                                                if (news != null) {
                                                    news.setBody(response.nextText());
                                                }
                                                break;
                                            case "commentCount":
                                                if (news != null) {
                                                    news.setCommentCount(response.nextText());
                                                }
                                                break;
                                            case "author":
                                                if (news != null) {
                                                    news.setAuthor(response.nextText());
                                                }
                                                break;
                                            case "pubDate":
                                                if (news != null) {
                                                    news.setTime(response.nextText());
                                                }
                                                break;
                                            case "url":
                                                if (news != null) {
                                                    news.setUrl(response.nextText());
                                                }
                                                break;
                                            case "type":
                                                if (news != null) {
                                                    news.setType(response.nextText());
                                                }
                                                break;
                                            case "authoruid2":
                                                if (news != null) {
                                                    news.setAuthoruid2(response.nextText());
                                                }

                                                //将实体类载入到适配器
                                                newsAdapter.addItem(news);
                                                break;
                                        }
                                        break;
                                    case XmlPullParser.END_TAG:
                                        break;
                                }
                                type = response.next();
                            }

                        } catch (XmlPullParserException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        Log.e(TAG,AppConstant.NEWS_HOST +
                "&pageIndex=" + pageIndex +
                "&catalog=" + catalog +
                "&pageSize=" + pageSize);
        AppContext.getRequestQueue().add(xmlRequest);
    }
}
