package com.fw.jmx;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBean {
    private static Log log = LogFactory.getLog(MyBean.class);
    
    public void simpleRun() {
        log.info("simple : " + new Date());
    }
    
    public void cronRun() {
        log.info("cron : " + new Date());
    }
}
