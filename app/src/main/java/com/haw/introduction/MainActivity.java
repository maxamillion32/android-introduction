package com.haw.introduction;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.text.MessageFormat;
import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener, SensorEventListener {

    private Sensor mSensor;
    private SensorManager mSensorManager;
    private boolean mRunSensor;
    private ArrayList<String> mListValues = new ArrayList<>();

    @Override
    protected void onResume() {
        //state of activity if the activity is running again after it has been paused (for example after closing app)

        super.onResume();
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        //registerListener is for the sensor to connect this certain sensor with a specific sensor listener
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //inflated layout view
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar); //initialising the toolbar which holds menu and label

        mSensorManager = (SensorManager) getSystemService(Activity.SENSOR_SERVICE); //returns the sensor service
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER); //returns the sensor we want
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL); //register sensor to listener

        //fill data for the list view (list view adapter expects a string array list; take a look at 'ExampleAdapter')
        mListValues.add("HAW");
        mListValues.add("Test");
        mListValues.add("MOSY");
        mListValues.add("Android");
        mListValues.add("HAW");
        mListValues.add("Test");
        mListValues.add("MOSY");
        mListValues.add("Android");
        mListValues.add("HAW");
        mListValues.add("Test");
        mListValues.add("MOSY");
        mListValues.add("Android");

        //initialise list view, including array adapter (which holds the list and the layout per item) and
        //onItemClickListener which will start a new activity
        final ListView list = (ListView) findViewById(R.id.listView);
        ExampleAdapter adapter = new ExampleAdapter(this, mListValues);
        list.setAdapter(adapter);

        final Activity tempActivity = this; //to access the instance of this activity for the click listener
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //intents are used to start a new activity
                Intent intent = new Intent(tempActivity, ItemActivity.class);
                intent.putExtra("title", list.getAdapter().getItem(position) + "");
                //put extra is an advanced way to transfer data between activity and avoiding static references
                tempActivity.startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //will be executed if user clicks on a menu item, like settings (menu = right corner dropdown)
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        //handles all views which are connected to this click listener (see implemented class)
        //takes the view id (which is a button) and will execute the right click event

        switch (v.getId()) {
            case R.id.button:
                //Calculator implementation; takes the values of the text fields
                String s1 = ((EditText) findViewById(R.id.editText)).getText().toString();
                String s2 = ((EditText) findViewById(R.id.editText2)).getText().toString();

                if (!s1.equals("") && !s2.equals("")) {
                    int v1 = Integer.parseInt(s1);
                    int v2 = Integer.parseInt(s2);

                    ((TextView) findViewById(R.id.textView)).setText("Result: " + (v1 + v2));
                }
                break;
            case R.id.button2:
                //starts sensor tracking via boolean
                mRunSensor = true;
                break;
            case R.id.button3:
                //stops sensor tracking via boolean
                mRunSensor = false;
                break;
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //Primary sensor method to track changes to the certain sensor
        if (mRunSensor) {
            ((TextView) findViewById(R.id.textView2)).setText(MessageFormat.format("Value: {0}", event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this); //to avoid background processes which are simply not needed
        //you should unregister the sensor listener
    }

    //onStop and onDestroy are often not called due to several reasons;
    //avoid calling important stuff in these two methods

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
