package com.jywy.woodpersons.ui.home.railway;

import android.view.View;
import android.widget.TextView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseListAdapter;
import com.jywy.woodpersons.network.entity.RailwayProductSpec;

import butterknife.BindView;

/**
 * Created by 高 on 2017/3/31.
 */

public class RailwayGoodsInfoAdapter extends BaseListAdapter<RailwayProductSpec, RailwayGoodsInfoAdapter.MyViewHolder> {


    @Override
    protected int getItemViewLayout() {
        return R.layout.item_railway_info_guige;
    }

    @Override
    protected MyViewHolder getItemViewHolder(View view) {
        return new RailwayGoodsInfoAdapter.MyViewHolder(view);
    }

    class MyViewHolder extends BaseListAdapter.ViewHolder {

        @BindView(R.id.tv_railway_info_guige)
        TextView tvInfoItemGuige;

        public MyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(int position) {
            String stuffName = getItem(position).getStuffName();
            String lenName = getItem(position).getLenName();
            String kindId = getItem(position).getKindId();
            String kindName = getItem(position).getKindName();
            Object timbername = getItem(position).getTimbername();
            Object diameterlen = getItem(position).getDiameterlen();
            Object spec = getItem(position).getSpec();


            if (kindId.equals("1")) {
                tvInfoItemGuige.setText(stuffName + "    " + lenName + "     " + kindName + "       " + diameterlen + "      " + timbername);
            } else {
                tvInfoItemGuige.setText(stuffName + "    " + lenName + "     " + kindName + "       " + spec);
            }

        }
    }
}