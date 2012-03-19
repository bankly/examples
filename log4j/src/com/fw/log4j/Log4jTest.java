package com.fw.log4j;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.NDC;

public class Log4jTest {
    public static void main(String[] args) {
        for (int i = 0; i <= 4; i++) {
            Thread t = new Thread(new Log4jRunnable(i));
            t.start();
        }
    }
}

class Log4jRunnable implements Runnable {
    private static Log log = LogFactory.getLog(Log4jRunnable.class);
    private int index;

    public Log4jRunnable(int index) {
        this.index = index;
    }

    @Override
    public void run() {
        NDC.push(Thread.currentThread().getName());        
        log.info("thread " + index + " start");
 
        try {
            Thread.sleep((long)(Math.random() * 100));
        } catch (InterruptedException e) {
        }
        
        log.info("thread " + index + " end");
        NDC.pop();
    }
}
