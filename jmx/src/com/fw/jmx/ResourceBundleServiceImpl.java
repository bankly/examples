package com.fw.jmx;

import java.util.ResourceBundle;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;

@ManagedResource(description = "ResourceBundleService MBean")
public class ResourceBundleServiceImpl implements ResourceBundleService,
        InitializingBean {
    private String baseName;
    private ResourceBundle resourceBundle;

    @ManagedOperation(description="Gets a string for the given key")
    @ManagedOperationParameters({
      @ManagedOperationParameter(name = "key", 
                                 description = "the key for the desired string")})
    public String getString(String key) {
        return resourceBundle.getString(key);
    }

    public void afterPropertiesSet() throws Exception {
        resourceBundle = ResourceBundle.getBundle(baseName);
    }

    @ManagedAttribute(defaultValue="env")
    public String getBaseName() {
        return baseName;
    }

    @ManagedAttribute(description="the base name of the resource bundle")
    public void setBaseName(String baseName) throws Exception {
        this.baseName = baseName;
        afterPropertiesSet();
    }
}
