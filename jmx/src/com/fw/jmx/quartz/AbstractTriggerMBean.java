package com.fw.jmx.quartz;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.AttributeNotFoundException;
import javax.management.InvalidAttributeValueException;
import javax.management.MBeanException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;

public abstract class AbstractTriggerMBean extends AbstractMBean {
    private static Log log = LogFactory.getLog(AbstractTriggerMBean.class);

    private Scheduler scheduler;
    private Trigger trigger;

    public AbstractTriggerMBean(Scheduler scheduler, Trigger trigger) {
        this.scheduler = scheduler;
        this.trigger = trigger;
    }

    public void postRegister(Boolean registrationDone) {
        registerJogDetail();
    }

    /**
     * 這個 method 並不會被執行，因為目前的 trigger 都是唯獨的
     * @deprecated
     */
    public void setAttribute(Attribute attribute)
            throws AttributeNotFoundException, InvalidAttributeValueException,
            MBeanException, ReflectionException {
        try {
            log.debug("reschedule trigger : " + trigger.getName() + "/"
                    + trigger.getGroup());
            scheduler.rescheduleJob(trigger.getName(), trigger.getGroup(),
                    trigger);
        } catch (SchedulerException e) {
            log.error("reschedule job error", e);
        }

        super.setAttribute(attribute);
    }

    /**
     * 這個 method 並不會被執行，因為目前的 trigger 都是唯獨的
     * @deprecated
     */
    public AttributeList setAttributes(AttributeList attributes) {
        try {
            log.debug("reschedule trigger : " + trigger.getName() + "/"
                    + trigger.getGroup());
            scheduler.rescheduleJob(trigger.getName(), trigger.getGroup(),
                    trigger);
        } catch (SchedulerException e) {
            log.error("reschedule job error", e);
        }

        return super.setAttributes(attributes);
    }

    private void registerJogDetail() {
        try {
            JobDetail jobDetail = scheduler.getJobDetail(trigger.getJobName(),
                    trigger.getJobGroup());
            JobDetailMBean jobDetailMBean = new JobDetailMBean(jobDetail);
            ObjectName objectName = new ObjectName(getObjectName()
                    + ",jobDetail=" + jobDetail.getName());

            getMbeanServer().registerMBean(jobDetailMBean, objectName);
        } catch (Exception e) {
            log.warn("Could not add JobDetailMBean", e);
        }
    }
}