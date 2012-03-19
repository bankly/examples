package com.fw.jmx;

public interface ResourceBundleServiceMBean {
    public String getString(String key);

    public String getBaseName();

    public void setBaseName(String baseName);
}
