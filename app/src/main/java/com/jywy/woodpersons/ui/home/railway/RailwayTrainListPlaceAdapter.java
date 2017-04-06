package com.jywy.woodpersons.ui.home.railway;

import android.view.View;
import android.widget.TextView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseListAdapter;
import com.jywy.woodpersons.network.entity.RailwayGoodsListRsp;

import butterknife.BindView;

import static com.jywy.woodpersons.R.id.tv_guige;
import static com.jywy.woodpersons.R.id.tv_length;
import static com.jywy.woodpersons.R.id.tv_phone_num;
import static com.jywy.woodpersons.R.id.tv_tree;

/**
 * Created by 高 on 2017/4/5.
 */

public class RailwayTrainListPlaceAdapter extends BaseListAdapter<RailwayGoodsListRsp.DataBean, RailwayTrainListPlaceAdapter.MyViewHolder> {


    @Override
    protected int getItemViewLayout() {
        return R.layout.item_list_place;
    }

    @Override
    protected MyViewHolder getItemViewHolder(View view) {
        return new MyViewHolder(view);
    }

    class MyViewHolder extends BaseListAdapter.ViewHolder {

        @BindView(R.id.tv_place_num)
        TextView tv_place_num;
        @BindView(R.id.tv_place_length)
        TextView tv_place_length;
        @BindView(R.id.tv_place_tree)
        TextView tv_place_tree;
        @BindView(R.id.tv_place_guige)
        TextView tv_place_guige;
        @BindView(R.id.tv_place_phone_num)
        TextView tv_place_phone_num;

        public MyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(int position) {
            tv_place_num.setText(getItem(position).getCarnum());
            tv_place_length.setText(getItem(position).getLenname());
            tv_place_tree.setText(getItem(position).getStuffname());
            String guige = getItem(position).getGuige();
            if (guige.isEmpty()) {
                tv_place_guige.setText(getItem(position).getKindname());
            } else {
                tv_place_guige.setText(getItem(position).getGuige());
            }

            tv_place_phone_num.setText(getItem(position).getContactphone());

        }
    }
}