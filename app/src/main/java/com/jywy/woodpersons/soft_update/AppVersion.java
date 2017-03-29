package com.jywy.woodpersons.soft_update;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 高 on 2017/3/29.
 */

public class AppVersion {

    @SerializedName("serverversion")
    private int serverVersion;
    @SerializedName("updateurl")
    private String updateUrl;

    public int getServerVersion() {
        return serverVersion;
    }

    public void setServerVersion(int serverVersion) {
        this.serverVersion = serverVersion;
    }

    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }



}
