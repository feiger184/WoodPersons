package com.jywy.woodpersons.ui.me;


import android.widget.Button;
import android.widget.Toast;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseFragment;
import com.jywy.woodpersons.network.UserPrefs;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 高 on 2017/3/20.
 */

public class MeFragment extends BaseFragment {

    @BindView(R.id.btn_loginout)
    Button btnloginout;
    @Override
    protected int geContentViewLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {


    }

    @OnClick(R.id.btn_loginout)
    public void loginout() {
        UserPrefs.getInstance().clearUser();
    }

}
