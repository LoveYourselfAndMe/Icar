package com.cheyifu.icar.net;

import com.cheyifu.icar.base.BaseBean;
import com.cheyifu.icar.global.Constants;
import com.cheyifu.icar.login.model.LoginBean;
import com.cheyifu.icar.tabHome.model.CarMessageBean;
import com.cheyifu.icar.tabHome.model.CurrentBean;
import com.cheyifu.icar.tabHome.model.StopBean;
import com.cheyifu.icar.tabHome.model.imageBean;
import com.cheyifu.icar.tabMy.bean.InfoBean;
import com.cheyifu.icar.tabPay.bean.orderBean;
import com.cheyifu.icar.tabPay.bean.payFeeBean;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2017/2/20.
 */
public interface APIService {
    /**
     * 获取验证码
     * @param map
     * @return
     */
    @Headers("Cache-Control: public, max-age=3600")
    @FormUrlEncoded
    @POST(Constants.GET_CODE)
    Call<BaseBean> getAuthCode(@FieldMap Map<String, Object> map);
    /**
     * 登录
     * @param map
     * @return
     */
    @Headers("Cache-Control: public, max-age=3600")
    @FormUrlEncoded
    @POST(Constants.USER_LOGIN)
    Call<LoginBean> login(@FieldMap Map<String, String> map);
    /**
     * 退出登录
     * @param map
     * @return
     */
    @Headers("Cache-Control: public, max-age=3600")
    @FormUrlEncoded
    @POST(Constants.USER_EXIT)
    Call<BaseBean> exit(@FieldMap Map<String, String> map);
    /**
     * 意见反馈
     * @param map
     * @return
     */
    @Headers("Cache-Control: public, max-age=3600")
    @FormUrlEncoded
    @POST(Constants.IDEAR_BACK)
    Call<BaseBean> idearBack(@FieldMap Map<String, String> map);
    /**
     * 个人信息
     * @param map
     * @return
     */
    @Headers("Cache-Control: public, max-age=3600")
    @FormUrlEncoded
    @POST(Constants.GET_INFO)
    Call<InfoBean> getInfo(@FieldMap Map<String, String> map);

    /**
     * 添加车辆
     * @param map
     * @return
     */
    @Headers("Cache-Control: public, max-age=3600")
    @FormUrlEncoded
    @POST(Constants.ADD_CAR)
    Call<BaseBean> addCarCode(@FieldMap Map<String, String> map);
    /**
     * 查询车辆
     * @param map
     * @return
     */
    @Headers("Cache-Control: public, max-age=3600")
    @FormUrlEncoded
    @POST(Constants.QUREY_CAR)
    Call<CarMessageBean> queryCar(@FieldMap Map<String, String> map);
    /**
     * 正在进行车辆
     * @param map
     * @return
     */
    @Headers("Cache-Control: public, max-age=3600")
    @FormUrlEncoded
    @POST(Constants.GET_CURRENT)
    Call<CurrentBean> getCurrent(@FieldMap Map<String, String> map);
    /**
     * 停车记录
     * @param map
     * @return
     */
    @Headers("Cache-Control: public, max-age=3600")
    @FormUrlEncoded
    @POST(Constants.GET_HISTORY)
    Call<StopBean> getHistory(@FieldMap Map<String, Object> map);
    /**
     * 车辆认证
     * @param map
     * @return
     */
    @Headers("Cache-Control: public, max-age=3600")
    @FormUrlEncoded
    @POST(Constants.REN_ZHENG)
    Call<BaseBean> renzheng(@FieldMap Map<String, String> map);

    /**
     * 支付界面
     * @param map
     * @return
     */
    @Headers("Cache-Control: public, max-age=3600")
    @FormUrlEncoded
    @POST(Constants.GET_FEE)
    Call<payFeeBean> payFee(@FieldMap Map<String, String> map);

    /**
     * 微信下单界面
     * @param map
     * @return
     */
    @Headers("Cache-Control: public, max-age=3600")
    @FormUrlEncoded
    @POST(Constants.UNIFIEDORDER)
    Call<orderBean> payFeeOrder(@FieldMap Map<String, String> map);
    /**
     * 支付宝下单界面
     * @param map
     * @return
     */
    @Headers("Cache-Control: public, max-age=3600")
    @FormUrlEncoded
    @POST(Constants.ZHI_FU_BAO)
    Call<orderBean> payFeeOrder_ALI(@FieldMap Map<String, String> map);
    /**
     * 支付宝下单查询结果界面
     * @param map
     * @return
     */
    @Headers("Cache-Control: public, max-age=3600")
    @FormUrlEncoded
    @POST(Constants.ZHI_FU_BAO_QUERY)
    Call<orderBean> payFeeOrder_ALI_query(@FieldMap Map<String, String> map);
    /**
     * 违章查询
     * @param map
     * @return
     */
    @Headers("Cache-Control: public, max-age=3600")
    @FormUrlEncoded
    @POST(Constants.violation_query)
    Call<BaseBean> violation_query(@FieldMap Map<String, String> map);
    /**
     *获得图片
     * @param map
     * @return
     */
    @Headers("Cache-Control: public, max-age=3600")
    @FormUrlEncoded
    @POST(Constants.images)
    Call<imageBean> iamges(@FieldMap Map<String, String> map);



}
