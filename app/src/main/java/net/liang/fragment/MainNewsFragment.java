package net.liang.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.liang.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainNewsFragment extends Fragment {


    public MainNewsFragment() {
        // Required empty public constructor
        Log.e("lianghuiyong","MainNewsFragment");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e("lianghuiyong","onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_news, container, false);
    }

}
