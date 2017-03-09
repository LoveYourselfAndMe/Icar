package com.cheyifu.icar.tabHome.iView;

import com.cheyifu.icar.base.IUI;
import com.cheyifu.icar.tabHome.model.PlatesBean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/15.
 */
public interface IMessageView extends IUI {
    void responseList(List<PlatesBean> plates);
}
