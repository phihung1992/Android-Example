package com.app2tap.myexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Project: MyExample
 * Author: HUNG CODER
 */

public class PersonAdapter extends BaseAdapter {
    private List<String> listPerson;
    private LayoutInflater inflater;

    public PersonAdapter(Context context, List<String> listPerson) {
        inflater = LayoutInflater.from(context);
        this.listPerson = listPerson;
    }

    @Override
    public int getCount() {
        return listPerson.size();
    }

    @Override
    public Object getItem(int position) {
        return listPerson.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(android.R.layout.simple_list_item_1, viewGroup, false);
            viewHolder.tvName = convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvName.setText(listPerson.get(position));
        return convertView;
    }

    private class ViewHolder {
        TextView tvName;
    }
}
