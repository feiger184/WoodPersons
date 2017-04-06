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

    private int selPosition = 10001;

    public void addDatas(List<String> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MainViewHolder mainViewHolder = new MainViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_unsold_tab, parent, false));
        return mainViewHolder;
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(final MainViewHolder holder, final int position) {
        holder.textView.setText(mData.get(position));
        holder.textView_pitch.setText(mData.get(position));
        holder.itemView.setTag(position);

        if (position > 10000) {
            return;
        }
        if (position == selPosition) {
            ((MainViewHolder)holder).textView.setVisibility(View.GONE);
            ((MainViewHolder)holder).textView_pitch.setVisibility(View.VISIBLE);
        } else {
            ((MainViewHolder)holder).textView.setVisibility(View.VISIBLE);
            ((MainViewHolder)holder).textView_pitch.setVisibility(View.GONE);
        }

        // ####################   item点击事件   #################
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.textView.setVisibility(View.GONE);
                    holder.textView_pitch.setVisibility(View.VISIBLE);
                    selPosition = Integer.parseInt(holder.itemView.getTag().toString());
                    MyGridAdapter.this.notifyDataSetChanged();
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        TextView textView_pitch;

        public MainViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_recycler_unsold_text);
            textView_pitch = (TextView) itemView.findViewById(R.id.tv_recycler_unsold_text_pitch);
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
