package com.jywy.woodpersons.ui.home.railway;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by ren on 2017/3/20.
 */

public class PlaceFragment extends BaseFragment{

    PlaceFragmentAdapter adapter;
    ArrayList<String> list;

    @Override
    protected int geContentViewLayout() {
        return R.layout.place_show_list;
    }

    @Override
    protected void initView() {

    }
}
