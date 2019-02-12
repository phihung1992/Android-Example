package com.app2tap.demorecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Project: DemoRecyclerView2.
 * Author: HUNG CODER.
 * Created on: 28/9/2017
 */

public class FaceAdapter extends RecyclerView.Adapter<FaceAdapter.ViewHolder> {
    private List<Face> mListFace;
    private OnCallBack mListener;

    public FaceAdapter(OnCallBack lisntener, List<Face> listFace) {
        this.mListFace = listFace;
        this.mListener = lisntener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_face, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Face face = mListFace.get(position);
        holder.ivAvatar.setImageResource(face.getResId());
        holder.tvName.setText(face.getName());
    }

    @Override
    public int getItemCount() {
        return mListFace.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAvatar;
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ivAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onItemClicked(getPosition());
                }
            });
        }
    }

    public interface OnCallBack {
        void onItemClicked(int position);
    }
}
