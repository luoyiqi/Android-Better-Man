package net.liang.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import net.liang.AppConstant;
import net.liang.AppContext;
import net.liang.R;
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
        swipeRefresh.setOnRefreshListener(this);

        //更新数据
        upDataList();
    }


    @Override
    public void onRefresh() {

    }

    void upDataList() {
        XMLRequest xmlRequest = new XMLRequest(Request.Method.GET,
                AppConstant.NEWS_HOST + "&pageIndex=" + pageIndex + "&catalog=" + catalog + "&pageSize=" + pageSize,
                new Response.Listener<XmlPullParser>() {
                    @Override
                    public void onResponse(XmlPullParser response) {
                        Log.e(TAG," DATA = "+ response.toString());
                        try {
                            for (int eventType = response.getEventType(); eventType != XmlPullParser.END_DOCUMENT; eventType = response
                                    .next()) {
                                //switch (eventType) {
                                //  case XmlPullParser.START_TAG:

                                String nodeName = response.getName();
                                //for (int i = 0; i<response.getLineNumber();i++){
                                //    Log.e(TAG, " DATA = " + response.getAttributeName(i));
                                //}
                                //break;
                                // }

                                // Log.e(TAG," DATA = "+ response.toString());
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
        Log.e(TAG, AppConstant.NEWS_HOST + "&pageIndex=" + pageIndex + "&catalog=" + catalog + "&pageSize=" + pageSize);
        AppContext.getRequestQueue().add(xmlRequest);
    }
}
