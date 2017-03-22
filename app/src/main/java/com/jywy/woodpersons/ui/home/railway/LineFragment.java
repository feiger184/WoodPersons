package com.jywy.woodpersons.ui.home.railway;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseFragment;
import com.jywy.woodpersons.commons.ActivityUtils;
import com.jywy.woodpersons.network.WoodPersonsClient;
import com.jywy.woodpersons.network.entity.RailwayGoods;
import com.jywy.woodpersons.network.entity.RailwayGoodsRsp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by 高 on 2017/3/21.
 */

public class LineFragment extends Fragment {

    private int port_id;

    private ActivityUtils activityUtils;
    private ProgressDialog dialog;

    @BindView(R.id.recyclerViewList)
    ListView recyclerView;

    private List<RailwayGoods> railwayGoodsesList = new ArrayList<RailwayGoods>();
    private int portID;
    private RailwayActivitySecondAdapter railwayActivitySecondAdapter;
    private View view;
    private RailwayActivitySecondAdapter railwayActivitySecondAdapter1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {

            view = inflater.inflate(R.layout.line_show_list, container, false);
        }

        return view;
    }


    protected void initView() {
        ButterKnife.bind(this, view);
        activityUtils = new ActivityUtils(getActivity());
        Bundle bundle = getArguments();
        port_id = bundle.getInt("PORT_ID");
        //初始化RecyclerViewn

        railwayActivitySecondAdapter1 = new RailwayActivitySecondAdapter();
        recyclerView.setAdapter(railwayActivitySecondAdapter1);
        getRailwayViewData(port_id);

    }

    public void getRailwayViewData(int portId) {

        showProgress();
        Call<RailwayGoodsRsp> railwayGoodsRspCall = WoodPersonsClient.getInstance().getWoodPersonsApi().getRailwayData(portId, 8);
        railwayGoodsRspCall.enqueue(new Callback<RailwayGoodsRsp>() {
            @Override
            public void onResponse(Call<RailwayGoodsRsp> call, Response<RailwayGoodsRsp> response) {
                //隐藏进度
                hideProgress();

                if (response.isSuccessful()) {
                    RailwayGoodsRsp railwayGoodsRsp = response.body();

                    Log.e("=============", "onResponse: " + railwayGoodsRsp.getRailwayGoodsesData().get(0).getBoardWood());
                    if (railwayGoodsRsp == null) {

                        showMessage("未知错误");
                    }
                }
            }

            @Override
            public void onFailure(Call<RailwayGoodsRsp> call, Throwable t) {
                showMessage("请求失败了" + t.getMessage());
            }
        });
    }


    public void showProgress() {
        dialog = ProgressDialog.show(getContext(), "数据加载", "数据正在加载中，不要着急~");
    }

    public void hideProgress() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }


}
