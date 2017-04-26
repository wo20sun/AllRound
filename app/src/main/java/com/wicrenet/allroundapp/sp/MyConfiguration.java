package com.wicrenet.allroundapp.sp;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;

import com.jaydenxiao.common.commonutils.LogUtil;


/**
 * 
 * @describe  系统全局配置参数. 
 * @author yedr
 * @date: 2014年12月20日 下午4:47:04 <br/>
 */
public class MyConfiguration {
    public final static String NULL_ID = "0";
    
/*   双重加锁懒汉式单列实现 
     private volatile static CuliuConfiguration mInstance;

    public static CuliuConfiguration getInstance() {
        if(null == mInstance) {
            synchronized(CuliuConfiguration.class) {
                if(null == mInstance)
                    mInstance = new CuliuConfiguration();
            }
        }
        return mInstance;
    }
    */
    
    /** 静态内部类单例模式实现 **/
    private static class SingletonHolder {  
        private static final MyConfiguration mInstance = new MyConfiguration();
    }
    
    public static MyConfiguration getInstance() {
        return SingletonHolder.mInstance;
    }

    private MyConfiguration() {
    }


    /**
     * 是否是第一次使用软件
     * @return
     */
    public boolean isFirstUse(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            int curVersion = info.versionCode;
            int lastVersion = SettingUtils.getSharedPreferences(context, "version", 0);
            if(curVersion > lastVersion) {
                // 如果当前版本大于上次版本，该版本属于第一次启动
                // 将当前版本写入preference中，则下次启动的时候，据此判断，不再为首次启动
                return true;
            } else {
                return false;
            }
        } catch(NameNotFoundException e) {
            LogUtil.i(e.getMessage());
        }

        return false;
    }

    /**
     * setAPPUsed:设置APP已经使用过了. <br/>
     * @author wangheng
     */
    public void setAPPUsed(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            int curVersion = info.versionCode;
            SettingUtils.setEditor(context, "version", curVersion);
        } catch(NameNotFoundException e) {
            LogUtil.i(e.getMessage());
        }
    }

    /**
     * isShortCutExists:ShortCut是否存在. <br/>
     * @author wangheng
     * @return
     */
    public boolean isShortCutExists(Context context) {
        return SettingUtils.getSharedPreferences(context, SPKey.KEY_SHORT_CUT_EXISTS, false);
    }

    /**
     * setShortCutExists:设置ShortCut是否存在. <br/>
     * @author wangheng
     * @param exists
     */
    public void setShortCutExists(Context context, boolean exists) {
        SettingUtils.setEditor(context, SPKey.KEY_SHORT_CUT_EXISTS, exists);
    }

    /**
     * setUid:设置UID. <br/>
     * @author wangheng
     * @param sex
     */
    public void setUid(Context context, String uid) {
        if(TextUtils.isEmpty(uid)) {
            return;
        }

        SettingUtils.setEditor(context, SPKey.KEY_UID, uid);
    }

    /**
     * getUid:得到UID. <br/>
     * @author wangheng
     * @return
     * @return
     */
    public String getUid(Context context) {
        return SettingUtils.getSharedPreferences(context, SPKey.KEY_UID, NULL_ID);
    }

    /**
     * setDevicedId:设置deviceId. 当deviceId已经存在，则不再改变
     * @author wangheng
     * @param sex
     */
    public void setDeviceId(Context context, String deviceId) {
        if(TextUtils.isEmpty(deviceId)) {
            return;
        }

        if( ! getDeviceId(context).equals(NULL_ID) && getDeviceId(context).equals(deviceId))
            return;

        LogUtil.w("CuliuStat", "NEW DeviceId-->" + deviceId);
        SettingUtils.setEditor(context, SPKey.KEY_DEVICE_ID, deviceId);
    }
    /**
     * getUid:得到DevicedId. <br/>
     * @author wangheng
     * @return
     * @return
     */
    public String getDeviceId(Context context) {
        return SettingUtils.getSharedPreferences(context, SPKey.KEY_DEVICE_ID, NULL_ID);
    }
    
    /**
     * 
     * 判断DeviceId是否非法
     * @author Administrator
     * @param context
     * @return
     */
    public boolean isDieviceIdUnvalid(Context context) {
        if (TextUtils.isEmpty(getDeviceId(context)) ||
                getDeviceId(context).equals(NULL_ID))
            return true;
        return false;
    }

    /** 获取是否有版本升级**/
    public boolean hasVersionUpdate(Context context){
    	return SettingUtils.getSharedPreferences(context, SPKey.KEY_HAS_VERSION_UPDATE, false);
    }
    
    /** 设置是否有版本升级**/
    public void setHasVersionUpdate(Context context, boolean hasUpdate){
    	SettingUtils.setEditor(context, SPKey.KEY_HAS_VERSION_UPDATE, hasUpdate);
    }

    /** 设置应用内存级别. */
    public void setAppMemeryLevel(Context context, int level){
        SettingUtils.setEditor(context, SPKey.KEY_APPMEMERY_LEVEL, level);
    }
    
    /** 得到应用内存级别. */
    public int getAppMemeryLevel(Context context){
        return SettingUtils.getSharedPreferences(context, SPKey.KEY_APPMEMERY_LEVEL, 0);
    }
}
