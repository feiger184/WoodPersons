package com.jywy.woodpersons.ui.home.unsold;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.base.PtrWrapper;
import com.jywy.woodpersons.base.wrapper.ToolbarWrapper;
import com.jywy.woodpersons.commons.ActivityUtils;
import com.jywy.woodpersons.network.UserPrefs;
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

import java.util.ArrayList;
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

    @BindView(R.id.tab_linear)
    LinearLayout tablayout;
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
    int wide = 0;
    int wideMax = 0;
    int thinckness = 0;
    int thincknessMax = 0;
    int diamterlen = 0;
    int diamterlenMax = 0;
    private UnSoldMarketAdapter unSoldMarketAdapter;
    private Call<UnSoldMarketRsp> unSoldMarketRspCall;
    private List<UnSoldMarket> unSoldMarkets;
    private PopupWindow mPopup;
    private MyGridAdapter adapter;
    private List<String> portList = new ArrayList<>();
    private List<String> kindList = new ArrayList<>();
    private List<String> stuffidList = new ArrayList<>();
    private List<String> lengthList = new ArrayList<>();


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
                addDataToList(); //将Tab数据放入集合中

            }
            if (msg.what == 2) {
                UnSoldMarketRsp body = (UnSoldMarketRsp) msg.obj;
                unSoldMarkets = body.getUnSoldMarkets();
                if (body.getPageEntity().getPageCount().equals("0")) {
                    tv_ErrorText.setVisibility(View.VISIBLE);
                } else {
                    tv_ErrorText.setVisibility(View.INVISIBLE);
                }

            }
        }


    };
    private int produtlen = 0;
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


    /*
    * 将Tab数据放入集合中
    * */
    private void addDataToList() {
        for (UnSoldMarketPort sdf : unSoldMarketPort) {
            String portName = sdf.getPortName();
            portList.add(portName);
        }
        for (UnSoldMarketKind sdf : unSoldMarketKind) {
            String kindName = sdf.getKindName();
            kindList.add(kindName);
        }
        for (UnSoldMarketStuff sdf : unSoldMarketStuff) {
            String stuffName = sdf.getStuffName();
            stuffidList.add(stuffName);
        }
        for (UnSoldMarketLength sdf : unSoldMarketLength) {
            String lenName = sdf.getLenName();
            lengthList.add(lenName);
        }
    }


    //加载listview数据
    private void LoadData(final boolean isRefresh) {

        if (unSoldMarketRspCall != null) {
            unSoldMarketRspCall.cancel();
        }

        unSoldMarketRspCall = WoodPersonsClient.getInstance().getWoodPersonsApi()
                .getUnSoldMarket(portId, kindid, stuffid, productlen, pagenum, UserPrefs.getInstance().getUserid(),
                        wide, wideMax, thinckness, thincknessMax, diamterlen,
                        diamterlenMax);
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

                            listUnsoldGoods.setSelection(unSoldMarketAdapter.getCount()-11);
                            unSoldMarketAdapter.notifyDataSetChanged();
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
                showPopupWindow(0);
                if (mPopup.isShowing()) {
                    // 隐藏窗口，如果设置了点击窗口外小时即不需要此方式隐藏
                    mPopup.dismiss();
                } else {
                    // 显示窗口
                    mPopup.showAsDropDown(tablayout);
                }
                currentView = 0;
                break;
            case R.id.tv_unsold_goods:
                showPopupWindow(1);
                if (mPopup.isShowing()) {
                    // 隐藏窗口，如果设置了点击窗口外小时即不需要此方式隐藏
                    mPopup.dismiss();
                } else {
                    // 显示窗口
                    mPopup.showAsDropDown(tablayout);
                }
                currentView = 1;

                break;
            case R.id.tv_unsold_tree:
                showPopupWindow(2);
                if (mPopup.isShowing()) {
                    // 隐藏窗口，如果设置了点击窗口外小时即不需要此方式隐藏
                    mPopup.dismiss();
                } else {
                    // 显示窗口
                    mPopup.showAsDropDown(tablayout);
                }
                currentView = 2;
                break;
            case R.id.tv_unsold_more:
                currentView = 3;
                showPopupWindow(3);
                if (mPopup.isShowing()) {
                    // 隐藏窗口，如果设置了点击窗口外小时即不需要此方式隐藏
                    mPopup.dismiss();
                } else {
                    // 显示窗口
                    mPopup.showAsDropDown(tablayout);
                }
                break;
        }


    }


    //加载Tab标签数据
    private void LoadListTabData() {
        Call<UnSoldMarketListRsp> unSoldMarketListTab = WoodPersonsClient.getInstance().getWoodPersonsApi().getUnSoldMarketListTab(UserPrefs.getInstance().getUserid());
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


    public void showPopupWindow(int currentView) {
        wide = 0;
        wideMax = 0;
        thinckness = 0;
        thincknessMax = 0;
        diamterlen = 0;
        diamterlenMax = 0;
        View inflate = LayoutInflater.from(this).inflate(R.layout.layout_unsold_tab, null);
        final RecyclerView recyclerviewTab = (RecyclerView) inflate.findViewById(R.id.recyclerview_unsold_tab);
        final TextView leghtlay = (TextView) inflate.findViewById(R.id.lengthlayout);
        LinearLayout widthHeight = (LinearLayout) inflate.findViewById(R.id.tv_width_height);
        final LinearLayout jingJi = (LinearLayout) inflate.findViewById(R.id.tv_jingji);
        jingJi.setVisibility(View.GONE);
        final EditText etWideMin = (EditText) inflate.findViewById(R.id.etWideMin);
        setRegion(etWideMin);
        final EditText etWideMax = (EditText) inflate.findViewById(R.id.etWideWidthMax);
        setRegion(etWideMax);
        final EditText etHouduMin = (EditText) inflate.findViewById(R.id.etHouduMin);
        setRegion(etHouduMin);
        final EditText etHouduMax = (EditText) inflate.findViewById(R.id.etHouduMax);
        setRegion(etHouduMax);
        final EditText etRadioMin = (EditText) inflate.findViewById(R.id.etRadioMin);
        setRegion(etRadioMin);
        final EditText etRadioMax = (EditText) inflate.findViewById(R.id.etRadioMax);
        setRegion(etRadioMax);
        Button btncommit = (Button) inflate.findViewById(R.id.btn_length_commit);

        //设置布局管理器
        if (currentView != 3) {
            leghtlay.setVisibility(View.GONE);
            widthHeight.setVisibility(View.GONE);
            btncommit.setVisibility(View.GONE);

            RecyclerView.LayoutManager manager = new GridLayoutManager(this, 2);
            recyclerviewTab.setLayoutManager(manager);
        } else {

            RecyclerView.LayoutManager manager = new GridLayoutManager(this, 4);
            recyclerviewTab.setLayoutManager(manager);
        }

        adapter = new MyGridAdapter();
        recyclerviewTab.setAdapter(adapter);
        if (adapter != null) {
            adapter.clear();
        }
        if (currentView == 0) {
            adapter.addDatas(portList);
            adapter.setOnItemClickListener(new MyGridAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int postion) {
                    if (postion == 0) {
                        textViews[0].setText("区域");
                    } else {
                        textViews[0].setText(portList.get(postion));
                    }
                    portId = Integer.valueOf(unSoldMarketPort.get(postion).getPortId());
                    ptrWrapper.autoRefresh();
                    mPopup.dismiss();


                }
            });

        } else if (currentView == 1) {
            adapter.addDatas(kindList);
            adapter.setOnItemClickListener(new MyGridAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int postion) {
                    if (postion == 0) {
                        textViews[1].setText("货种");
                    } else {
                        textViews[1].setText(kindList.get(postion));
                    }
                    kindid = Integer.valueOf(unSoldMarketKind.get(postion).getKindId());
                    ptrWrapper.autoRefresh();
                    mPopup.dismiss();

                }
            });


        } else if (currentView == 2) {
            adapter.addDatas(stuffidList);
            adapter.setOnItemClickListener(new MyGridAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int postion) {
                    if (postion == 0) {
                        textViews[2].setText("树种");
                    } else {
                        textViews[2].setText(stuffidList.get(postion));
                    }
                    stuffid = Integer.valueOf(unSoldMarketStuff.get(postion).getStuffId());
                    ptrWrapper.autoRefresh();
                    mPopup.dismiss();
                }
            });


        } else if (currentView == 3) {
            if (textViews[1].getText().toString().equals("原木")) {
                jingJi.setVisibility(View.VISIBLE);
                widthHeight.setVisibility(View.GONE);

            }

            adapter.addDatas(lengthList);
            adapter.setOnItemClickListener(new MyGridAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int postion) {
                    produtlen = Integer.valueOf(unSoldMarketLength.get(postion).getLenId());

                }
            });

        }
        btncommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productlen = produtlen;
                if (etWideMin.getText().toString().equals("")) {
                    wide = 0;
                } else {
                    wide = Integer.valueOf(etWideMin.getText().toString());
                }

                if (etWideMax.getText().toString().equals("")) {
                    wideMax = 0;
                } else {
                    wideMax = Integer.valueOf(etWideMax.getText().toString());
                }

                if (etHouduMin.getText().toString().equals("")) {
                    thinckness = 0;
                } else {
                    thinckness = Integer.valueOf(etHouduMin.getText().toString());
                }

                if (etHouduMax.getText().toString().equals("")) {
                    wide = 0;
                } else {
                    thincknessMax = Integer.valueOf(etHouduMax.getText().toString());
                }
                if (etRadioMin.getText().toString().equals("")) {
                    wide = 0;
                } else {
                    diamterlen = Integer.valueOf(etRadioMin.getText().toString());
                }
                if (etRadioMax.getText().toString().equals("")) {
                    wide = 0;
                } else {
                    diamterlenMax = Integer.valueOf(etRadioMax.getText().toString());
                }
                mPopup.dismiss();
                ptrWrapper.autoRefresh();

            }
        });

        mPopup = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, 800, false);


        /** 设置背景 */
        // 设置点击窗口外边窗口消失
        mPopup.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        mPopup.setFocusable(true);
    }

    @OnItemClick(R.id.list_unsold_goods)
    public void JumpToInfo(int position) {
        UnSoldMarket item = unSoldMarketAdapter.getItem(position);
        String cdKey = item.getCdKey();
        Intent intent = RailwayGoodsInfoActivity.getStartIntent(this, cdKey);
        startActivity(intent);

    }

    private int MIN_MARK = 10;
    private int MAX_MARK = 500;

    //private void setRegion(EditText et)
    private void setRegion(final EditText et) {
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start > 1) {
                    if (MIN_MARK != -1 && MAX_MARK != -1) {
                        int num = Integer.parseInt(s.toString());
                        if (num > MAX_MARK) {
                            s = String.valueOf(MAX_MARK);
                            et.setText(s);
                        } else if (num < MIN_MARK)
                            s = String.valueOf(MIN_MARK);
                        return;
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && !s.equals("")) {
                    if (MIN_MARK != -1 && MAX_MARK != -1) {
                        int markVal = 0;
                        try {
                            markVal = Integer.parseInt(s.toString());
                        } catch (NumberFormatException e) {
                            markVal = 0;
                        }
                        if (markVal > MAX_MARK) {
                            Toast.makeText(getBaseContext(), "不能超过500", Toast.LENGTH_SHORT).show();
                            et.setText(String.valueOf(MAX_MARK));
                        }
                        return;
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unSoldMarketRspCall.cancel();
        unSoldMarketRspCall = null;
    }

}