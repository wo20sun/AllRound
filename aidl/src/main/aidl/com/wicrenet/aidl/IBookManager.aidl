// IBookManager.aidl
package com.wicrenet.aidl;

// Declare any non-default types here with import statements
import com.wicrenet.aidl.Book;//虽然在同一个包下，但是必须这样引用
import com.wicrenet.aidl.IOnNewBookArriveListener;

interface IBookManager {
    List<Book> getBookList();

    void add(in Book book);//前面的标识符in（输入）必须出现，否则编译报错生成不了aidl对应的java文件  其他的还有out（输出）,inout（输入输出）

    void registerListener(IOnNewBookArriveListener listener);

    void unRegisterListener(IOnNewBookArriveListener listener);
}
