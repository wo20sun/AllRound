package com.wicrenet.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import com.jaydenxiao.common.commonutils.LogUtil;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class BookManagerService extends Service {

    private CopyOnWriteArrayList<Book> list = new CopyOnWriteArrayList();

    private RemoteCallbackList<IOnNewBookArriveListener>  listenerList = new RemoteCallbackList<>();//用它管理才能正确的取消监听

    private AtomicBoolean atomicBoolean = new AtomicBoolean(false);


    private Binder binder = new IBookManager.Stub() {

        @Override
        public List<Book> getBookList() throws RemoteException {
            return list;
        }

        @Override
        public void add(Book book) throws RemoteException {
            list.add(book);
        }

        @Override
        public void registerListener(IOnNewBookArriveListener listener) throws RemoteException {
            listenerList.register(listener);
        }

        @Override
        public void unRegisterListener(IOnNewBookArriveListener listener) throws RemoteException {
            listenerList.unregister(listener);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        list.add(new Book("Android源码设计模式分析", 1));
        list.add(new Book("Android开发艺术探索", 2));

        new Thread(new Runnable() {
            @Override
            public void run() {
                {
                    try {
                        while (!atomicBoolean.get()) {

                            Thread.sleep(5000);

                            int bookId = list.size() + 1;
                            String bookName = "book" + bookId;
                            Book book = new Book(bookName, bookId);

                            list.add(book);
                            int size = listenerList.beginBroadcast();
                            LogUtil.d("size:"+size);
                            for (int i = 0; i < size; i++) {
                                listenerList.getBroadcastItem(i).onNewBookArrive(book);
                            }
                            listenerList.finishBroadcast();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;
    }

    @Override
    public void onDestroy() {
        atomicBoolean.set(true);
        super.onDestroy();
    }
}
