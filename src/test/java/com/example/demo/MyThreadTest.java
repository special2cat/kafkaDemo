package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyThreadTest implements Runnable {

    private final Logger log = LoggerFactory.getLogger(MyThreadTest.class);

    public String str;

    public MyThreadTest(String str) {
        this.str = str;
    }

    @Override
    public void run() {
        System.out.println("sout"+this.str);
        log.info("log"+this.str);
    }


}
