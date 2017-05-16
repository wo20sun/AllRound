package com.wicrenet.allroundapp;

/**
 * Created by sunpeng on 17/5/10.
 */

public class Test {


    private Test() {

    }

    public synchronized static Test getTestInstance() {
        return TestHolder.test;
    }

    private static class TestHolder{
        private static final Test test = new Test();
    }

}
