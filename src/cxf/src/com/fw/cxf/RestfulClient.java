package com.fw.cxf;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

public class RestfulClient {
    public static void main(String[] args) {
        String url = "http://localhost:8080";
        List<Object> providers = new ArrayList<Object>();
        providers.add(new ClientExceptionMapper());

        Calculator proxy = JAXRSClientFactory.create(url, Calculator.class, providers);
        System.out.println(proxy.divide(4, 2));
        System.out.println(proxy.divide(4, 0));
    }
}
