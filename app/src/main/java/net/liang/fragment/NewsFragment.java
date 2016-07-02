package net.liang.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.orhanobut.logger.Logger;

import net.liang.AppContext;
import net.liang.R;
import net.liang.adapter.NewsAdapter;
import net.liang.base.BaseFragment;
import net.liang.base.BaseRecyclerListener;
import net.liang.bean.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 综合->资讯
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

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

        //一进入到页面刷新数据
        swipeRefresh.post(new Runnable() {
            @Override
            public void run() {
                swipeRefresh.setRefreshing(true);
            }
        });
        onRefresh();
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

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        newsAdapter = new NewsAdapter(getContext());
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //上拉加载
        recyclerView.addOnScrollListener(new BaseRecyclerListener(layoutManager,newsAdapter){
            @Override
            public void onLoadMore() {
                listLoadMore();
            }

            @Override
            public void onSlide() {
                //Log.e(TAG,"onSlide 上滑");

          /*      FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
                CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
                final int fabBottomMargin = lp.bottomMargin;
                fab.animate()
                        .translationY(fab.getHeight() + fabBottomMargin)
                        .setInterpolator(new AccelerateInterpolator(2))
                        .start();

                FragmentTabHost tabHost = (FragmentTabHost) getActivity().findViewById(android.R.id.tabhost);
                CoordinatorLayout.LayoutParams lpTab = (CoordinatorLayout.LayoutParams) tabHost.getLayoutParams();
                final int tabHostBottomMargin = lpTab.bottomMargin;
                tabHost.animate()
                        .translationY(fab.getHeight() + tabHostBottomMargin)
                        .setInterpolator(new AccelerateInterpolator(2))
                        .start();*/
            }

            @Override
            public void onDescent() {
                //Log.e(TAG,"onDescent 下滑");
           /*     FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
                FragmentTabHost tabHost = (FragmentTabHost) getActivity().findViewById(android.R.id.tabhost);
                fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
                tabHost.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();*/
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
        //pageIndex = pageIndex +1;
        //upDataList();
    }

    void upDataList() {
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, AppContext.NewsLink, null,new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject jsonObject) {
                //获取到数据后把加载动画取消显示
                swipeRefresh.setRefreshing(false);
                //--------------------- 数据处理 start ----------------------------

                Logger.json(jsonObject.toString());

/*                try {
                    JSONArray jsonStories = jsonObject.getJSONArray("stories");
                    JSONObject jsonStory;
                    //历遍json数组
                    for(int i=0; i<jsonStories.length(); i++){
                        jsonStory = jsonStories.getJSONObject(i);

                        Log.e("lianghuiyong",jsonStory.toString());

                        News news = new News();
                        news.setID(jsonStory.getString("id"));
                        news.setType(jsonStory.getString("type"));
                        news.setTime(jsonStory.getString("ga_prefix"));
                        news.setImageUrl(jsonStory.getString("images"));
                        news.setTitle(jsonStory.getString("title"));

                        newsAdapter.addItem(news);

                    }

                    //Log.d("lianghuiyong",jsonStories.toString());
                    //Logger.json(jsonStories.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

                //Logger.json(jsonObject.toString());

                //--------------------- 数据处理  end  ----------------------------
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //Logger.e("onError");
            }
        });

        AppContext.getRequestQueue().add(jsonRequest);
    }
}
