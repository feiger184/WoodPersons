package com.jywy.woodpersons.ui.home.railway;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.jywy.woodpersons.R;
import com.jywy.woodpersons.base.BaseActivity;
import com.jywy.woodpersons.commons.AvatarLoadOptions;
import com.jywy.woodpersons.network.WoodPersonsClient;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import me.relex.circleindicator.CircleIndicator;

public class RailwayGoodsInfoPictureActivity extends BaseActivity {


    @BindView(R.id.viewpager)
    ViewPager viewPager;
    /*使用开源库CircleIndicator来实现ViewPager的圆点指示器。*/
    @BindView(R.id.indicator)
    CircleIndicator indicator;


    //用来拿图片地址的key
    private static final String IMAGES = "images";
    private ArrayList<String> list;
    private ArrayList<ImageView> imageViewArrayList;
    public static Intent getStartIntent(Context context, ArrayList<String> imgUrls) {
        Intent intent = new Intent(context, RailwayGoodsInfoPictureActivity.class);
        intent.putExtra(IMAGES, imgUrls);
        return intent;
    }

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_railway_goods_info_picture;
    }


    @Override
    protected void initView() {
        list = new ArrayList<>();
        imageViewArrayList = new ArrayList<>();
        list = getIntent().getStringArrayListExtra(IMAGES);
        if (list.size() <= 0) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.ic_default_info);
            imageViewArrayList.add(imageView);
            RailwayGoodsPictureAdapter adapter = new RailwayGoodsPictureAdapter(imageViewArrayList);
            viewPager.setAdapter(adapter);
        } else {
            RailwayGoodsPictureAdapter adapter = new RailwayGoodsPictureAdapter(getImage(list));
            viewPager.setAdapter(adapter);
            indicator.setViewPager(viewPager);
        }

    }


    private ArrayList<ImageView> getImage(ArrayList<String> list) {
        ArrayList<ImageView> list_image = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(this);
            ImageLoader.getInstance()
                    .displayImage(WoodPersonsClient.BASE_IMG + list.get(i),
                            imageView, AvatarLoadOptions.build_item());
            list_image.add(imageView);
        }
        return list_image;
    }

}
