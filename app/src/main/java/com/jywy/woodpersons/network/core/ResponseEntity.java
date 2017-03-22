package com.jywy.woodpersons.network.core;

import com.google.gson.annotations.SerializedName;
import com.jywy.woodpersons.network.entity.Status;

/**
 * Created by 高 on 2017/3/21.
 */

// 响应的实体基类：为了防止直接实例化，所以做成抽象类
public abstract class ResponseEntity {


    @SerializedName("status")
    private Status mStatus;

    public Status getStatus() {
        return mStatus;
    }
}
