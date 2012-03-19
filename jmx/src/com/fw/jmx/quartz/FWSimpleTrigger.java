package com.fw.jmx.quartz;


public interface FWSimpleTrigger extends FWTrigger {

    /**
     * <p>
     * Get the the number of times the <code>SimpleTrigger</code> should
     * repeat, after which it will be automatically deleted.
     * </p>
     * 
     * @see #REPEAT_INDEFINITELY
     */
    public int getRepeatCount();

    /**
     * <p>
     * Get the the time interval (in milliseconds) at which the
     * <code>SimpleTrigger</code> should repeat.
     * </p>
     */
    public long getRepeatInterval();

    /**
     * <p>
     * Get the number of times the <code>SimpleTrigger</code> has already
     * fired.
     * </p>
     */
    public int getTimesTriggered();
}