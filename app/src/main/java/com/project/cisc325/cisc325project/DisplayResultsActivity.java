package com.project.cisc325.cisc325project;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;
import android.content.Intent;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DisplayResultsActivity extends ListActivity {

    String TAG = DisplaySearchActivity.class.getSimpleName();
    ArrayList<String> listItems=new ArrayList<String>();
    ArrayAdapter<String> adapter;

    public void search(String query) {
        String jsonUrl = "http://api.deckbrew.com/mtg/cards?name=" + query;
        JsonArrayRequest req = new JsonArrayRequest(jsonUrl, new Response.Listener<JSONArray> () {
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                try {
                    for(int i = 0; i < response.length(); i++) {
                        JSONObject card = (JSONObject) response.get(i);
                        String name = card.getString("name");
                        adapter.add(name);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        AppController.getInstance().addToRequestQueue(req);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_results);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        setListAdapter(adapter);

        Intent intent = getIntent();
        String query = intent.getStringExtra(DisplaySearchActivity.EXTRA_MESSAGE);
        search(query);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_results, menu);
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
