package org.esiea.cisse_soundrakumar.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.esiea.cisse_soundrakumar.myapplication.R;


public class DisplayListActivity extends Activity {
    public static final String TAG = "DisplayListActivity";
    SQLiteDatabase db = null;
    String tableName = "lieu";
    String Data = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);

        try {
            db = this.openOrCreateDatabase("database", MODE_PRIVATE, null);

            Cursor c = db.rawQuery("SELECT * FROM " + tableName, null);
            int Column1 = c.getColumnIndex("Name");
            int Column2 = c.getColumnIndex("Description");
            int Column3 = c.getColumnIndex("Address");

            c.moveToFirst();

            if (c != null) {

                do {
                    String Name = c.getString(Column1);
                    String Description = c.getString(Column2);
                    String Address = c.getString(Column3);

                    Data = Data + Name + "\t | " + Description + "\t| " + Address +"\n\n";

                } while (c.moveToNext());
            }

            TextView tv = new TextView(this); // creating Text View to show data in the app
            tv.setTextSize(18F);
            tv.setText("\n"+"Name \t| Description \t| Address \n <---------------------------->\n"
                    + Data);
            setContentView(tv);  // set created text view as Content View
        }

        catch (Exception e) {
            Log.e(TAG, "error", e);
            Log.i(TAG, "no data");
        }

        finally {
            if (db != null ){
                db.close();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.display, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {

            case R.id.action_drop:
                Log.i(TAG, "click on drop");
                dropTable();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Drop the table "Lieu"
     */
    private void dropTable() {
        Log.i(TAG, "drop in progress");
        db = this.openOrCreateDatabase("database", MODE_PRIVATE, null);
        db.execSQL("DROP TABLE IF EXISTS " + tableName );
        Toast.makeText(getBaseContext(), "Table dropped", Toast.LENGTH_SHORT).show();
    }

}
