package com.app2tap.customgridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Project: CustomGridView
 * Author: HUNG CODER
 * Created on: 16/09/2017.
 */

public class MyAdapter extends BaseAdapter {
    private List<Face> listFace;
    private LayoutInflater inflater;

    public MyAdapter(Context context, List<Face> listFace) {
        inflater = LayoutInflater.from(context);
        this.listFace = listFace;
    }

    @Override
    public int getCount() {
        return listFace.size();
    }

    @Override
    public Face getItem(int position) {
        return listFace.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_face, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.ivAvatar = view.findViewById(R.id.iv_face);
            viewHolder.tvName = view.findViewById(R.id.tv_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Face face = listFace.get(position);
        viewHolder.tvName.setText(face.getName());
        viewHolder.ivAvatar.setImageResource(face.getResId());
        return view;
    }

    private class ViewHolder {
        ImageView ivAvatar;
        TextView tvName;
    }
}
