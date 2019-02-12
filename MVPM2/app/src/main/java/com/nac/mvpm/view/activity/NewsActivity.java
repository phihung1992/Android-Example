package com.nac.mvpm.view.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nac.mvpm.MyApplication;
import com.nac.mvpm.R;
import com.nac.mvpm.model.NewsEntity;
import com.nac.mvpm.presenter.NewsPresenter;

import java.util.List;

/**
 * Created by Admin on 13/05/2017.
 */
public class NewsActivity extends BaseActivity<NewsPresenter> implements NewsPresenter.OnCallBack {
    private LinearLayout mLnList;
    private View mLoading;

    @Override
    protected int getLayoutId() {
        return R.layout.news_activity;
    }

    @Override
    protected void initViews() {
        mLnList = (LinearLayout) findViewById(R.id.ln_list);
        mLoading = findViewById(R.id.ln_loading);
        //demo
        mPresenter.startQuery("http://www.24h.com.vn/bong-da-c48.html");
    }

    @Override
    protected void initPresenter() {
        mPresenter = new NewsPresenter(this);
        mPresenter.setOnCallBack(this);
    }

    private static class NewsItem extends RelativeLayout {

        public NewsItem(final Context context, NewsEntity data) {
            super(context);
            inflate(context, R.layout.item_news, this);
            ImageView ivNews = (ImageView) findViewById(R.id.iv_news);
            TextView tvTitle = (TextView) findViewById(R.id.tv_title);
            TextView tvDate = (TextView) findViewById(R.id.tv_date);

            tvTitle.setText(data.getTitle());
            tvDate.setText(data.getDate());

            findViewById(R.id.ln_item_new).setContentDescription(data.getLinkNews());
            findViewById(R.id.ln_item_new).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(v.getContentDescription().toString()));
                    context.startActivity(intent);
                }
            });

            Glide.with(MyApplication.getInstance())
                    .load(data.getLinkImg())
                    .into(ivNews);
        }

        public static View getView(Context context, NewsEntity data) {
            return new NewsItem(context, data);
        }
    }

    @Override
    public void onStartQuery() {
        mLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccess(List<NewsEntity> listData) {
        mLoading.setVisibility(View.GONE);

        for (int i = 0; i < listData.size(); i++) {
            mLnList.addView(NewsItem.getView(this, listData.get(i)));
        }
    }

    @Override
    public void onFailure(Exception error) {
        mLoading.setVisibility(View.GONE);
    }
}
