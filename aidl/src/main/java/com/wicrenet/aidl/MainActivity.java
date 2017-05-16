package com.wicrenet.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;

import com.jaydenxiao.common.commonutils.LogUtil;

public class MainActivity extends Activity {
    private IBookManager bookManager;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                LogUtil.d(((Book)msg.obj).getBookName());
            }
        }
    };

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IBookManager iBookManager = IBookManager.Stub.asInterface(iBinder);
            bookManager = iBookManager;
            try {
                iBookManager.add(new Book("Linus情景分析", 3));
                LogUtil.d(iBookManager.getBookList().toString());

                iBookManager.registerListener(iOnNewBookArriveListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    private IOnNewBookArriveListener iOnNewBookArriveListener = new IOnNewBookArriveListener.Stub() {
        @Override
        public void onNewBookArrive(Book book) throws RemoteException {
            handler.obtainMessage(0, book).sendToTarget();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, BookManagerService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onDestroy() {
        if (bookManager != null && bookManager.asBinder().isBinderAlive()) {
            try {
                bookManager.unRegisterListener(iOnNewBookArriveListener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        unbindService(serviceConnection);
        super.onDestroy();
    }
}
