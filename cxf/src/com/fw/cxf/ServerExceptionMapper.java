package com.fw.cxf;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.cxf.jaxrs.ext.MessageContext;

public class ServerExceptionMapper implements ExceptionMapper<CalculatorException> {
    @Context 
    private MessageContext mc; 

    @Override
    public Response toResponse(CalculatorException exception) {
        ResponseBuilder builder = Response.status(Status.INTERNAL_SERVER_ERROR);
        builder.entity(generateErrorMessage(exception));
        return builder.build();
    }

    private String generateErrorMessage(CalculatorException exception) {
        HttpServletRequest request = mc.getHttpServletRequest();
        String errorKey = exception.getMessage();
        
        Locale locale = request.getLocale();
        ResourceBundle rb = ResourceBundle.getBundle("com.fw.cxf.error", locale);
        String message = rb.getString(errorKey);

        return message;
    }
}
