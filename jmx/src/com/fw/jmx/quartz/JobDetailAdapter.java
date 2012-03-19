package com.fw.jmx.quartz;

import org.quartz.JobDetail;

public class JobDetailAdapter implements FWJobDetail {
    private JobDetail jobDetail;

    public JobDetailAdapter(JobDetail jobDetail) {
        this.jobDetail = jobDetail;
    }

    public String getDescription() {
        return this.jobDetail.getDescription();
    }

    public String getFullName() {
        return this.jobDetail.getFullName();
    }

    public String getGroup() {
        return this.jobDetail.getGroup();
    }

    public String getJobClass() {
        return this.jobDetail.getJobClass().getName();
    }
    
    public String getName() {
        return this.jobDetail.getName();
    }

    public boolean isDurable() {
        return this.jobDetail.isDurable();
    }

    public boolean isStateful() {
        return this.jobDetail.isStateful();
    }

    public boolean isVolatile() {
        return this.jobDetail.isVolatile();
    }
}
