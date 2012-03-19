package com.fw.jmx.quartz;

import org.quartz.Trigger;

public interface FWJobDetail {

    /**
     * <p>
     * Get the name of this <code>Job</code>.
     * </p>
     */
    public String getName();

    /**
     * <p>
     * Get the group of this <code>Job</code>.
     * </p>
     */
    public String getGroup();

    /**
     * <p>
     * Returns the 'full name' of the <code>JobDetail</code> in the format
     * "group.name".
     * </p>
     */
    public String getFullName();

    /**
     * <p>
     * Return the description given to the <code>Job</code> instance by its
     * creator (if any).
     * </p>
     * 
     * @return null if no description was set.
     */
    public String getDescription();

    /**
     * <p>
     * Get the class name of <code>Job</code> that will be executed.
     * </p>
     */
    public String getJobClass();
    
    /**
     * <p>
     * Whether or not the <code>Job</code> should not be persisted in the
     * <code>{@link org.quartz.spi.JobStore}</code> for re-use after program
     * restarts.
     * </p>
     * 
     * <p>
     * If not explicitly set, the default value is <code>false</code>.
     * </p>
     * 
     * @return <code>true</code> if the <code>Job</code> should be garbage
     *         collected along with the <code>{@link FWScheduler}</code>.
     */
    public boolean isVolatile();

    /**
     * <p>
     * Whether or not the <code>Job</code> should remain stored after it is
     * orphaned (no <code>{@link Trigger}s</code> point to it).
     * </p>
     * 
     * <p>
     * If not explicitly set, the default value is <code>false</code>.
     * </p>
     * 
     * @return <code>true</code> if the Job should remain persisted after
     *         being orphaned.
     */
    public boolean isDurable();

    /**
     * <p>
     * Whether or not the <code>Job</code> implements the interface <code>{@link StatefulJob}</code>.
     * </p>
     */
    public boolean isStateful();

}