package com.jywy.woodpersons.ui.home.railway;

import android.view.View;
import android.widget.TextView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseListAdapter;
import com.jywy.woodpersons.network.entity.RailwayGoodsList;
import com.jywy.woodpersons.network.entity.RailwayGoodsListRsp;

import butterknife.BindView;

/**
 * Created by 高 on 2017/3/22.
 */

public class RailwayTrainListAdapter extends BaseListAdapter<RailwayGoodsListRsp.DataBean,RailwayTrainListAdapter.MyViewHolder> {


    @Override
    protected int getItemViewLayout() {
        return R.layout.item_train_list;
    }

    @Override
    protected MyViewHolder getItemViewHolder(View view) {
        return new MyViewHolder(view);
    }

    class MyViewHolder extends BaseListAdapter.ViewHolder {

        @BindView(R.id.tv_train_num)
        TextView tv_train_num;
        @BindView(R.id.tv_length)
        TextView tv_length;
        @BindView(R.id.tv_tree)
        TextView tv_tree;
        @BindView(R.id.tv_guige)
        TextView tv_guige;
        @BindView(R.id.tv_phone_num)
        TextView tv_phone_num;




        public MyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(int position) {
            tv_train_num.setText(getItem(position).getCarnum());
            tv_length.setText(getItem(position).getLenname());
            tv_tree.setText(getItem(position).getStuffname());
            tv_guige.setText(getItem(position).getKindname());
            tv_phone_num.setText(getItem(position).getContactphone());
        }
    }


}
