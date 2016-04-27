package com.haw.introduction;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toolbar;

public class ItemActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item); //inflated layout view
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setActionBar(toolbar); //initialising the toolbar which holds menu and label

        if(getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true); //enables the back button in the toolbar
            //back button click events are implemented in the same way as other menu items
            //(onOptionsItemSelected --> android.R.id.home)
        }

        ((TextView) findViewById(R.id.textView4)).setText(getIntent().getStringExtra("title"));
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
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
