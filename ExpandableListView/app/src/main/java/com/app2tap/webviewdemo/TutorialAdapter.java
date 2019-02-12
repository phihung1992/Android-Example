package com.app2tap.webviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Project: WebViewDemo
 * Author: HUNG CODER
 */

public class TutorialAdapter extends BaseExpandableListAdapter {
    private LayoutInflater mInflater;
    private String[] mListTutorials;
    private String[][] mListData;

    public TutorialAdapter(Context context, String[] listTutorial, String[][] listData) {
        mInflater = LayoutInflater.from(context);
        mListTutorials = listTutorial;
        mListData = listData;
    }

    @Override
    public int getGroupCount() {
        return mListTutorials.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mListData[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mListTutorials[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mListData[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_tut_group, viewGroup, false);
        }

        TextView tvGroup = convertView.findViewById(R.id.tv_group);
        tvGroup.setText(mListTutorials[groupPosition]);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_tut_child, viewGroup, false);
        }
        TextView tvLesson = convertView.findViewById(R.id.tv_lesson);
        tvLesson.setText(mListData[groupPosition][childPosition]);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
