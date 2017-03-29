package com.jywy.woodpersons.ui.home;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jywy.woodpersons.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ren on 2017/3/27.
 */

public class UnsoldAdapter extends RecyclerView.Adapter{
    private Context mContext;
    private List<UnsoldInfo> mData=new ArrayList<UnsoldInfo>();
    //定义数据源

    public UnsoldAdapter(Context context, List<UnsoldInfo> data) {
        mContext = context;
        mData = data;
    }
    @Override//将ItemView渲染进来，创建ViewHolder
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override//将数据源的数据绑定到相应控件上
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder holder2 = (MyViewHolder) holder;
        UnsoldInfo unsoldInfo = mData.get(position);
        Uri uri = Uri.parse(unsoldInfo.drawableUrl);
        holder2.unsold_img.setImageURI(uri);//设置图片
        holder2.unsold_name.setText(unsoldInfo.productMes);//设置产品信息
        if (unsoldInfo.productType.equals("")) {
            holder2.unsold_type.setVisibility(View.INVISIBLE);
        }
        holder2.unsold_type.setText(unsoldInfo.productType);//设置产品类型
        holder2.unsold_site.setText(unsoldInfo.productSite);//设置产品地点
        holder2.unsold_phone.setText(unsoldInfo.productPhone);//设置电话信息
        holder2.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClicked(mData.get(position));

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    //定义自己的ViewHolder，将View的控件引用在成员变量上
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public SimpleDraweeView unsold_img;
        public TextView unsold_name;
        public TextView unsold_type;
        public TextView unsold_site;
        public TextView unsold_phone;

        public MyViewHolder(View itemView) {
            super(itemView);
            unsold_img = (SimpleDraweeView) itemView.findViewById(R.id.unsold_img);
            unsold_name = (TextView) itemView.findViewById(R.id.unsold_name);
            unsold_type = (TextView) itemView.findViewById(R.id.unsold_type);
            unsold_site = (TextView) itemView.findViewById(R.id.unsold_site);
            unsold_phone = (TextView) itemView.findViewById(R.id.unsold_phone);

        }
    }


    //item点击事件 回调接口
    public interface onItemClickListener {
        //点击商品item，把商品信息传进来
        void onItemClicked(UnsoldInfo unsoldInfo);
    }

    private onItemClickListener listener;

    public void setListener(onItemClickListener onItemClickListener) {
        listener = onItemClickListener;
    }
}
