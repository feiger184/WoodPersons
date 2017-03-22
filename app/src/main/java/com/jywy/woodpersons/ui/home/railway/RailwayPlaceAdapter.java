package com.jywy.woodpersons.ui.home.railway;

import android.view.View;
import android.widget.TextView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseListAdapter;
import com.jywy.woodpersons.network.entity.RailwayGoods;

import butterknife.BindView;

/**
 * Created by 高 on 2017/3/22.
 */

public class RailwayPlaceAdapter extends BaseListAdapter<RailwayGoods, RailwayPlaceAdapter.MyViewHolder> {


    @Override
    protected int getItemViewLayout() {
        return R.layout.place_show_item;
    }

    @Override
    protected RailwayPlaceAdapter.MyViewHolder getItemViewHolder(View view) {
        return new RailwayPlaceAdapter.MyViewHolder(view);
    }

    class MyViewHolder extends BaseListAdapter.ViewHolder {
        @BindView(R.id.place)
        TextView tv_place;
        @BindView(R.id.total)
        TextView tv_total;
        @BindView(R.id.raw_wood)
        TextView tv_raw_wood;
        @BindView(R.id.board_wood)
        TextView tv_board_wood;

        public MyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(int position) {
            tv_place.setText(getItem(position).getArriveTime());
            tv_total.setText(getItem(position).getGoodsCount());
            tv_raw_wood.setText(getItem(position).getLogWood());
            tv_board_wood.setText(getItem(position).getBoardWood());
        }
    }

}
