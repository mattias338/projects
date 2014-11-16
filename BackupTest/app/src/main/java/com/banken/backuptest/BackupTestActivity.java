package com.banken.backuptest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;


public class BackupTestActivity extends Activity {
    public final static String FILENAME = "Data.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backup_test);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.backup_test, menu);
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


    public void saveButtonOnClick(View view) {
        EditText weightEdittext = (EditText) findViewById(R.id.weight_edittext);

        log(weightEdittext.getText().toString());
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(getFilename(), true));
            writer.write(System.currentTimeMillis() + "," + weightEdittext.getText().toString());
            writer.newLine();
            writer.close();
            log("wrote to file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readButtonOnClick(View view) {
        log("Read file");

        TextView presentTextView = (TextView) findViewById(R.id.present_data_textview);
        Calendar calendar = Calendar.getInstance();

        BufferedReader reader;
        try {
            StringBuilder string = new StringBuilder();
            String line;
            reader = new BufferedReader(new FileReader(getFilename()));
            while ((line = reader.readLine()) != null) {
                String time = line.substring(0,line.indexOf(","));
                calendar.setTimeInMillis(Long.parseLong(time));
                string.append(calendar.get(Calendar.YEAR));
                string.append("-");
                string.append(calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH));
                string.append("-");
                string.append(calendar.get(Calendar.DAY_OF_MONTH));
                string.append(" ");
                string.append(calendar.get(Calendar.HOUR_OF_DAY));
                string.append(".");
                int minutes = calendar.get(Calendar.MINUTE);
                string.append(minutes < 10 ? "0" + minutes : minutes);

                string.append(": ");
                string.append(line.substring(line.indexOf(",") + 1, line.length()));
                string.append(System.getProperty("line.separator"));
            }
            reader.close();
            presentTextView.setText(string.toString());
        } catch (IOException e) {
            e.printStackTrace();
            log("Couldn't open file");
        }
    }



    private String getFilename() {
        return getFilesDir() + File.separator + FILENAME;
    }

    private void log(String s) {
        Log.i("Mattias", s);
    }
}


