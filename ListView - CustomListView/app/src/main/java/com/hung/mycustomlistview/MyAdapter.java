package com.hung.mycustomlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HUNG on 2/5/2017.
 */

public class MyAdapter extends BaseAdapter {
    private ArrayList<Person> listPersons;
    private LayoutInflater inflater;

    public MyAdapter(Context context, ArrayList<Person> listPersons) {
        this.listPersons = listPersons;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listPersons.size();
    }

    @Override
    public Person getItem(int position) {
        return listPersons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            convertView = inflater.inflate(R.layout.item_person, parent, false);
            viewHolder.ivAvatar = (ImageView) convertView.findViewById(R.id.iv_avatar);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Person person = listPersons.get(position);

        viewHolder.ivAvatar.setImageResource(person.getAvatarId());
        viewHolder.tvName.setText(person.getName());

        return convertView;
    }

    private class ViewHolder {
        ImageView ivAvatar;
        TextView tvName;
    }

}
