package com.cheyifu.icar.tabHome.model;

import com.cheyifu.icar.base.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 */
public class CurrentBean extends BaseBean {
    private String count;
    private List<RecordsBean> records;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<RecordsBean> getRecords() {
        return records;
    }

    public void setRecords(List<RecordsBean> records) {
        this.records = records;
    }
}
