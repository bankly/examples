package com.fw.jmx.quartz;

import org.quartz.SimpleTrigger;
import org.quartz.Trigger;

public class SimpleTriggerAdapter extends AbstractTriggerAdapter implements
        FWSimpleTrigger {

    private SimpleTrigger trigger;

    public SimpleTriggerAdapter(SimpleTrigger trigger) {
        this.trigger = trigger;
    }

    public Trigger getTrigger() {
        return this.trigger;
    }

    public int getRepeatCount() {
        return this.trigger.getRepeatCount();
    }

    public long getRepeatInterval() {
        return this.trigger.getRepeatInterval();
    }

    public int getTimesTriggered() {
        return this.trigger.getTimesTriggered();
    }
}
