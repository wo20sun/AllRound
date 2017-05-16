// IOnNewBookArriveListener.aidl
package com.wicrenet.aidl;

// Declare any non-default types here with import statements
import com.wicrenet.aidl.Book;

interface IOnNewBookArriveListener {

    void onNewBookArrive(in Book book);
}
