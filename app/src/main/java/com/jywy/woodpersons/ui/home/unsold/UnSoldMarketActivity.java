package com.jywy.woodpersons.ui.home.unsold;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.base.PtrWrapper;
import com.jywy.woodpersons.base.wrapper.ToolbarWrapper;
import com.jywy.woodpersons.commons.ActivityUtils;
import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.network.entity.UnSoldMarket;
import com.jywy.woodpersons.network.entity.UnSoldMarketKind;
import com.jywy.woodpersons.network.entity.UnSoldMarketLength;
import com.jywy.woodpersons.network.entity.UnSoldMarketList;
import com.jywy.woodpersons.network.entity.UnSoldMarketListRsp;
import com.jywy.woodpersons.network.entity.UnSoldMarketPageEntity;
import com.jywy.woodpersons.network.entity.UnSoldMarketPort;
import com.jywy.woodpersons.network.entity.UnSoldMarketRsp;
import com.jywy.woodpersons.network.entity.UnSoldMarketStuff;
import com.jywy.woodpersons.ui.home.railway.RailwayGoodsInfoActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import butterknife.OnItemClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UnSoldMarketActivity extends BaseActivity {

    @BindViews({R.id.tv_unsold_place, R.id.tv_unsold_goods, R.id.tv_unsold_tree, R.id.tv_unsold_more})
    TextView[] textViews;

    @BindView(R.id.list_unsold_goods)
    ListView listUnsoldGoods;

    @BindView(R.id.tv_error_text)
    TextView tv_ErrorText;

    private ActivityUtils activityUtils;
    private List<UnSoldMarketPort> unSoldMarketPort;
    private List<UnSoldMarketKind> unSoldMarketKind;
    private List<UnSoldMarketStuff> unSoldMarketStuff;
    private List<UnSoldMarketLength> unSoldMarketLength;
    private int currentView;
    private PtrWrapper ptrWrapper;
    private int portId;//区域
    private int kindid;//货种
    private int stuffid;//树种
    private int productlen;//长度
    private int pagenum;//当前页
    private UnSoldMarketAdapter unSoldMarketAdapter;
    private Call<UnSoldMarketRsp> unSoldMarketRspCall;
    private List<UnSoldMarket> unSoldMarkets;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                UnSoldMarketList unSoldMarketList = (UnSoldMarketList) msg.obj;
                unSoldMarketPort = unSoldMarketList.getUnSoldMarketPort();
                unSoldMarketKind = unSoldMarketList.getUnSoldMarketKind();
                unSoldMarketStuff = unSoldMarketList.getUnSoldMarketStuff();
                unSoldMarketLength = unSoldMarketList.getUnSoldMarketLength();
            }
            if (msg.what == 2) {
                UnSoldMarketRsp body = (UnSoldMarketRsp) msg.obj;
                unSoldMarkets = body.getUnSoldMarkets();
                if (body.getPageEntity().getRowCount().equals("0")) {
                    unSoldMarketRspCall.cancel();
                    tv_ErrorText.setVisibility(View.VISIBLE);
                }
                tv_ErrorText.setVisibility(View.GONE);


            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LoadListTabData();
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_un_sold_market;
    }

    @Override
    protected void initView() {
        activityUtils = new ActivityUtils(this);
        //Toolbar
        new ToolbarWrapper(this).setCustomTitle(R.string.unsold_market);

        ptrWrapper = new PtrWrapper(this, true) {
            @Override
            protected void onRefresh() {
                LoadData(true);
            }

            @Override
            protected void onLoadMore() {
                LoadData(false);
            }
        };

        //listview适配器
        unSoldMarketAdapter = new UnSoldMarketAdapter();

        // 自动刷新
        ptrWrapper.postRefreshDelayed(50);

    }

    //加载listview数据
    private void LoadData(final boolean isRefresh) {

        if (unSoldMarketRspCall != null) {
            unSoldMarketRspCall.cancel();
        }
        unSoldMarketRspCall = WoodPersonsClient.getInstance().getWoodPersonsApi().getUnSoldMarket(portId, kindid, stuffid, productlen, pagenum, 8);
        unSoldMarketRspCall.enqueue(new Callback<UnSoldMarketRsp>() {
            @Override
            public void onResponse(Call<UnSoldMarketRsp> call, Response<UnSoldMarketRsp> response) {

                if (response.isSuccessful()) {
                    UnSoldMarketRsp body = response.body();
                    UnSoldMarketPageEntity pageEntity = body.getPageEntity();
                    String pageCount = pageEntity.getPageCount();
                    List<UnSoldMarket> unSoldMarkets = body.getUnSoldMarkets();
                    Message message = handler.obtainMessage();
                    message.what = 2;
                    message.obj = body;
                    handler.sendMessage(message);

                    if (isRefresh) {
                        pagenum = 0;
                        unSoldMarketAdapter.clear();
                        listUnsoldGoods.setAdapter(unSoldMarketAdapter);
                        unSoldMarketAdapter.reset(unSoldMarkets);
                    } else {
                        if (pagenum <= Integer.valueOf(pageCount)) {
                            listUnsoldGoods.setAdapter(unSoldMarketAdapter);
                            unSoldMarketAdapter.addAll(unSoldMarkets);
                            pagenum++;
                        }
                    }
                }
                ptrWrapper.stopRefresh();
            }

            @Override
            public void onFailure(Call<UnSoldMarketRsp> call, Throwable t) {
                ptrWrapper.stopRefresh();
                activityUtils.showToast("请求失败" + t.getMessage());
            }
        });
    }

    @OnClick({R.id.tv_unsold_place, R.id.tv_unsold_goods, R.id.tv_unsold_tree, R.id.tv_unsold_more})
    public void clickChange(View view) {
        switch (view.getId()) {
            case R.id.tv_unsold_place:
                showPopupMenu(view, 0);
                currentView = 0;
                break;
            case R.id.tv_unsold_goods:
                showPopupMenu(view, 1);
                currentView = 1;
                break;
            case R.id.tv_unsold_tree:
                showPopupMenu(view, 2);
                currentView = 2;
                break;
            case R.id.tv_unsold_more:
                showPopupMenu(view, 3);
                currentView = 3;
                break;
        }

    }


    //加载Tab标签数据
    private void LoadListTabData() {
        Call<UnSoldMarketListRsp> unSoldMarketListTab = WoodPersonsClient.getInstance().getWoodPersonsApi().getUnSoldMarketListTab(8);
        unSoldMarketListTab.enqueue(new Callback<UnSoldMarketListRsp>() {
            @Override
            public void onResponse(Call<UnSoldMarketListRsp> call, Response<UnSoldMarketListRsp> response) {
                if (response.isSuccessful()) {

                    UnSoldMarketListRsp body = response.body();
                    UnSoldMarketList unSoldMarketList = body.getUnSoldMarketList();
                    Message message = handler.obtainMessage();
                    message.what = 1;
                    message.obj = unSoldMarketList;
                    handler.sendMessage(message);

                }
            }

            @Override
            public void onFailure(Call<UnSoldMarketListRsp> call, Throwable t) {
                activityUtils.showToast(t.getMessage());

            }
        });
    }

    public void showPopupMenu(View view, int index) {

        PopupMenu popupMenu = new PopupMenu(this, view);

        //给PopupMenu填充本地Menu
        popupMenu.inflate(R.menu.unsold_groups);
        popupMenu.setOnMenuItemClickListener(menuItemListener);

        Menu menu = popupMenu.getMenu();
        if (index == 0) {
            for (UnSoldMarketPort repo : unSoldMarketPort) {
                menu.add(Menu.NONE, Integer.valueOf(repo.getPortId()), Menu.NONE, repo.getPortName());
            }
        } else if (index == 1) {
            for (UnSoldMarketKind repo : unSoldMarketKind) {
                menu.add(Menu.NONE, Integer.valueOf(repo.getKindId()), Menu.NONE, repo.getKindName());
            }
        } else if (index == 2) {
            for (UnSoldMarketStuff repo : unSoldMarketStuff) {
                menu.add(Menu.NONE, Integer.valueOf(repo.getStuffId()), Menu.NONE, repo.getStuffName());
            }
        } else {
            for (UnSoldMarketLength repo : unSoldMarketLength) {
                menu.add(Menu.NONE, Integer.valueOf(repo.getLenId()), Menu.NONE, repo.getLenName());
            }
        }

        popupMenu.show();
    }

    @OnItemClick(R.id.list_unsold_goods)
    public void JumpToInfo(int position) {
        UnSoldMarket item = unSoldMarketAdapter.getItem(position);

        String cdKey = item.getCdKey();
        Intent intent = RailwayGoodsInfoActivity.getStartIntent(this, cdKey);
        startActivity(intent);

    }

    private PopupMenu.OnMenuItemClickListener menuItemListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            //改变标题 和数据
            textViews[currentView].setText(item.getTitle().toString());

            if (currentView == 0) {
                if (item.getTitle().toString().equals("不限")) {
                    portId = 0;
                } else {
                    portId = item.getItemId();
                }
                LoadData(true);
            } else if (currentView == 1) {
                if (item.getTitle().toString().equals("不限")) {
                    kindid = 0;
                } else {
                    kindid = item.getItemId();
                }
                LoadData(true);
            } else if (currentView == 2) {
                if (item.getTitle().toString().equals("不限")) {
                    stuffid = 0;
                } else {
                    stuffid = item.getItemId();
                }
                LoadData(true);
            } else if (currentView == 3) {
                if (item.getTitle().toString().equals("不限")) {
                    productlen = 0;
                } else {
                    productlen = item.getItemId();
                }
                LoadData(true);
            }

            return true;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unSoldMarketRspCall.cancel();
        unSoldMarketRspCall = null;
    }


}