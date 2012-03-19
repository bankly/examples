package com.fw.jmx;

import mx4j.tools.adaptor.http.HttpAdaptor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sun.jdmk.comm.HtmlAdaptorServer;

public class TestMain {
    public static void main(String[] args) {
        try {
            ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                    "appContext.xml");
            HttpAdaptor httpAdaptor = (HttpAdaptor) context
                    .getBean("httpAdaptor");
            httpAdaptor.start();

//            HtmlAdaptorServer htmlAdapter = (HtmlAdaptorServer) context
//                    .getBean("htmlAdapter");
//            htmlAdapter.start();

            context.registerShutdownHook();

            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
