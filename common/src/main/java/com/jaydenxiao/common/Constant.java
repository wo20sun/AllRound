package com.jaydenxiao.common;

/**
 * Created by sunpeng on 17/2/13.
 */

public class Constant {

    public static boolean DEBUG = true;


    public static final String DOMIN = Constant.DEBUG ? Constant.BETA_DOMIN : Constant.NORMAL_DOMIN;

    public static String BETA_URL = "http://www.ketongtx.com/api/services/";//测试
    public static String NORMAL_URL = "http://wise.wicrenet.com/api/services/";//正式

    public static final String BETA_DOMIN = "http://www.ketongtx.com/";
    public static final String NORMAL_DOMIN = "http://wise.wicrenet.com/";

    /**
     * 下载APK的地址
     */
    public static final String Apk_Address_Test = "http://ketongtx.com/resource/download/VShop_v1.0.0_debug.apk";
    public static final String Apk_Address_Normal = "http://wise.wicrenet.com/resource/download/VShop_v1.0.0_release.apk";

    public static final String DEFAULT_ICON = "http://wise.wicrenet.com/minishop/images/default-shop-ico.png";

    public static final String EVENT_WELCOME_INIT_COMPLETED="EVENT_WELCOME_INIT_COMPLETED";//欢迎页初始化
    public static final String EVENT_UPDATE_ICON="EVENT_UPDATE_ICON";//更新头像
    public static final String EVENT_UPDATE_GOODS="EVENT_UPDATE_GOODS";//更新商品 删除分类中的商品等于删除自己小店的商品，所以要更新


    //分类页面状态
    public static final String OPERATE_CATEGORY_TYPE="OPERATE_CATEGORY_TYPE";//增加，修改分类
    public static final String OPERATE_CATEGORY_ADD="OPERATE_CATEGORY_ADD";//增加
    public static final String OPERATE_CATEGORY_UPDATE="OPERATE_CATEGORY_UPDATE";//修改

    //adapter
    public static final String ADAPTER_POSITION="ADAPTER_POSITION";
    public static final String ADAPTER_OBJECT="ADAPTER_OBJECT";

    //activity request code
    public static final int REQUEST_FIRST_CODE = 1;
    public static final int REQUEST_SECOND_CODE = 2;
    public static final int REQUEST_THIRD_CODE = 3;

    /**
     * 微信分享的APPID
     */
    public static final String WX_Share_AppID_Test = "wx21a2ba86ddd45b5d";
    public static final String WX_Share_AppID_Normal = "wx21a2ba86ddd45b5d";

    public static int ORDER_STATUS = 0;//订单状态
}
