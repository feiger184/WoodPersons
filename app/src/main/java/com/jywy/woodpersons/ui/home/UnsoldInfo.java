package com.jywy.woodpersons.ui.home;

import java.io.Serializable;

/**
 * Created by ren on 2017/3/27.
 */

public class UnsoldInfo implements Serializable {
//    public String avatarUrl; //明显头像的Url
//    public String name;  //明显的名字
//    public int imgHeight;  //头像图片的高度

    public String drawableUrl;//未售产品图片
    public String productMes;//未售产品信息
    public String productType;//未售产品类型
    public String productSite;//未售产品地点
    public String productPhone;//未售产品电话
    public String productCdKey;//未售产品cdkey
}
