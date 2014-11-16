package com.banken.testapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Stopwatch {
    final private Activity activity;
    private long startTime;
    private long stopTime;
    private static final String START_TIME = "startTime";
    private static final String STOP_TIME = "stopTime";

    public Stopwatch(Activity activity) {
        this.activity = activity;
        init();
    }

    public void init() {
        loadState();
    }

    public enum StopwatchState {
        RUNNING, STOPPED
    }

    public void start() {
        if (getStopwatchState() == StopwatchState.STOPPED ) {
            if (startTime == 0) {
                startTime = getCurrentTime();
                StopwatchActivity.log("startTime0: " + startTime);
            } else {
                StopwatchActivity.log("getCurentTime: " + getCurrentTime() + ", stopTime: " + stopTime);
                StopwatchActivity.log("startTime: " + startTime);
                startTime += getCurrentTime() - stopTime;
                StopwatchActivity.log("startTime: " + startTime);
            }
            stopTime = 0;
        }

//        if (startTime == 0) {
//            startTime = getCurrentTime();
//        } else if (stopTime != 0) {
//            startTime += getCurrentTime() - stopTime;
//            stopTime = 0;
//        }
    }

    public void stop() {
        if (getStopwatchState() == StopwatchState.RUNNING) {
            stopTime = getCurrentTime();
        }
    }

    public void reset() {
        startTime = 0;
        stopTime = 0;
    }

    public long getStopwatchTime() {
        return getCurrentTime() - startTime;
    }

    public void loadState() {
        SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);
        startTime = preferences.getLong(START_TIME, 0);
        stopTime = preferences.getLong(STOP_TIME, 0);
        StopwatchActivity.log("getState: startTime: " + startTime + ", stopTime: " + stopTime);
    }

    public void storeState(){
        SharedPreferences.Editor edit = activity.getPreferences(Context.MODE_PRIVATE).edit();
        edit.putLong(START_TIME, startTime);
        edit.putLong(STOP_TIME, stopTime);
        edit.commit();
        StopwatchActivity.log("storeState: startTime: " + startTime + ", stopTime: " + stopTime);
    }

    public StopwatchState getStopwatchState() {
        if (startTime != 0 && stopTime == 0) {
            return StopwatchState.RUNNING;
        }
        return StopwatchState.STOPPED;
    }

    private long getCurrentTime() {
        return System.currentTimeMillis();
    }
}
