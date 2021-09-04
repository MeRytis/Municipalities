package com.task.programming.municipalities.schedule;

public enum Schedule {
    YEARLY("Yearly"),
    MONTHLY("Monthly"),
    WEEKLY("Weekly"),
    DAILY("Daily");

    private String scheduleKey;

    private Schedule(String scheduleKey) {
        this.scheduleKey = scheduleKey;
    }

    public String getScheduleKey() {
        return scheduleKey;
    }

}