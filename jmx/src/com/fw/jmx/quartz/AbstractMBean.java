package com.fw.jmx.quartz;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.DynamicMBean;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanException;
import javax.management.MBeanInfo;
import javax.management.MBeanRegistration;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.ReflectionException;

public abstract class AbstractMBean implements DynamicMBean, MBeanRegistration {

    private MBeanServer mbeanServer;
    private String objectName;
    
    protected MBeanServer getMbeanServer() {
        return mbeanServer;
    }

    protected String getObjectName() {
        return objectName;
    }

    public void postDeregister() {
    }

    public void postRegister(Boolean registrationDone) {
    }

    public void preDeregister() throws Exception {
    }

    public ObjectName preRegister(MBeanServer server, ObjectName name)
            throws Exception {
        this.mbeanServer = server;
        this.objectName = name.toString();
        
        return name;
    }

    public Object getAttribute(String attribute)
            throws AttributeNotFoundException, MBeanException,
            ReflectionException {
        return getDynamicMBean().getAttribute(attribute);
    }

    public AttributeList getAttributes(String[] attributes) {
        return getDynamicMBean().getAttributes(attributes);
    }

    public MBeanInfo getMBeanInfo() {
        return getDynamicMBean().getMBeanInfo();
    }

    public Object invoke(String actionName, Object[] params, String[] signature)
            throws MBeanException, ReflectionException {
        return getDynamicMBean().invoke(actionName, params, signature);
    }

    public void setAttribute(Attribute attribute)
            throws AttributeNotFoundException, InvalidAttributeValueException,
            MBeanException, ReflectionException {
        // scheduler.rescheduleJob(trigger.getName(), trigger.getGroup(),
        // trigger);
        getDynamicMBean().setAttribute(attribute);
    }

    public AttributeList setAttributes(AttributeList attributes) {
        // scheduler.rescheduleJob(trigger.getName(), trigger.getGroup(),
        // trigger);
        return getDynamicMBean().setAttributes(attributes);
    }

    public abstract DynamicMBean getDynamicMBean();
}
