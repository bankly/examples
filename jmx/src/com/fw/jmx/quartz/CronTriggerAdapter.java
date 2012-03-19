package com.fw.jmx.quartz;

import java.util.TimeZone;

import org.quartz.CronTrigger;
import org.quartz.Trigger;

public class CronTriggerAdapter extends AbstractTriggerAdapter implements
        FWCronTrigger {
    private CronTrigger trigger;

    public CronTriggerAdapter(CronTrigger trigger) {
        this.trigger = trigger;
    }

    public Trigger getTrigger() {
        return this.trigger;
    }

    public String getCronExpression() {
        return this.trigger.getCronExpression();
    }

    public String getExpressionSummary() {
        return this.trigger.getExpressionSummary();
    }

    public TimeZone getTimeZone() {
        return this.trigger.getTimeZone();
    }
}
