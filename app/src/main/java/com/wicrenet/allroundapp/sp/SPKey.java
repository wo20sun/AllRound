package com.wicrenet.allroundapp.sp;

/**
 * SharedPreferences文件中保存的Key都在这个地方定义
 * @author wangheng
 */
public class SPKey {

    /** 登录标示**/
    public static final String KEY_ACCESS_COOKIE = "access_cookie";

    public static final String KEY_USER_NICKNAME = "nickName";
    public static final String KEY_USER_NAME = "username";
    public static final String KEY_USER_PASSWORD = "userPassword";
    public static final String KEY_USER_ICON = "userIcon";
    public static final String KEY_USER_Tel = "userTel";
    public static final String KEY_USER_WID = "WID";

    public static final String KEY_IS_NOISE_REMIND = "is_noise_remind";//是否是声音提醒
    public static final String KEY_IS_VIBRATE_REMIND = "is_vibrate_remind";//是否是震动提醒

    public static final String KEY_IS_MESSAGE_PAGE = "is_message_page";//是否在消息页面
    public static final String KEY_MESSAGE_COUNT = "message_count";//消息个数
    public static final String KEY_ORDER_COUNT = "order_count";//订单个数



    /** 快捷方式存在的KEY **/
    public static final String KEY_SHORT_CUT_EXISTS = "shortCutExists";

    /** userId的KEY **/
    public static final String KEY_UID = "uid";

    /** sessionId的KEY **/
    public static final String KEY_DEVICE_ID = "deviceId";

    /** 有版本升级**/
    public static final String KEY_HAS_VERSION_UPDATE = "key_has_version_update";

    /** 上一次记录的内存级别 **/
    public static final String KEY_APPMEMERY_LEVEL = "AppMemeryLevel";


}
