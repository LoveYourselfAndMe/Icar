package com.cheyifu.icar.global;



/**
 *
 * 常量池
 * 缓存路径，存储路径，常量
 */
public class Constants {

    /**注册的手机号码*/
    public static final String PHONE_NUM = "phone_num";
    public static final String DownloadAPK = "";
    public static   String phone;


    //我的界面储存的一些值

    public static String my_bitmap="";
    //微信id
    public static final String APP_ID="wx4806c87d56eb2304";
    public static final String SP_PHONE_NUM_KEY = "phone_num";//保存登陆时返回的key
    public static final String PUSSH = "open_close";//消息推送状态



    /**
     * 用户登录
     */
    public final static String USER_LOGIN = "login";
    /**
     * 退出登录
     */
    public final static String USER_EXIT= "logout";
    /**
     * 意见反馈
     */
    public final static String IDEAR_BACK= "feedback";
    /**
     * 个人信息
     */
    public final static String GET_INFO= "getInfo";
    /**
     *获取验证码
     */
    public final static String GET_CODE = "appRegSms";
    /**
     *添加车
     */
    public final static String ADD_CAR = "addCar";
    /**
    * 查询车辆
    * */
    public final static String QUREY_CAR = "getCars";
    /**
     * 正在进行车辆
     * */
    public final static String GET_CURRENT = "getInParking";
    /**
     * 正在进行车辆
     * */
    public final static String GET_HISTORY = "getInOutParking";
    /**
     * 车辆认证
     * */
    public final static String REN_ZHENG = "carAuth";
    /**
     * 锁车
     * */
    public final static String LOCK_NO = "carLockOrNo";
    /**
     * 缴费
     * */
    public final static String PAY_CAR = "getFree";
    /**
     * 获取车辆记录
     * */
    public final static String HISTTORY = "getInOutParking";
    /**
     * 获取停车费用界面
     * */
    public final static String GET_FEE = "getFee";
//微信
    public final static String UNIFIEDORDER = "wxpay/unifiedOrder";
    //支付宝
    public final static String ZHI_FU_BAO = "alipay/unifiedOrder";

    //支付宝查询结果界面
    public final static String ZHI_FU_BAO_QUERY = "alipay/queryOrder";
    //违章查询
    public final static String violation_query = "illegalQuery";
    //获得图片
    public final static String images = "getUrls";



}
