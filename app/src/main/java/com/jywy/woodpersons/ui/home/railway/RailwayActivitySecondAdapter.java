package com.jywy.woodpersons.ui.home.railway;

import android.view.View;
import android.widget.TextView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseListAdapter;
import com.jywy.woodpersons.network.entity.RailwayGoods;

import butterknife.BindView;

/**
 * Created by 高 on 2017/3/21.
 */

public class RailwayActivitySecondAdapter extends BaseListAdapter<RailwayGoods, RailwayActivitySecondAdapter.MyViewHolder> {

    @Override
    protected int getItemViewLayout() {
        return R.layout.line_show_item;
    }

    @Override
    protected MyViewHolder getItemViewHolder(View view) {
        return new MyViewHolder(view);
    }

    class MyViewHolder extends BaseListAdapter.ViewHolder {
        @BindView(R.id.time)
        TextView tv_Time;
        @BindView(R.id.train)
        TextView tv_train;
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
            tv_Time.setText(getItem(position).getArriveTime());
            tv_train.setText(getItem(position).getTrainDisplay());
            tv_total.setText(getItem(position).getGoodsCount());
            tv_raw_wood.setText(getItem(position).getLogWood());
            tv_board_wood.setText(getItem(position).getBoardWood());
        }
    }


}
