package net.liang.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import net.liang.AppContext;
import net.liang.AppManager;
import net.liang.R;
import net.liang.base.BaseAppCompatActivity;
import net.liang.fragment.MainExploreFragment;
import net.liang.fragment.MainMeFragment;
import net.liang.fragment.MainNewsFragment;
import net.liang.fragment.MainTweetFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseAppCompatActivity {

    private ActionBarDrawerToggle toggle;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(android.R.id.tabhost) FragmentTabHost tabhost;
    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.drawer_layout) DrawerLayout drawer;
    @Bind(R.id.main_content) View main_context;


    private long mExitTime = 0;
    private Class mClass[] = {MainNewsFragment.class, MainTweetFragment.class, MainExploreFragment.class, MainMeFragment.class};
    private String mTitles[] = {"新闻", "推荐", "发现", "我"};
    private int mImages[] = {
            R.drawable.tab_icon_new,
            R.drawable.tab_icon_tweet,
            R.drawable.tab_icon_explore,
            R.drawable.tab_icon_me};

    @Override
    protected int getLayoutId() {   return R.layout.activity_main; }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    private View getTabView(int index) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_main_tab, null);

        ImageView image = (ImageView) view.findViewById(R.id.image);
        TextView title = (TextView) view.findViewById(R.id.title);

        image.setImageResource(mImages[index]);
        title.setText(mTitles[index]);

        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        AppManager.getAppManager().finishActivity(LoginActivity.class);
        AppManager.getAppManager().finishActivity(RegisterActivity.class);

        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        toggle.syncState();
        drawer.addDrawerListener(toggle);
    }

    @Override
    public void initTabs() {
        tabhost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        tabhost.getTabWidget().setDividerDrawable(null);

        for (int i = 0; i < mClass.length; i++) {
            TabHost.TabSpec tabSpec = tabhost.newTabSpec(mTitles[i]).setIndicator(getTabView(i));
            tabhost.addTab(tabSpec, mClass[i], null);
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        //如果侧栏没退出，先退出侧栏
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else{
            // 判断两次点击的时间间隔（默认设置为2秒）
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                showSnackbar(main_context,"再按一次退出程序");
                mExitTime = System.currentTimeMillis();
            } else {
                //关闭所有的activity
                AppManager.getAppManager().finishAllActivity();
                super.onBackPressed();
            }
            return super.onKeyDown(keyCode, event);
        }
        return true;
    }


}
