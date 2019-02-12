package com.nac.mvpm.view.activity;

import android.app.Activity;
import android.os.Bundle;

import com.nac.mvpm.presenter.BasePresenter;

/**
 * Created by Admin on 13/05/2017.
 */
public abstract class BaseActivity<T extends BasePresenter> extends Activity{

    protected T mPresenter;

    public static final int NO_LAYOUT = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getLayoutId() == NO_LAYOUT) return;
        setContentView(getLayoutId());
        initPresenter();
        initViews();
    }
    public T getPresenter(){
        return mPresenter;
    }

    protected abstract void initPresenter();

    protected abstract void initViews();

    protected abstract int getLayoutId();
}
