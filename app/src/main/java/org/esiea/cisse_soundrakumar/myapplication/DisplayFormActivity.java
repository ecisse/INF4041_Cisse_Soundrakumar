package org.esiea.cisse_soundrakumar.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.esiea.cisse_soundrakumar.myapplication.R;


public class DisplayFormActivity extends Activity{
    public static final String TAG = "DisplayFormActivity";
    SQLiteDatabase db = null;
    String tableName = "lieu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_form);
    }

    /**
     * Save the task in database in tableName
     * @param view
     */
    public void createTask (View view) {
        Log.i(TAG, "Creation task in progress");
        db = this.openOrCreateDatabase("database", MODE_PRIVATE, null);

        EditText task_name = (EditText)findViewById(R.id.editTextTaskName);
        EditText task_description = (EditText)findViewById(R.id.editTextTaskDescription);
        EditText task_address = (EditText)findViewById(R.id.editTextAddress);

        String taskName = task_name.getText().toString();
        String description = task_description.getText().toString();
        String address = task_address.getText().toString();
        Log.i(TAG, taskName +" | "+description+" | "+address);

        try {
            db = this.openOrCreateDatabase("database", MODE_PRIVATE, null);
            db.execSQL("INSERT INTO " + tableName + " (Name, Description, Address)"
                    + " VALUES('" + taskName + "','" + description + "','" + address +"');");
        }

        catch (Exception e) {
            Log.e(TAG, "error", e);
            Log.i(TAG, "insertion problem");
            Toast.makeText(getBaseContext(), "problem in DB", Toast.LENGTH_LONG).show();
        }

        finally {
            if (db != null) {
                db.close();
                Toast.makeText(getBaseContext(), "Task saved", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, DisplayFormActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {

            case R.id.action_table:
                Log.i(TAG, "click on table");
                displayList();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Display the task list
     */
    public void displayList () {
        Log.i(TAG, "displayList");
        Intent intent = new Intent(this, DisplayListActivity.class);
        startActivity(intent);
    }

}
