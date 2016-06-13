package net.liang.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import net.liang.R;

import org.kymjs.kjframe.utils.StringUtils;

/**
 * Created by lenovo on 2016/5/25.
 * AppCompat风格
 */
public class BaseAppCompatActivity extends AppCompatActivity {

    private Toolbar mtoolBar;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        mtoolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolBar);
    }


    public void setToolBarTitle(int resId) {
        setToolBarTitle(getString(resId));
    }

    public void setToolBarTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            title = getString(R.string.app_name);
        }

        if (mtoolBar != null) {
            mtoolBar.setTitle(title);
        }
    }

    public Toolbar getToolBar(){
        return mtoolBar;
    }

}
