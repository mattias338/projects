package com.banken.dbtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;


public class MainActivity extends Activity {

    private FeedReaderDbHelper feedReaderDbHelper;
    private OtherDbActions otherDbActions = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    public void onButtonCreate(View view) {
        feedReaderDbHelper = new FeedReaderDbHelper(this.getApplicationContext());
        otherDbActions = new OtherDbActions(feedReaderDbHelper);
    }

    public void onStoreData(View view) {
        Log.i("Random ", "" + Math.random());
        otherDbActions.storeData();
    }

    public void onReadData(View view) {
        String dbData = otherDbActions.readData();

        TextView text = (TextView) findViewById(R.id.textView_info);

        text.setText(dbData);


    }
}
