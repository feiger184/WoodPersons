package com.jywy.woodpersons.ui.home.unsold;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jywy.woodpersons.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 高 on 2017/3/30.
 */

public class MyGridAdapter extends RecyclerView.Adapter<MyGridAdapter.MainViewHolder> {


    private List<String> mData = new ArrayList<>();

    public void addDatas(List<String> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MainViewHolder mainViewHolder = new MainViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false));
        return mainViewHolder;
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, final int position) {
        holder.textView.setText(mData.get(position));


        // ####################   item点击事件   #################
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MainViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.gridview_text);
        }
    }


    // ###################################   item的点击时间（接口回调） ##############
    public interface OnItemClickListener {

        void onItemClick(View view, int postion);
    }

    private OnItemClickListener onItemClickListener;

    //对外提供一个监听的方法
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
