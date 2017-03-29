package com.jywy.woodpersons.ui.home.unsold;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseListAdapter;
import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.network.entity.UnSoldMarket;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

/**
 * Created by 高 on 2017/3/28.
 */

public class UnSoldMarketAdapter extends BaseListAdapter<UnSoldMarket, UnSoldMarketAdapter.MyViewHolder> {


    @Override
    protected int getItemViewLayout() {
        return R.layout.item_unsold_list;
    }

    @Override
    protected MyViewHolder getItemViewHolder(View view) {
        return new MyViewHolder(view);
    }

    class MyViewHolder extends BaseListAdapter.ViewHolder {

        @BindView(R.id.iv_unsold_pic)
        ImageView ivUnsoldPic;
        @BindView(R.id.tv_unsold_guige)
        TextView tvUnsoldGuige;
        @BindView(R.id.tv_unsold_hun)
        TextView tvUnsoldHun;
        @BindView(R.id.tv_unsold_place)
        TextView tvUnsoldPlace;
        @BindView(R.id.tv_unsold_port)
        TextView tvUnsoldPort;
        @BindView(R.id.tv_unsold_contact_phone)
        TextView tvUnsoldContactPhone;
        @BindView(R.id.tv_unsold_time)
        TextView tvUnsoldTime;

        public MyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void bind(int position) {
            String carNum = getItem(position).getCarNum();
            String stuffName = getItem(position).getStuffName();
            String lenName = getItem(position).getLenName();
            String guiGe = getItem(position).getGuiGe();
            String kindName = getItem(position).getKindName();
            String contactPhone = getItem(position).getContactPhone();
            String multiGuiGe = getItem(position).getMultiGuiGe();
            String portName = getItem(position).getPortName();
            String dumpPosition = getItem(position).getDumpPosition();
            String updateTime = getItem(position).getUpdateTime();
            String substring1 = updateTime.substring(0,16);
            String pic = getItem(position).getPic();

            if (carNum.equals("")) {
                if (guiGe.equals("")) {
                    tvUnsoldGuige.setText(stuffName + "  " + lenName + "  " + kindName);
                } else {
                    tvUnsoldGuige.setText(stuffName + "  " + lenName + "  " + guiGe);
                }

            } else {
                String substring = carNum.substring(carNum.length() - 4, carNum.length());

                if (guiGe.equals("")) {
                    tvUnsoldGuige.setText(substring + "  " + stuffName + "  " + lenName + "  " + kindName);
                } else {
                    tvUnsoldGuige.setText(substring + "  " + stuffName + "  " + lenName + "  " + guiGe);
                }

            }


            if (!multiGuiGe.equals("")) {
                tvUnsoldHun.setText(" " + multiGuiGe + " ");
            } else {
                tvUnsoldHun.setVisibility(View.INVISIBLE);
            }
            if (!dumpPosition.equals("")) {
                tvUnsoldPlace.setText(dumpPosition);
            }
            tvUnsoldPort.setText(portName);
            tvUnsoldContactPhone.setText(contactPhone);
            tvUnsoldTime.setText(substring1);

            Picasso.with(getContext()).load(WoodPersonsClient.BASE_IMG + pic)
                    .placeholder(R.drawable.ic_default_unsold_pic)
                    .error(R.drawable.ic_default_unsold_pic)
                    .into(ivUnsoldPic);

        }
    }

}
