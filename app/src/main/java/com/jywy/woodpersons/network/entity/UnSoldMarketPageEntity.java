package com.jywy.woodpersons.network.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 高 on 2017/3/27.
 */
public class UnSoldMarketPageEntity {

    @SerializedName("pagecount")
    private String pageCount;

    @SerializedName("pagenum")
    private String pageNum;

    @SerializedName("rowcount")
    private String rowCount;


    public String getPageCount() {
        return pageCount;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getRowCount() {
        return rowCount;
    }

    public void setRowCount(String rowCount) {
        this.rowCount = rowCount;
    }


}
