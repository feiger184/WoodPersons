package com.jywy.woodpersons.ui.home.railway;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by 高 on 2017/4/1.
 */

public class RailwayGoodsPictureAdapter extends PagerAdapter {

    private ArrayList<ImageView> picList;
    public RailwayGoodsPictureAdapter(ArrayList<ImageView> picList) {
        this.picList = picList;
    }

    @Override
    public int getCount() {
        return picList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = picList.get(position);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemListener();
                }
            }
        });
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public interface OnItemClickListener {
        void onItemListener();
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
