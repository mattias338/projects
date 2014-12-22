package com.banken.exlo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.banken.exlo.datahandling.DataEntry;

import java.util.Calendar;
import java.util.Locale;

public class AddEntryActivity extends Activity {
    private long time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayAdapter<CharSequence> typeAdapter =
                ArrayAdapter.createFromResource(this, R.array.types_array, android.R.layout.simple_spinner_dropdown_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner typeSpinner = (Spinner) findViewById(R.id.spinner_type);
        typeSpinner.setAdapter(typeAdapter);

        ArrayAdapter<CharSequence> unitAdapter =
                ArrayAdapter.createFromResource(this, R.array.units_array, android.R.layout.simple_spinner_dropdown_item);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner unitSpinner = (Spinner) findViewById(R.id.spinner_unit);
        unitSpinner.setAdapter(unitAdapter);

        buttonSetToNowCallback(null);
    }

    public void buttonSetToNowCallback(View view) {
        time = getCurrentTime();
        updateTimeViewWithTime(time);
    }

    public void buttonAddCallback(View view) {
        // Save entry
        Spinner typeSpinner = (Spinner) findViewById(R.id.spinner_type);
        String selectedType = (String) typeSpinner.getSelectedItem();
        Spinner unitSpinner = (Spinner) findViewById(R.id.spinner_unit);
        String selectedUnit = (String) unitSpinner.getSelectedItem();
        DataEntry dataEntry = new DataEntry(time , selectedType, selectedUnit);

        

    }

    private static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    private void updateTimeViewWithTime(long time) {
        TextView dateTimeTextView = (TextView) findViewById(R.id.textView_dateTime);
        dateTimeTextView.setText(getTimeFormatted(time));
    }

    private static String getTimeFormatted(long time) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);

        StringBuilder string = new StringBuilder();
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

        return string.toString();
    }

    public void textViewDateTimeCallback(View view) {
        // start another activity that lets the user set time and date...

        // maybe use DatePickerDialog and TimePickerDialog instead
    }
}
