package com.fw.jmx.quartz;

import javax.management.DynamicMBean;
import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;

public class SimpleTriggerMBean extends AbstractTriggerMBean {
    private static Log log = LogFactory.getLog(SimpleTriggerMBean.class);

    private DynamicMBean dynamicMBean;

    public SimpleTriggerMBean(Scheduler scheduler, SimpleTrigger trigger) {
        super(scheduler, trigger);

        SimpleTriggerAdapter triggerAdapter = new SimpleTriggerAdapter(trigger);

        try {
            dynamicMBean = new StandardMBean(triggerAdapter,
                    FWSimpleTrigger.class);
        } catch (NotCompliantMBeanException e) {
            log.error("the trigger does not follow JMX design patterns"
                    + " for SimpleTrigger Interface", e);
        }
    }

    public DynamicMBean getDynamicMBean() {
        return dynamicMBean;
    }
}