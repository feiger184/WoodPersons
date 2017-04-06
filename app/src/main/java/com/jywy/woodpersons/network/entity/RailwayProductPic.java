package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 高 on 2017/4/1.
 */
public class RailwayProductPic {

    @SerializedName("productpic")//大图
    private String PictureUrlBig;

    @SerializedName("thumbpics")
    private String thumbPics;

    public String getPictureUrlBig() {
        return PictureUrlBig;
    }

    public void setPictureUrlBig(String pictureUrlBig) {
        PictureUrlBig = pictureUrlBig;
    }

    public String getThumbPics() {
        return thumbPics;
    }

    public void setThumbPics(String thumbPics) {
        this.thumbPics = thumbPics;
    }
}
