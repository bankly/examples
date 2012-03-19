package com.fw.jmx.quartz;

import java.util.Date;

import org.quartz.Calendar;

public interface FWTrigger {

    /**
     * <p>
     * Get the name of this <code>Trigger</code>.
     * </p>
     */
    public String getName();

    /**
     * <p>
     * Get the group of this <code>Trigger</code>.
     * </p>
     */
    public String getGroup();

    /**
     * <p>
     * Get the name of the associated <code>{@link org.quartz.JobDetail}</code>.
     * </p>
     */
    public String getJobName();

    /**
     * <p>
     * Get the name of the associated <code>{@link org.quartz.JobDetail}</code>'s
     * group.
     * </p>
     */
    public String getJobGroup();

    /**
     * <p>
     * Returns the 'full name' of the <code>Trigger</code> in the format
     * "group.name".
     * </p>
     */
    public String getFullName();

    /**
     * <p>
     * Returns the 'full name' of the <code>Job</code> that the <code>Trigger</code>
     * points to, in the format "group.name".
     * </p>
     */
    public String getFullJobName();

    /**
     * <p>
     * Return the description given to the <code>Trigger</code> instance by
     * its creator (if any).
     * </p>
     * 
     * @return null if no description was set.
     */
    public String getDescription();

    /**
     * <p>
     * Get the name of the <code>{@link Calendar}</code> associated with this
     * Trigger.
     * </p>
     * 
     * @return <code>null</code> if there is no associated Calendar.
     */
    public String getCalendarName();

    /**
     * The priority of a <code>Trigger</code> acts as a tiebreaker such that if 
     * two <code>Trigger</code>s have the same scheduled fire time, then the
     * one with the higher priority will get first access to a worker
     * thread.
     * 
     * <p>
     * If not explicitly set, the default value is <code>5</code>.
     * </p>
     * 
     * @see #DEFAULT_PRIORITY
     */
    public int getPriority();

    /**
     * <p>
     * Get the time at which the <code>Trigger</code> should occur.
     * </p>
     */
    public Date getStartTime();

    /**
     * <p>
     * Get the time at which the <code>Trigger</code> should quit repeating -
     * even if an assigned 'repeatCount' isn't yet satisfied.
     * </p>
     * 
     * @see #getFinalFireTime()
     */
    public Date getEndTime();

    /**
     * <p>
     * Returns the next time at which the <code>Trigger</code> will fire. If
     * the trigger will not fire again, <code>null</code> will be returned.
     * The value returned is not guaranteed to be valid until after the <code>Trigger</code>
     * has been added to the scheduler.
     * </p>
     */
    public Date getNextFireTime();

    /**
     * <p>
     * Returns the previous time at which the <code>Trigger</code> will fire.
     * If the trigger has not yet fired, <code>null</code> will be returned.
     */
    public Date getPreviousFireTime();

    /**
     * <p>
     * Returns the last time at which the <code>Trigger</code> will fire, if
     * the Trigger will repeat indefinitely, null will be returned.
     * </p>
     * 
     * <p>
     * Note that the return time *may* be in the past.
     * </p>
     */
    public Date getFinalFireTime();

    /**
     * <p>
     * Get the instruction the <code>Scheduler</code> should be given for
     * handling misfire situations for this <code>Trigger</code>- the
     * concrete <code>Trigger</code> type that you are using will have
     * defined a set of additional <code>MISFIRE_INSTRUCTION_XXX</code>
     * constants that may be passed to this method.
     * </p>
     * 
     * <p>
     * If not explicitly set, the default value is <code>MISFIRE_INSTRUCTION_SMART_POLICY</code>.
     * </p>
     * 
     * @see #MISFIRE_INSTRUCTION_SMART_POLICY
     * @see #updateAfterMisfire(Calendar)
     * @see FWSimpleTrigger
     * @see FWCronTrigger
     */
    public int getMisfireInstruction();

    /**
     * <p>
     * This method should not be used by the Quartz client.
     * </p>
     */
    public String getFireInstanceId();

}