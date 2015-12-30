package org.esiea.cisse_soundrakumar.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.esiea.cisse_soundrakumar.myapplication.R;

public class MainActivity extends Activity {
    public static final String TAG = "MainActivity";
    public static final String EXTRA_MESSAGE = "fr.esiea.togoapp.MESSAGE" ;
    public SQLiteDatabase db = null;
    String tableName = "Lieu";

    /**
     * show the view activity_main
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i( TAG , "MainActivity");

        db = this.openOrCreateDatabase("database", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + tableName + "(Name VARCHAR, Description VARCHAR, Address VARCHAR);");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {

            case R.id.action_form:
                Log.i(TAG, "click on form");
                return true;

            case R.id.action_map:
                Log.i(TAG, "click on map");
                return true;

            case R.id.action_table:
                Log.i(TAG, "click on list");
                return true;

            case R.id.action_settings:
                //displays settings;
                Log.i(TAG, "click on settings");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * display the list of task
     * @param view
     */
    public void displayList (View view) {
        Log.i(TAG, "displayList");
        Intent intent = new Intent(this, DisplayListActivity.class);
        startActivity(intent);
    }

    /**
     * Display the creation Form
     * @param view
     */
    public void displayForm (View view) {
        Log.i(TAG, "display form creation");
        Intent intent = new Intent(this, DisplayFormActivity.class);
        startActivity(intent);
    }

    // TODO display map with task
    /**
     * Display the map view
     * @param view
     */
    public void displayMap (View view) {
        Log.i(TAG, "not yet implemented");
        Toast.makeText(getBaseContext(), "Available in the next release", Toast.LENGTH_LONG).show();
    }
}