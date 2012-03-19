package com.fw.jmx.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

public class SchedulerAdapter implements FWScheduler {
    private Scheduler scheduler;

    public SchedulerAdapter(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public String getSchedulerInstanceId() throws SchedulerException {
        return scheduler.getSchedulerInstanceId();
    }

    public String getSchedulerName() throws SchedulerException {
        return scheduler.getSchedulerName();
    }

    public boolean isInStandbyMode() throws SchedulerException {
        return scheduler.isInStandbyMode();
    }

    public boolean isShutdown() throws SchedulerException {
        return scheduler.isShutdown();
    }

    public boolean isStarted() throws SchedulerException {
        return scheduler.isStarted();
    }

    public void shutdown() throws SchedulerException {
        scheduler.shutdown();
    }

    public void shutdown(boolean waitForJobsToComplete)
            throws SchedulerException {
        scheduler.shutdown(waitForJobsToComplete);
    }

    public void standby() throws SchedulerException {
        scheduler.standby();
    }

    public void start() throws SchedulerException {
        scheduler.start();
    }
}
