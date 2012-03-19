package com.fw.cxf;

public class CalculatorException extends RuntimeException {
    private static final long serialVersionUID = -124215631592097290L;

    public CalculatorException() {
        super();
    }

    public CalculatorException(String message) {
        super(message);
    }

    public CalculatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public CalculatorException(Throwable cause) {
        super(cause);
    }
}
