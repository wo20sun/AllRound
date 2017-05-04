package com.wicrenet.allroundapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import com.jaydenxiao.common.commonutils.LogUtil;
import com.jaydenxiao.common.commonutils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends FragmentActivity {

    @Bind(R.id.button)
    TextView button;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    }){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        test1();
//        test2();
        Subscription subscription = Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                LogUtil.w(Thread.currentThread().getName());
                subscriber.onNext(true);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Boolean aBoolean) {

            }
        });
    }

    private void test2() {
        Observable
                .create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        LogUtil.v("rx_call", Thread.currentThread().getName());

                        subscriber.onNext("dd");
                        subscriber.onCompleted();
                    }
                })

                .observeOn(Schedulers.newThread())    //新线程

                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        LogUtil.v("rx_map", Thread.currentThread().getName());
                        return s + "88";
                    }
                })

                .observeOn(Schedulers.io())      //io线程

                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        LogUtil.v("rx_filter", Thread.currentThread().getName());
                        return s != null;
                    }
                })

                .subscribeOn(Schedulers.io())     //定义事件产生线程：io线程
                .observeOn(AndroidSchedulers.mainThread())     //事件消费线程：主线程

                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        LogUtil.v("rx_subscribe", Thread.currentThread().getName());
                    }
                });
    }

    private void test1() {
        Student student0 = new Student();
        Course[] courses0 = {new Course("111"), new Course("222")};
        student0.setCourse(courses0);
        Student student1 = new Student();
        Course[] courses1 = {new Course("333"), new Course("444")};
        student1.setCourse(courses1);

        Student[] students = {student0, student1};

        Subscriber<Course> subscriber = new Subscriber<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                Log.d("eee", course.getName());
            }
        };
        Observable.from(students)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        Log.d("bbb", student.toString());
                        return Observable.from(student.getCourse());
                    }
                })
                .subscribe(subscriber);
    }

    @OnClick(R.id.button)
    public void onClick() {
        ToastUtil.showBottomtoast(this,"哈哈哈哈2222");
    }


    class Student {
        Course[] course;

        public Course[] getCourse() {
            return course;
        }

        public void setCourse(Course[] course) {
            this.course = course;
        }
    }

    class Course {
        String name;

        public Course(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
