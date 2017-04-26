package com.wicrenet.allroundapp.global;

import android.net.Uri;

public class Constant {

	public static boolean DEBUG = true;//记得要改

	public static String BETA_URL = "http://www.ketongtx.com/wise-openfire/rs/";//测试
	public static String NORMAL_URL = "http://wise.wicrenet.com/wise-openfire/rs/";//正式

	public static final String BETA_DOMIN = "http://www.ketongtx.com/";
	public static final String NORMAL_DOMIN = "http://wise.wicrenet.com/";

	public static String NET_FAILURE_TIP = "请检查您的网络";

	/**
	 * msg变更通知RUI
	 */
	public static final Uri URI_MSG = Uri.parse("content://com.wise.msg");

	/**
	 * 通知聊天列表顺序的改变 （来消息时，设置置顶）
	 */
	public static final Uri URI_MSG_TOP = Uri.parse("content://com.wise.msg.top");

	/**
	 * 主机IP地址
	 */
	public static final String Host_Ip_Test = "test.ketongtx.com";
	public static final String Host_Ip_Normal = "im.wicrenet.com";
	/**
	 * domin 域名
	 */
	public static final String Host_Domin_Test = "iz11oqbz3uzz";
	public static final String Host_Domin_Normal = "iz11cldm868z";

	/**
	 * 登录xmpp的
	 */
	public static final String login_resource_test = "wise_server";
	public static final String login_resource_normal = "android";


	/**
	 * 微信分享的APPID
	 */
	public static final String WX_Share_AppID_Test = "wxe3eb97fe39b0861e";
	public static final String WX_Share_AppID_Normal = "wx33f07918bdf43e88";

	/**
	 * 下载APK的地址
	 */
	public static final String Apk_Address_Test = "http://ketongtx.com/resource/download/WiseSeller_v1.0.0_debug.apk";
	public static final String Apk_Address_Normal = "http://wise.wicrenet.com/resource/download/WiseSeller_v1.0.0_release.apk";


}

