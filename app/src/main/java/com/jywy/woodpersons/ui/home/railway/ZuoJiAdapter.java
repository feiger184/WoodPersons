package com.jywy.woodpersons.ui.home.railway;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jywy.woodpersons.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 高 on 2017/3/24.
 */
public class ZuoJiAdapter extends RecyclerView.Adapter<ZuoJiAdapter.ViewHolder> {


    private LayoutInflater mInflater;
    private List<String> mDatas = new ArrayList<>();


    public ZuoJiAdapter(Context context, List<String> datats) {
        mInflater = LayoutInflater.from(context);
        mDatas = datats;
    }


    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.layout_zuoji_item,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.mTxt = (TextView) view
                .findViewById(R.id.item_zuoji);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.mTxt.setText(mDatas.get(position) + "\t");
        //如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickLitener.onItemClick(mDatas.get(position));
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTxt;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * ItemClick的回调接口
     */
    public interface OnItemClickLitener {
        void onItemClick(String phone);
    }

}
