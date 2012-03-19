package com.fw.jmx.quartz;

import java.util.TimeZone;

public interface FWCronTrigger extends FWTrigger {

    public String getCronExpression();

    /**
     * <p>
     * Returns the time zone for which the <code>cronExpression</code> of this
     * <code>CronTrigger</code> will be resolved.
     * </p>
     */
    public TimeZone getTimeZone();

    public String getExpressionSummary();

}