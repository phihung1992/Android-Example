package com.nac.mvpm.presenter;

import android.content.Context;

public abstract class BasePresenter {
    private Context mContext;

    public BasePresenter(Context mContext) {
        this.mContext = mContext;
    }
}