package net.liang.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import com.orhanobut.logger.Logger;

import net.liang.R;

import butterknife.ButterKnife;

/**
 * Created by lenovo on 2016/6/22.
 */
public class BaseFragment extends Fragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);     //注解注册
        Logger.init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public void showSnackbar(View view, String s) {
        Snackbar sb =  Snackbar.make(view, s, Snackbar.LENGTH_SHORT);
        sb.getView().setBackgroundColor(getResources().getColor(R.color.day_colorPrimary));
//        sb.getView().setBackgroundColor(getResources().getColor(R.color.night_colorPrimary));
        sb.show();
    }
}
