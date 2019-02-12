package com.app2tap.myproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Project: MyProject.
 * Author: HUNG CODER.
 * Created on: 1/10/2017
 */

public class SongAdapter extends BaseAdapter {
    private static final String ALBUM_LINK = "https://storage.googleapis.com/automotive-media/";
    private List<Song> mListSong;
    private LayoutInflater mInflater;
    private Context mContext;

    public SongAdapter(Context context, List<Song> listSong) {
        mListSong = listSong;
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public int getCount() {
        return mListSong.size();
    }

    @Override
    public Song getItem(int position) {
        return mListSong.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_song, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ivPhoto = (ImageView) convertView.findViewById(R.id.iv_photo);
            viewHolder.tvSongName = (TextView) convertView.findViewById(R.id.tv_song_name);
            viewHolder.tvArtist = (TextView) convertView.findViewById(R.id.tv_artist);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Song song = mListSong.get(position);

        String link = ALBUM_LINK + song.getImage();
        Glide.with(mContext).load(link).into(viewHolder.ivPhoto);
        viewHolder.tvSongName.setText(song.getTitle());
        viewHolder.tvArtist.setText(song.getArtist());

        return convertView;
    }

    private class ViewHolder {
        ImageView ivPhoto;
        TextView tvSongName;
        TextView tvArtist;
    }
}
