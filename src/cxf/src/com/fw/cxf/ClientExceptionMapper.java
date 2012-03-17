package com.fw.cxf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.ResponseExceptionMapper;

public class ClientExceptionMapper implements ResponseExceptionMapper<CalculatorException> {
    public CalculatorException fromResponse(Response r) {
        InputStream is = (InputStream)r.getEntity();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuffer message = new StringBuffer();
        String line = null;
        
        try {
            while((line = br.readLine()) != null) {
                message.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return new CalculatorException(message.toString());
    }
}