package com.fw.jmx.quartz;

import javax.management.DynamicMBean;
import javax.management.NotCompliantMBeanException;
import javax.management.StandardMBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.JobDetail;

public class JobDetailMBean extends AbstractMBean {
    private static Log log = LogFactory.getLog(JobDetailMBean.class);

    private DynamicMBean dynamicMBean;

    public JobDetailMBean(JobDetail jobDetail) {
        JobDetailAdapter jobDetailAdapter = new JobDetailAdapter(jobDetail);

        try {
            dynamicMBean = new StandardMBean(jobDetailAdapter,
                    FWJobDetail.class);
        } catch (NotCompliantMBeanException e) {
            log.error("the jobDetail does not follow JMX design patterns"
                    + " for JobDetail Interfaces", e);
        }
    }

    public DynamicMBean getDynamicMBean() {
        return dynamicMBean;
    }
}
