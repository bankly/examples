/* 
 * Copyright 2004-2005 OpenSymphony 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may obtain a copy 
 * of the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations 
 * under the License.
 * 
 */

/*
 * Previously Copyright (c) 2001-2004 James House
 */
package com.fw.jmx.quartz;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobListener;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

/**
 * <p>
 * This is the main interface of a Quartz Scheduler.
 * </p>
 * 
 * <p>
 * A <code>Scheduler</code> maintains a registery of
 * <code>{@link org.quartz.JobDetail}</code> s and
 * <code>{@link Trigger}</code>s. Once registered, the <code>Scheduler</code>
 * is responible for executing <code>Job</code> s when their associated
 * <code>Trigger</code> s fire (when their scheduled time arrives).
 * </p>
 * 
 * <p>
 * <code>Scheduler</code> instances are produced by a
 * <code>{@link SchedulerFactory}</code>. A scheduler that has already been
 * created/initialized can be found and used through the same factory that
 * produced it. After a <code>Scheduler</code> has been created, it is in
 * "stand-by" mode, and must have its <code>start()</code> method called
 * before it will fire any <code>Job</code>s.
 * </p>
 * 
 * <p>
 * <code>Job</code> s are to be created by the 'client program', by defining a
 * class that implements the <code>{@link org.quartz.Job}</code> interface.
 * <code>{@link JobDetail}</code> objects are then created (also by the
 * client) to define a individual instances of the <code>Job</code>.
 * <code>JobDetail</code> instances can then be registered with the
 * <code>Scheduler</code> via the <code>scheduleJob(JobDetail, Trigger)</code>
 * or <code>addJob(JobDetail, boolean)</code> method.
 * </p>
 * 
 * <p>
 * <code>Trigger</code> s can then be defined to fire individual
 * <code>Job</code> instances based on given schedules.
 * <code>SimpleTrigger</code> s are most useful for one-time firings, or
 * firing at an exact moment in time, with N repeats with a given delay between
 * them. <code>CronTrigger</code> s allow scheduling based on time of day, day
 * of week, day of month, and month of year.
 * </p>
 * 
 * <p>
 * <code>Job</code> s and <code>Trigger</code> s have a name and group
 * associated with them, which should uniquely identify them within a single
 * <code>{@link FWScheduler}</code>. The 'group' feature may be useful for
 * creating logical groupings or categorizations of <code>Jobs</code> s and
 * <code>Triggers</code>s. If you don't have need for assigning a group to a
 * given <code>Jobs</code> of <code>Triggers</code>, then you can use the
 * <code>DEFAULT_GROUP</code> constant defined on this interface.
 * </p>
 * 
 * <p>
 * Stored <code>Job</code> s can also be 'manually' triggered through the use
 * of the <code>triggerJob(String jobName, String jobGroup)</code> function.
 * </p>
 * 
 * <p>
 * Client programs may also be interested in the 'listener' interfaces that are
 * available from Quartz. The <code>{@link JobListener}</code> interface
 * provides notifications of <code>Job</code> executions. The
 * <code>{@link TriggerListener}</code> interface provides notifications of
 * <code>Trigger</code> firings. The <code>{@link SchedulerListener}</code>
 * interface provides notifications of <code>Scheduler</code> events and
 * errors.
 * </p>
 * 
 * <p>
 * The setup/configuration of a <code>Scheduler</code> instance is very
 * customizable. Please consult the documentation distributed with Quartz.
 * </p>
 * 
 * @see Job
 * @see JobDetail
 * @see Trigger
 * @see JobListener
 * @see TriggerListener
 * @see SchedulerListener
 * 
 * @author James House
 * @author Sharada Jambula
 */
public interface FWScheduler {

    /*
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * 
     * Interface.
     * 
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

    /**
     * <p>
     * Returns the name of the <code>Scheduler</code>.
     * </p>
     */
    String getSchedulerName() throws SchedulerException;

    /**
     * <p>
     * Returns the instance Id of the <code>Scheduler</code>.
     * </p>
     */
    String getSchedulerInstanceId() throws SchedulerException;

    // /////////////////////////////////////////////////////////////////////////
    // /
    // / Schedululer State Management Methods
    // /
    // /////////////////////////////////////////////////////////////////////////

    /**
     * <p>
     * Starts the <code>Scheduler</code>'s threads that fire
     * <code>{@link Trigger}s</code>. When a scheduler is first created it
     * is in "stand-by" mode, and will not fire triggers. The scheduler can also
     * be put into stand-by mode by calling the <code>standby()</code> method.
     * </p>
     * 
     * <p>
     * The misfire/recovery process will be started, if it is the initial call
     * to this method on this scheduler instance.
     * </p>
     * 
     * @throws SchedulerException
     *             if <code>shutdown()</code> has been called, or there is an
     *             error within the <code>Scheduler</code>.
     * 
     * @see #standby
     * @see #shutdown
     */
    void start() throws SchedulerException;

    /**
     * Whether the scheduler has been started.
     * 
     * <p>
     * Note: This only reflects whether <code>{@link #start()}</code> has ever
     * been called on this Scheduler, so it will return <code>true</code> even
     * if the <code>Scheduler</code> is currently in standby mode or has been
     * since shutdown.
     * </p>
     * 
     * @see #start()
     * @see #isShutdown()
     * @see #isInStandbyMode()
     */
    boolean isStarted() throws SchedulerException;

    /**
     * <p>
     * Temporarily halts the <code>Scheduler</code>'s firing of
     * <code>{@link Trigger}s</code>.
     * </p>
     * 
     * <p>
     * When <code>start()</code> is called (to bring the scheduler out of
     * stand-by mode), trigger misfire instructions will NOT be applied during
     * the execution of the <code>start()</code> method - any misfires will be
     * detected immediately afterward (by the <code>JobStore</code>'s normal
     * process).
     * </p>
     * 
     * <p>
     * The scheduler is not destroyed, and can be re-started at any time.
     * </p>
     * 
     * @see #start()
     * @see #pauseAll()
     */
    void standby() throws SchedulerException;

    /**
     * <p>
     * Reports whether the <code>Scheduler</code> is in stand-by mode.
     * </p>
     * 
     * @see #standby()
     * @see #start()
     */
    boolean isInStandbyMode() throws SchedulerException;

    /**
     * <p>
     * Halts the <code>Scheduler</code>'s firing of
     * <code>{@link Trigger}s</code>, and cleans up all resources associated
     * with the Scheduler. Equivalent to <code>shutdown(false)</code>.
     * </p>
     * 
     * <p>
     * The scheduler cannot be re-started.
     * </p>
     * 
     * @see #shutdown(boolean)
     */
    void shutdown() throws SchedulerException;

    /**
     * <p>
     * Halts the <code>Scheduler</code>'s firing of
     * <code>{@link Trigger}s</code>, and cleans up all resources associated
     * with the Scheduler.
     * </p>
     * 
     * <p>
     * The scheduler cannot be re-started.
     * </p>
     * 
     * @param waitForJobsToComplete
     *            if <code>true</code> the scheduler will not allow this
     *            method to return until all currently executing jobs have
     *            completed.
     * 
     * @see #shutdown
     */
    void shutdown(boolean waitForJobsToComplete) throws SchedulerException;

    /**
     * <p>
     * Reports whether the <code>Scheduler</code> has been shutdown.
     * </p>
     */
    boolean isShutdown() throws SchedulerException;

}
