package com.cheyifu.icar.tabHome.model;

import com.cheyifu.icar.base.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 */
public class StopBean extends BaseBean {
    private String count;
    private String pageNum;
    private List<StopRecordsBean> records;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public List<StopRecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<StopRecordsBean> records) {
        this.records = records;
    }
}
