package com.hungnp3.demofragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FirstFragment extends Fragment {
    private View rootView;
    private OnCallback listener;

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_first, container, false);
        initView();
        return rootView;
    }

    private void initView() {
        rootView.findViewById(R.id.btn_show_frag_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickButton();
            }
        });
    }

    public void setListener(OnCallback listener) {
        this.listener = listener;
    }

    public interface OnCallback {
        void clickButton();
    }
}
