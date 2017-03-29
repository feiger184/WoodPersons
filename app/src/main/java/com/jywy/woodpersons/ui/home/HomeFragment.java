package com.jywy.woodpersons.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseFragment;
import com.jywy.woodpersons.base.MarqueTextView;
import com.jywy.woodpersons.commons.ActivityUtils;
import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.network.entity.ExChangRate;
import com.jywy.woodpersons.network.entity.ExChangeRateRsp;
import com.jywy.woodpersons.network.entity.HomeUnSoldMarketRsp;
import com.jywy.woodpersons.network.entity.RailwayGoodsInfo;
import com.jywy.woodpersons.network.entity.UnSoldMarket;
import com.jywy.woodpersons.ui.home.railway.RailwayActivity;
import com.jywy.woodpersons.ui.home.railway.RailwayGoodsInfoActivity;
import com.jywy.woodpersons.ui.home.unsold.UnSoldMarketActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.os.Build.VERSION_CODES.BASE;
import static cn.jpush.android.api.b.b;
import static cn.jpush.android.api.b.c;
import static cn.jpush.android.api.b.e;

/**
 * Created by 高 on 2017/3/20.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.tvM)
    MarqueTextView tvExchangeRate;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private RecyclerView.LayoutManager mLayoutManager;
    private UnsoldAdapter mAdapter;

    private ActivityUtils activityUtils;

    private List<ExChangRate> exChangRates;
    private List<UnSoldMarket> homeUnSoldMarket;
    private List<UnsoldInfo> list = new ArrayList<>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                exChangRates = (List<ExChangRate>) msg.obj;
                ShowExchangeRateToTextView();
            }

            if (msg.what == 2) {
                homeUnSoldMarket = (List<UnSoldMarket>) msg.obj;
                buildData();
            }

        }
    };


    //加载布局
    @Override
    protected int geContentViewLayout() {
        return R.layout.index_test;
    }

    /*
        * 初始化视图
        * */
    protected void initView() {
        activityUtils = new ActivityUtils(this);
        getExChangeRate();//加载汇率数据
        getHomeUnsoldMarketData();
        initDrawable();

    }

    //图片处理
    protected void initDrawable() {
        TextView tv_railway = ButterKnife.findById((Activity) getContext(), R.id.tv_Railway_goods);
        TextView tv_home_offer = ButterKnife.findById((Activity) getContext(), R.id.tv_home_offer);
        TextView tv_home_order = ButterKnife.findById((Activity) getContext(), R.id.tv_home_order);
        TextView tv_home_boutique = ButterKnife.findById((Activity) getContext(), R.id.tv_home_boutique);
        TextView tv_home_store = ButterKnife.findById((Activity) getContext(), R.id.tv_home_store);
        TextView tv_home_unsold = ButterKnife.findById((Activity) getContext(), R.id.tv_home_unsold);
        TextView tv_home_buy = ButterKnife.findById((Activity) getContext(), R.id.tv_home_buy);
        TextView tv_home_more = ButterKnife.findById((Activity) getContext(), R.id.tv_home_more);

        Drawable dw_railway = getResources().getDrawable(R.drawable.home_railway);
        Drawable dw_offer = getResources().getDrawable(R.drawable.home_offer);
        Drawable dw_order = getResources().getDrawable(R.drawable.home_order);
        Drawable dw_boutique = getResources().getDrawable(R.drawable.home_boutique);
        Drawable dw_store = getResources().getDrawable(R.drawable.home_store);
        Drawable dw_unsold = getResources().getDrawable(R.drawable.home_unsold);
        Drawable dw_buy = getResources().getDrawable(R.drawable.home_buy);
        Drawable dw_home_more = getResources().getDrawable(R.drawable.home_more);

        dw_railway.setBounds(0, 0, 160, 160);//第一0是距左边距离，第二0是距上边距离，160分别是长宽
        tv_railway.setCompoundDrawables(null, dw_railway, null, null);//只放左边
        dw_offer.setBounds(0, 0, 160, 160);//第一0是距左边距离，第二0是距上边距离，160分别是长宽
        tv_home_offer.setCompoundDrawables(null, dw_offer, null, null);//只放左边
        dw_order.setBounds(0, 0, 160, 160);//第一0是距左边距离，第二0是距上边距离，160分别是长宽
        tv_home_order.setCompoundDrawables(null, dw_order, null, null);//只放左边
        dw_boutique.setBounds(0, 0, 160, 160);//第一0是距左边距离，第二0是距上边距离，160分别是长宽
        tv_home_boutique.setCompoundDrawables(null, dw_boutique, null, null);//只放左边
        dw_store.setBounds(0, 0, 160, 160);//第一0是距左边距离，第二0是距上边距离，160分别是长宽
        tv_home_store.setCompoundDrawables(null, dw_store, null, null);//只放左边
        dw_unsold.setBounds(0, 0, 160, 160);//第一0是距左边距离，第二0是距上边距离，160分别是长宽
        tv_home_unsold.setCompoundDrawables(null, dw_unsold, null, null);//只放左边
        dw_buy.setBounds(0, 0, 160, 160);//第一0是距左边距离，第二0是距上边距离，160分别是长宽
        tv_home_buy.setCompoundDrawables(null, dw_buy, null, null);//只放左边
        dw_home_more.setBounds(0, 0, 160, 160);//第一0是距左边距离，第二0是距上边距离，160分别是长宽
        tv_home_more.setCompoundDrawables(null, dw_home_more, null, null);//只放左边

        //汇率
        TextView tv_parities = ButterKnife.findById((Activity) getContext(), R.id.tv_parities);
        Drawable dw_parities = getResources().getDrawable(R.drawable.parities);
        dw_parities.setBounds(0, 0, 40, 40);
        tv_parities.setCompoundDrawables(dw_parities, null, null, null);

        //店铺 上面4个
        TextView tv_wood_count = ButterKnife.findById((Activity) getContext(), R.id.tv_wood_count);
        TextView tv_wood_message = ButterKnife.findById((Activity) getContext(), R.id.tv_wood_message);
        TextView tv_wood_freight = ButterKnife.findById((Activity) getContext(), R.id.tv_wood_freight);
        TextView tv_wood_recommend = ButterKnife.findById((Activity) getContext(), R.id.tv_wood_recommend);

        Drawable dw_wood_count = getResources().getDrawable(R.drawable.wood_count);
        Drawable dw_wood_message = getResources().getDrawable(R.drawable.wood_message);
        Drawable dw_wood_freight = getResources().getDrawable(R.drawable.wood_freight);
        Drawable dw_wood_recommend = getResources().getDrawable(R.drawable.wood_recommend);

        dw_wood_count.setBounds(0, 0, 80, 80);
        tv_wood_count.setCompoundDrawables(null, dw_wood_count, null, null);//只放上边
        dw_wood_message.setBounds(0, 0, 80, 80);
        tv_wood_message.setCompoundDrawables(null, dw_wood_message, null, null);//只放上边
        dw_wood_freight.setBounds(0, 0, 80, 80);
        tv_wood_freight.setCompoundDrawables(null, dw_wood_freight, null, null);//只放上边
        dw_wood_recommend.setBounds(0, 0, 80, 80);
        tv_wood_recommend.setCompoundDrawables(null, dw_wood_recommend, null, null);//只放上边
        //未售
//        TextView unsold_site= ButterKnife.findById((Activity) getContext(),R.id.unsold_site);
//        Drawable dw_unsold_location=getResources().getDrawable(R.drawable.location);
//        dw_unsold_location.setBounds(0,0,30,35);
//        unsold_site.setCompoundDrawables(dw_unsold_location,null,null,null);
    }

    //8个按钮的点击事件
    @OnClick({R.id.tv_Railway_goods, R.id.tv_home_unsold})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_Railway_goods:
                activityUtils.startActivity(RailwayActivity.class);
                break;
            case R.id.tv_home_unsold:
                activityUtils.startActivity(UnSoldMarketActivity.class);
                break;
        }
    }

    //得到汇率
    private void getExChangeRate() {
        Call<ExChangeRateRsp> rateRspCall = WoodPersonsClient.getInstance().getWoodPersonsApi().getExChangeRate();
        rateRspCall.enqueue(new Callback<ExChangeRateRsp>() {
            @Override
            public void onResponse(Call<ExChangeRateRsp> call, Response<ExChangeRateRsp> response) {
                if (response.isSuccessful()) {
                    ExChangeRateRsp body = response.body();
                    List<ExChangRate> changRates = body.getExChangRates();
                    Message msg = handler.obtainMessage();
                    msg.what = 1;
                    msg.obj = changRates;
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onFailure(Call<ExChangeRateRsp> call, Throwable t) {
                activityUtils.showToast(t.getMessage());

            }
        });

    }

    //展示汇率 跑马灯
    private void ShowExchangeRateToTextView() {

        ExChangRate exChangRate1 = exChangRates.get(0);
        String dollarName = exChangRate1.getCurrencyName();
        String dollarPrice = exChangRate1.getMeanPrice();


        ExChangRate exChangRate2 = exChangRates.get(1);
        String eurName = exChangRate2.getCurrencyName();
        String eurPrice = exChangRate2.getMeanPrice();


        ExChangRate exChangRate3 = exChangRates.get(2);
        String rubName = exChangRate3.getCurrencyName();
        String rubPrice = exChangRate3.getMeanPrice();


        ExChangRate exChangRate4 = exChangRates.get(3);
        String chfName = exChangRate4.getCurrencyName();
        String chfPrice = exChangRate4.getMeanPrice();

        String text = String.format(getResources().getString(R.string.text_paoma), dollarName, dollarPrice, eurName, eurPrice, rubName, rubPrice, chfName, chfPrice);
        int index[] = new int[8];
        index[0] = text.indexOf(dollarName);
        index[1] = text.indexOf(dollarPrice);
        index[2] = text.indexOf(eurName);
        index[3] = text.indexOf(eurPrice);
        index[4] = text.indexOf(rubName);
        index[5] = text.indexOf(rubPrice);
        index[6] = text.indexOf(chfName);
        index[7] = text.indexOf(chfPrice);

        SpannableStringBuilder style = new SpannableStringBuilder(text);
        style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.yellow)), index[1], index[1] + 6, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.yellow)), index[3], index[3] + 6, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.yellow)), index[5], index[5] + 6, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.yellow)), index[7], index[7] + 6, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        tvExchangeRate.setText(style);

    }

    //得到未受市场数据
    private void getHomeUnsoldMarketData() {
        Call<HomeUnSoldMarketRsp> homeUnSoldMaret = WoodPersonsClient.getInstance().getWoodPersonsApi().getHomeUnSoldMaret();
        homeUnSoldMaret.enqueue(new Callback<HomeUnSoldMarketRsp>() {
            @Override
            public void onResponse(Call<HomeUnSoldMarketRsp> call, Response<HomeUnSoldMarketRsp> response) {
                if (response.isSuccessful()) {
                    HomeUnSoldMarketRsp body = response.body();
                    List<UnSoldMarket> homeUnSoldMarket = body.getHomeUnSoldMarket();
                    Message message = handler.obtainMessage();
                    message.what = 2;
                    message.obj = homeUnSoldMarket;
                    handler.sendMessage(message);
                }
            }

            @Override
            public void onFailure(Call<HomeUnSoldMarketRsp> call, Throwable t) {
                activityUtils.showToast(t.getMessage());
            }
        });

    }

    //生成4个产品数据，这些Url地址都来源于网络
    private void buildData() {

        //设置布局管理器为2列，纵向
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mAdapter = new UnsoldAdapter(getContext(), list);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        for (int i = 0; i < 4; i++) {
            UnsoldInfo p = new UnsoldInfo();
            UnSoldMarket unSoldMarket = homeUnSoldMarket.get(i);
            String stuffName = unSoldMarket.getStuffName();
            String lenName = unSoldMarket.getLenName();
            String guiGe = unSoldMarket.getGuiGe();
            String multiGuiGe = unSoldMarket.getMultiGuiGe();
            String portName = unSoldMarket.getPortName();
            String contactPhone = unSoldMarket.getContactPhone();
            String pic = unSoldMarket.getPic();
            String cdKey = unSoldMarket.getCdKey();
            p.productMes = stuffName + "   " + lenName + "  " + guiGe;
            if (!multiGuiGe.equals("")) {
                p.productType = multiGuiGe;
            } else {
                p.productType = "";
            }
            p.productSite = portName;
            p.productPhone = contactPhone;
            p.drawableUrl = WoodPersonsClient.BASE_IMG + pic;
            p.productCdKey = cdKey;
//            p.imgHeight = (i % 2)*100 + 400; //偶数和奇数的图片设置不同的高度，以到达错开的目的
            list.add(p);
        }

        //点击主页未售市场图片进入详情页面
        mAdapter.setListener(new UnsoldAdapter.onItemClickListener() {
            @Override
            public void onItemClicked(UnsoldInfo unsoldInfo) {
                String cdKey = unsoldInfo.productCdKey;
                Intent intent = RailwayGoodsInfoActivity.getStartIntent(getActivity(), cdKey);
                getActivity().startActivity(intent);
            }
        });

    }

    /*
    * 点击未售模块进入未售市场
    * */
    @OnClick(R.id.tv_more_unsold)
    public void clickSkipToUnsold() {
        activityUtils.startActivity(UnSoldMarketActivity.class);
    }

}

