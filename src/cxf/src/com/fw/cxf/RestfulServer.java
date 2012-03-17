package com.fw.cxf;

import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.ResourceProvider;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

public class RestfulServer {
    public static void main(String[] args) {
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        ResourceProvider provider = new SingletonResourceProvider(new CalculatorImpl());
        sf.setResourceClasses(Calculator.class);
        sf.setResourceProvider(Calculator.class, provider);
        sf.setAddress("http://localhost:8080/");        
        sf.setProvider(new ServerExceptionMapper());
        
        sf.create();
    }
}
