package com.fw.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.internal.runners.TestClass;
import org.junit.internal.runners.TestMethod;

public class FwTestMethod extends TestMethod {
    private final Method fMethod;

    public FwTestMethod(Method method, TestClass testClass) {
        super(method, testClass);
        fMethod = method;
    }

    @Override
    public void invoke(Object test) throws IllegalArgumentException,
            IllegalAccessException, InvocationTargetException {
        Class<?>[] types = fMethod.getParameterTypes();
        Object[] params = new Object[types.length];

        for (int i = 0; i < types.length; i++) {
            if (types[i].isPrimitive()) {
                if ("boolean".equals(types[i].getSimpleName())) {
                    params[i] = false;
                } else {
                    params[i] = 0;
                }
            } else {
                params[i] = null;
            }
        }

        fMethod.invoke(test, params);
    }
}
