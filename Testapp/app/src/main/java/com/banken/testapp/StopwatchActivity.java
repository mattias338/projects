package com.banken.testapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class StopwatchActivity extends Activity {

    private ScheduledExecutorService executor;
    private ScheduledFuture<?> executorHandle;
    private TextView timeView;
    private Stopwatch stopwatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        //setup executorService
        executor = Executors.newSingleThreadScheduledExecutor();

        timeView = (TextView) findViewById(R.id.time);

        stopwatch = new Stopwatch(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onStartButtonClick(View view) {
        stopwatch.start();
        startUpdating();
    }

    private String getTimeFormatted(long time) {
        String s = Double.toString(time / 1000.0);
        int point = s.indexOf(".");
        return s.substring(0, Math.min(point + 2, s.length()));
    }

    public void onStopButtonClick(View view) {
        stopwatch.stop();
        cancelUpdates();
    }

    public void onResetButtonClick(View view) {
        stopwatch.reset();
        cancelUpdates();
        timeView.setText("0");
    }

    @Override
    public void onResume() {
        super.onResume();
        stopwatch.loadState();
        if (stopwatch.getStopwatchState() == Stopwatch.StopwatchState.RUNNING) {
            startUpdating();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        stopwatch.storeState();
        cancelUpdates();
    }

    private boolean executorExists() {
        return (executorHandle != null && !executorHandle.isDone());
    }

    private void cancelUpdates() {
        if (executorExists()) {
            executorHandle.cancel(true);
        }
    }

    private void startUpdating() {
        log("Setting up executor");

        if (!executorExists()) {
            final TextView textView = timeView;
            executorHandle = executor.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    final String sTime = getTimeFormatted(stopwatch.getStopwatchTime());
                    log("In executor" + sTime);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(sTime);
                        }
                    });
                }
            }, 0, 100, TimeUnit.MILLISECONDS);
        }
    }

    static void log(String log) {
        Log.i("Mattias", log);
    }
}
