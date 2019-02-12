package com.hung.myviewpager;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by HUNG on 2/5/2017.
 */

public class FaceAdapter extends PagerAdapter {
    private ArrayList<Face> listFace;
    private LayoutInflater inflater;

    public FaceAdapter(Context context, ArrayList<Face> listFace) {
        this.listFace = listFace;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listFace.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.item_face, container, false);
        LinearLayout lnContainer = (LinearLayout) view.findViewById(R.id.ln_container);
        ImageView ivFace = (ImageView) view.findViewById(R.id.iv_icon);
        TextView tvName = (TextView) view.findViewById(R.id.tv_name);

        Face face = listFace.get(position);
        lnContainer.setBackgroundColor(Color.parseColor(face.getColor()));
        ivFace.setImageResource(face.getFaceId());
        tvName.setText(face.getName());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


}
