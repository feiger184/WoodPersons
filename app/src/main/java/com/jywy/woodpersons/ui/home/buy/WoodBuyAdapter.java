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
public class WoodBuyAdapter  extends BaseListAdapter<WoodBuyEntity, WoodBuyAdapter.MyViewHolder> {

    @Override
    protected int getItemViewLayout() {
        return R.layout.item_woodbuy;
    }

    @Override
    protected MyViewHolder getItemViewHolder(View view) {
        return new WoodBuyAdapter.MyViewHolder(view);
    }

    class MyViewHolder extends BaseListAdapter.ViewHolder {


        @BindView(R.id.iv_buy_pic)
        ImageView ivUnsoldPic;
        @BindView(R.id.tv_buy_port)
        TextView tvUnsoldPort;
        @BindView(R.id.tv_buy_contact_phone)
        TextView tvUnsoldContactPhone;
        public MyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(int position) {
            String kindName = getItem(position).getKindName();
            String contactPhone = getItem(position).getContactPhone();
            String portName = getItem(position).getPortName();
            tvUnsoldPort.setText(kindName);
            tvUnsoldContactPhone.setText(contactPhone);

        }
    }
}
