package com.wicrenet.allroundapp.sp;

import android.content.Context;
import android.text.format.Time;

import static com.wicrenet.allroundapp.sp.SettingUtils.getSharedPreferences;

public class SPUtil {

    /**
     * 获取用户名
     *
     * @param context
     * @return
     */
    public static String getUsername(Context context) {
        return getSharedPreferences(context,SPKey.KEY_USER_NAME,"");
    }


    /**
     * 获取店铺LOGO
     *
     * @param context
     * @return
     */
    public static String getLogo(Context context) {
        return getSharedPreferences(context,SPKey.KEY_USER_ICON,"");
    }


    public static boolean getMessageIsChecked(Context context) {
        return getSharedPreferences(context,SPKey.KEY_IS_MESSAGE_PAGE,false);
    }

    /**
     * 如果tab点击在消息上面
     * @param context
     * @param isChecked
     */
    public static void setMessageChecked(Context context,boolean isChecked) {
        SettingUtils.setEditor(context,SPKey.KEY_IS_MESSAGE_PAGE,isChecked);
    }

    /**
     * 当前年 不是今天
     *
     * @param when
     * @return
     */
    public static boolean isThisYear(long when) {
        Time time = new Time();
        time.set(when);

        int thenYear = time.year;
//		int thenMonth = time.month;
        int thenMonthDay = time.monthDay;

        time.set(System.currentTimeMillis());
        return (thenYear == time.year) && (thenMonthDay != time.monthDay);
    }

}
