package com.cheyifu.icar.tabHome.model;

import com.cheyifu.icar.base.BaseBean;

/**
 * Created by Administrator on 2017/3/3.
 */
public class PlatesBean extends BaseBean {
    private String plate;
    private int auth;

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }
}
