package com.cheyifu.icar.tabMy.bean;

import com.cheyifu.icar.base.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/3/8.
 */
public class InfoBean extends BaseBean {

    public String mobile  ;
    public String ebBalance  ;
    public String discount  ;
    public List<String> pic;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEbBalance() {
        return ebBalance;
    }

    public void setEbBalance(String ebBalance) {
        this.ebBalance = ebBalance;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public List<String> getPic() {
        return pic;
    }

    public void setPic(List<String> pic) {
        this.pic = pic;
    }
}
