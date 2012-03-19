package com.fw.weblogic;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import sun.net.www.protocol.http.Handler;

public class FwURLStreamHandlerFactory implements URLStreamHandlerFactory {
    public URLStreamHandler createURLStreamHandler(String protocol) {
        protocol = protocol.toLowerCase();
        if ("http".equals(protocol)) {
            return new Handler();
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println("override the default WebLogic Server HTTP stream handler");
        
        try {
            java.net.URL
                    .setURLStreamHandlerFactory(new FwURLStreamHandlerFactory());
        } catch (Exception e) {
            System.err
                    .println("Exception while setting the URLStreamHandlerFactory object"
                            + e);
        }
    }
}
