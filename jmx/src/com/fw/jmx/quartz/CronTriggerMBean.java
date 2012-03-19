package com.fw.jmx.quartz;

import javax.management.DynamicMBean;
import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;

public class CronTriggerMBean extends AbstractTriggerMBean {
    private static Log log = LogFactory.getLog(CronTriggerMBean.class);

    private DynamicMBean dynamicMBean;

    public CronTriggerMBean(Scheduler scheduler, CronTrigger trigger) {
        super(scheduler, trigger);

        CronTriggerAdapter triggerAdapter = new CronTriggerAdapter(trigger);

        try {
            dynamicMBean = new StandardMBean(triggerAdapter,
                    FWCronTrigger.class);
        } catch (NotCompliantMBeanException e) {
            log.error("the trigger does not follow JMX design patterns"
                    + " for CronTrigger Interfaces", e);
        }
    }

    public DynamicMBean getDynamicMBean() {
        return dynamicMBean;
    }
}