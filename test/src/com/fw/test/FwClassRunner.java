package com.fw.test;

import java.lang.reflect.Method;

import org.junit.internal.runners.InitializationError;
import org.junit.internal.runners.JUnit4ClassRunner;
import org.junit.internal.runners.TestMethod;

public class FwClassRunner extends JUnit4ClassRunner {
    public FwClassRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }
    
    @Override
    protected void validate() throws InitializationError {
        FwMethodValidator methodValidator= new FwMethodValidator(getTestClass());
        methodValidator.validateMethodsForDefaultRunner();
        methodValidator.assertValid();
    }
    
    @Override
    protected TestMethod wrapMethod(Method method) {
        return new FwTestMethod(method, getTestClass());
    }
}
