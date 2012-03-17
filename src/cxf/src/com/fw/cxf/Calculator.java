package com.fw.cxf;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/calculator")
public interface Calculator {
    @GET
    @Path("/divide/{a}/{b}")
    public double divide(@PathParam("a")double a, @PathParam("b")double b) 
        throws CalculatorException;
}
