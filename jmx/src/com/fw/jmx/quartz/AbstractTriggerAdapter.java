package com.fw.jmx.quartz;

import java.util.Date;

import org.quartz.Trigger;

public abstract class AbstractTriggerAdapter implements FWTrigger {
    public abstract Trigger getTrigger();

    public String getCalendarName() {
        return getTrigger().getCalendarName();
    }

    public String getDescription() {
        return getTrigger().getDescription();
    }

    public Date getEndTime() {
        return getTrigger().getEndTime();
    }

    public Date getFinalFireTime() {
        return getTrigger().getFinalFireTime();
    }

    public String getFireInstanceId() {
        return getTrigger().getFireInstanceId();
    }

    public String getFullJobName() {
        return getTrigger().getFullJobName();
    }

    public String getFullName() {
        return getTrigger().getFullName();
    }

    public String getGroup() {
        return getTrigger().getGroup();
    }

    public String getJobGroup() {
        return getTrigger().getJobGroup();
    }

    public String getJobName() {
        return getTrigger().getJobName();
    }

    public int getMisfireInstruction() {
        return getTrigger().getMisfireInstruction();
    }

    public String getName() {
        return getTrigger().getName();
    }

    public Date getNextFireTime() {
        return getTrigger().getNextFireTime();
    }

    public Date getPreviousFireTime() {
        return getTrigger().getPreviousFireTime();
    }

    public int getPriority() {
        return getTrigger().getPriority();
    }

    public Date getStartTime() {
        return getTrigger().getStartTime();
    }
}
