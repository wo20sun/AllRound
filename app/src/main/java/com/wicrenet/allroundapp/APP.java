package com.wicrenet.allroundapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.DownloadManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.wicrenet.allroundapp.sp.MyConfiguration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Stack;


/**
 * @describe 全局和APP相关.
 */
public class APP {

    /**
     * 单例模式实现
     **/
    private static class SingletonHolder {

        private static final APP mInstance = new APP();
    }



    private Handler mHandler;
    private Activity mCurActivity;

    // Activity栈
    private static Stack<Activity> activityStack;
    /**
     * 得到APP Single Instance
     *
     * @return
     */
    public static APP getInstance() {
        return SingletonHolder.mInstance;
    }

    private APP() {
        networkInit();
    }

    public void startupApp() {

        // 重设ImageLoader
//        YitengImageLoader.getInstance().reset();
    }


    /**
     * getHandler:得到全局Handler. <br/>
     *
     * @return
     * @author wangheng
     */
    public synchronized Handler getHandler() {
        if (null == mHandler) {
            mHandler = new Handler();
        }
        return mHandler;
    }

    private void networkInit() {
//        YitengImageLoader.getInstance().init();

//        mDefaultRequestQueue = DefaultRequestQueueFactory.create(MyApplication.getContext());
//        mDefaultRequestQueue.start();
//
//        netWork(mDefaultRequestQueue).settings().setAsDefault().done();
    }

    /**
     * 得到Resources
     *
     * @return
     */
    public Resources getResources() {
        return MyApplication.getContext().getResources();
    }

    /**
     * getString:得到字符串资源. <br/>
     *
     * @param stringId
     * @return
     * @author wangheng
     */
    public String getString(int stringId) {
        return getResources().getString(stringId);
    }

    /**
     * 设置userId
     *
     * @param uid
     * @author yedr
     */
    public void setUid(String uid) {
        if (TextUtils.isEmpty(uid))
            return;

        // DebugLog.w("CuliuStat", "setUid-->" + uid + "; currentUid-->" + getUid());
        if (uid.equals(getUid()))
            return;

        // save to Local cache
        MyConfiguration.getInstance().setUid(MyApplication.getContext(), uid);
    }

    /**
     * 获取userId
     *
     * @return
     * @author yedr
     */
    public String getUid() {
        String uid = "0";

        // Return Local cache
        uid = MyConfiguration.getInstance().getUid(MyApplication.getContext());
        if (null != uid)
            return uid;

        // Return Default value
        return uid;
    }


    public Activity getCurActivity() {
        return mCurActivity;
    }


    public void setCurActivity(Activity act) {
        mCurActivity = act;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        if (activityStack.size() > 0) {
            Activity activity = activityStack.lastElement();
            finishActivity(activity);
        }

    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0; i < activityStack.size(); i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public int getActivitySize() {
        return activityStack.size();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
        }
    }

    public String getClientId(Context context) {
        TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String szImei = TelephonyMgr.getDeviceId();

        String m_szDevIDShort = "35" + //we make this look like a valid IMEI

                Build.BOARD.length() % 10 +
                Build.BRAND.length() % 10 +
                Build.CPU_ABI.length() % 10 +
                Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 +
                Build.HOST.length() % 10 +
                Build.ID.length() % 10 +
                Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 +
                Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 +
                Build.TYPE.length() % 10 +
                Build.USER.length() % 10; //13 digits
        String m_szAndroidID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        String m_szWLANMAC = wm.getConnectionInfo().getMacAddress();

        BluetoothAdapter m_BluetoothAdapter = null; // Local Bluetooth adapter
        m_BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        String m_szBTMAC = m_BluetoothAdapter.getAddress();

        String m_szLongID = szImei + m_szDevIDShort
                + m_szAndroidID + m_szWLANMAC + m_szBTMAC;
        // compute md5
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        m.update(m_szLongID.getBytes(), 0, m_szLongID.length());
        // get md5 bytes
        byte p_md5Data[] = m.digest();
        // create a hex string
        String m_szUniqueID = new String();
        for (int i = 0; i < p_md5Data.length; i++) {
            int b = (0xFF & p_md5Data[i]);
            // if it is a single digit, make sure it have 0 in front (proper padding)
            if (b <= 0xF)
                m_szUniqueID += "0";
            // add number to string
            m_szUniqueID += Integer.toHexString(b);
        }
        // hex string to uppercase
        m_szUniqueID = m_szUniqueID.toUpperCase();

        return m_szUniqueID;
    }

    /**
     * 程序是否在前台运行
     *
     * @return
     */
    public boolean isAppOnForeground(Context context) {
        // Returns a list of application processes that are running on the  
        // device  

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = context.getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.  
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }

}
