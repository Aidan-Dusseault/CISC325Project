package com.project.cisc325.cisc325project;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;


public class DisplaySearchActivity extends Activity {

    public final static String EXTRA_MESSAGE = "com.project.cisc325.cisc325project.MESSAGE";

    public void searchButton(View view) {
        Intent intent = new Intent(this, DisplayResultsActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String query = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, query);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_search);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
