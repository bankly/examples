package com.fw.jmx.quartz;

import java.util.List;

import javax.management.DynamicMBean;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.StandardMBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;

public class SchedulerMBean extends AbstractMBean {
    private static Log log = LogFactory.getLog(SchedulerMBean.class);

    private Scheduler scheduler;
    private List<Trigger> triggers;
    private DynamicMBean dynamicMBean;

    public SchedulerMBean(Scheduler scheduler, List<Trigger> triggers) {
        this.scheduler = scheduler;
        this.triggers = triggers;

        SchedulerAdapter schedulerAdapter = new SchedulerAdapter(scheduler);

        try {
            dynamicMBean = new StandardMBean(schedulerAdapter,
                    FWScheduler.class);
        } catch (NotCompliantMBeanException e) {
            log.error("the scheduler does not follow JMX design patterns"
                    + " for Scheduler Interface", e);
        }
    }

    public DynamicMBean getDynamicMBean() {
        return this.dynamicMBean;
    }

    public void postRegister(Boolean registrationDone) {
        registerTriggers();
    }

    private void registerTriggers() {
        try {
            for (Trigger trigger : triggers) {
                ObjectName objectName = new ObjectName(getObjectName()
                        + ",tirgger=" + trigger.getName());

                DynamicMBean triggerMBean = null;
                if (trigger instanceof SimpleTrigger) {
                    triggerMBean = new SimpleTriggerMBean(this.scheduler,
                            (SimpleTrigger) trigger);
                } else if (trigger instanceof CronTrigger) {
                    triggerMBean = new CronTriggerMBean(this.scheduler,
                            (CronTrigger) trigger);
                } else {
                    throw new IllegalArgumentException("trigger : "
                            + trigger.getClass().getName()
                            + " does not implement Trigger interface");
                }

                getMbeanServer().registerMBean(triggerMBean, objectName);
            }
        } catch (Exception e) {
            log.warn("Could not add triggers", e);
        }
    }
}
