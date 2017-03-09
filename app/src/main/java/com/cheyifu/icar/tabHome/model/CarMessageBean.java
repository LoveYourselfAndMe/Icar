package com.cheyifu.icar.tabHome.model;

import com.cheyifu.icar.base.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/23.
 */
public class CarMessageBean extends BaseBean {
    public List<PlatesBean> plates;

    public List<PlatesBean> getPlates() {
        return plates;
    }

    public void setPlates(List<PlatesBean> plates) {
        this.plates = plates;
    }
}
