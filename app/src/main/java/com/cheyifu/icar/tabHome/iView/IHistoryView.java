package com.cheyifu.icar.tabHome.iView;

import com.cheyifu.icar.base.IUI;
import com.cheyifu.icar.tabHome.model.CurrentBean;
import com.cheyifu.icar.tabHome.model.StopBean;


/**
 * Created by Administrator on 2017/2/16.
 */
public interface IHistoryView extends IUI {
    void response(CurrentBean currentBeen);
    void refreshFinish();
    void responseStopHistory(StopBean bean);
}

