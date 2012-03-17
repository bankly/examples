package com.fw.cxf;

public class CalculatorImpl implements Calculator {
    @Override
    public double divide(double a, double b) throws CalculatorException {
        if(b == 0) {
            throw new CalculatorException("error.illegalargument");
        }
        
        return a / b;
    }
}
