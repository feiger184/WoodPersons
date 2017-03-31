package com.jywy.woodpersons.ui.home.buy;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseListAdapter;
import com.jywy.woodpersons.network.entity.WoodBuyEntity;

import butterknife.BindView;

/**
 * Created by 高 on 2017/3/30.
 */
public class WoodBuyAdapter extends BaseListAdapter<WoodBuyEntity, WoodBuyAdapter.MyViewHolder> {

    @Override
    protected int getItemViewLayout() {
        return R.layout.item_woodbuy;
    }

    @Override
    protected MyViewHolder getItemViewHolder(View view) {
        return new WoodBuyAdapter.MyViewHolder(view);
    }

    class MyViewHolder extends BaseListAdapter.ViewHolder {


        @BindView(R.id.tv_buy_item_guige)
        TextView tvBuyItemGuige;
        @BindView(R.id.tv_buy_item_houdu)
        TextView tvBuyItemHeight;
        @BindView(R.id.tv_buy_item_width)
        TextView tvBuyItemWidth;
        @BindView(R.id.tv_buy_item_contact)
        TextView tvBuyItemContact;
        @BindView(R.id.tv_buy_item_time)
        TextView tvBuyItemTime;

        public MyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(int position) {
            String portName = getItem(position).getPortName();
            String stuffName = getItem(position).getStuffName();
            String lenName = getItem(position).getLenName();
            String kindName = getItem(position).getKindName();
            String thinckNess = getItem(position).getThinckNess();
            String wideWidth = getItem(position).getWideWidth();
            String contactPhone = getItem(position).getContactPhone();
            String refreshTime = getItem(position).getRefreshTime();
            tvBuyItemGuige.setText(portName + "  " + stuffName + "  " + lenName + "  " + kindName);
            tvBuyItemHeight.setText(thinckNess);
            tvBuyItemWidth.setText(wideWidth);
            tvBuyItemContact.setText(contactPhone);
            tvBuyItemTime.setText(refreshTime);

        }
    }
}
