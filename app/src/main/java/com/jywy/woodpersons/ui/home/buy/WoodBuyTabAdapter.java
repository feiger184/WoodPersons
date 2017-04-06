package com.jywy.woodpersons.ui.home.buy;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.ui.home.unsold.MyGridAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 高 on 2017/3/31.
 */

public class WoodBuyTabAdapter extends RecyclerView.Adapter<WoodBuyTabAdapter.MainViewHolder> {


    private List<String> mData = new ArrayList<>();
    private int selPosition = 10001;


    public void addDatas(List<String> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    @Override
    public WoodBuyTabAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        WoodBuyTabAdapter.MainViewHolder mainViewHolder = new WoodBuyTabAdapter.MainViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_buy_tab, parent, false));
        return mainViewHolder;
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final WoodBuyTabAdapter.MainViewHolder holder, final int position) {
        holder.textView.setText(mData.get(position));
        holder.textView_pitch.setText(mData.get(position));
        holder.itemView.setTag(position);


        if (position > 10000) {
            return;
        }
        if (position == selPosition) {
            ((WoodBuyTabAdapter.MainViewHolder)holder).textView.setVisibility(View.GONE);
            ((WoodBuyTabAdapter.MainViewHolder)holder).textView_pitch.setVisibility(View.VISIBLE);
        } else {
            ((WoodBuyTabAdapter.MainViewHolder)holder).textView.setVisibility(View.VISIBLE);
            ((WoodBuyTabAdapter.MainViewHolder)holder).textView_pitch.setVisibility(View.GONE);
        }

        // ####################   item点击事件   #################
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.textView.setVisibility(View.GONE);
                    holder.textView_pitch.setVisibility(View.VISIBLE);
                    selPosition = Integer.parseInt(holder.itemView.getTag().toString());
                    WoodBuyTabAdapter.this.notifyDataSetChanged();
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
        TextView textView_pitch;

        public MainViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_recycler_buy_text);
            textView_pitch = (TextView) itemView.findViewById(R.id.tv_recycler_buy_text_pitch);
        }
    }


    // ###################################   item的点击时间（接口回调） ##############
    public interface OnItemClickListener {

        void onItemClick(View view, int postion);
    }

    private WoodBuyTabAdapter.OnItemClickListener onItemClickListener;

    //对外提供一个监听的方法
    public void setOnItemClickListener(WoodBuyTabAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
