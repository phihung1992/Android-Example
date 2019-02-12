package com.nac.mvpm.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.nac.mvpm.manager.HTMLParser;
import com.nac.mvpm.model.NewsEntity;

import java.util.List;

public class NewsPresenter extends BasePresenter {
    private OnCallBack mListener;

    public NewsPresenter(Context mContext) {
        super(mContext);
    }

    public void setOnCallBack(OnCallBack event) {
        mListener = event;
    }


    public void startQuery(String link){
        new AsyncTask<String,Void,List<NewsEntity>>(){
            @Override
            protected void onPreExecute() {
                mListener.onStartQuery();
            }

            @Override
            protected List<NewsEntity> doInBackground(String[] link) {

                return HTMLParser.getInstance().getNews(link[0]);
            }

            @Override
            protected void onPostExecute(List<NewsEntity> data) {
                if(data == null){
                    mListener.onFailure(new NullPointerException("Null data"));
                }else{
                    mListener.onSuccess(data);
                }
            }
        }.execute(link);
    }

    public interface OnCallBack {
        void onStartQuery();

        void onSuccess(List<NewsEntity> listData);

        void onFailure(Exception error);
    }
}