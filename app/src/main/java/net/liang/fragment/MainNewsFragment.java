package net.liang.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.liang.R;
import net.liang.adapter.TabLayoutViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainNewsFragment extends Fragment {


    private TabLayoutViewPagerAdapter adapter;
    private String[] tv_Titles;
    private List<Fragment> tab_fragments;

    private TabLayout tabTitle;
    private ViewPager tabViewpager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_news, container, false);

        tabTitle = (TabLayout) view.findViewById(R.id.tab_title);
        tabViewpager = (ViewPager) view.findViewById(R.id.tab_viewpager);
        /** 初始化tabLayout */
        init_tab();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void init_tab() {
        tab_fragments = new ArrayList<>();
        tab_fragments.add(new NewsFragment());
        tab_fragments.add(new HotNewFragment());
        tab_fragments.add(new BlogFragment());
        tab_fragments.add(new RecommendFragment());

        tv_Titles = getResources().getStringArray(R.array.news_viewpage_arrays);

        /** 解决 fragment 嵌套切换问题*/
        //adapter = new TabLayoutViewPagerAdapter(getActivity().getSupportFragmentManager(),
        adapter = new TabLayoutViewPagerAdapter(getChildFragmentManager(),
                tv_Titles,
                tab_fragments);
        tabViewpager.setAdapter(adapter);
        tabViewpager.setOffscreenPageLimit(4);//设置缓存页
        tabTitle.setupWithViewPager(tabViewpager);
    }


}
