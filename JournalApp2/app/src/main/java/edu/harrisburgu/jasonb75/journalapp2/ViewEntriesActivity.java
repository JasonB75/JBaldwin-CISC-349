package edu.harrisburgu.jasonb75.journalapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewEntriesActivity extends AppCompatActivity {

    protected final String SERVER_URL = "http://10.0.0.146:5000/get_all "; // THe url of the flask server
    private ArrayList<JournalEntry> entryArrayList = new ArrayList<JournalEntry>();;
    private Button newEntryButton;

    Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entries);

        context = this;

        newEntryButton = findViewById(R.id.newEntryButton);
        newEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MainActivity.class);
                startActivity(i);
            }
        });



        RequestQueue queue = Volley.newRequestQueue(this);

        // Create the adapter to convert the array to views
        EntriesLIstAdapter adapter = new EntriesLIstAdapter(this, entryArrayList); // The adapter for the list view, that has the array of entries

        //Start the request to pull all entries from the flask server
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET,
                        SERVER_URL, null,

                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++){
                                    try{
                                        //GEt the JSon object from the response
                                        JSONObject data = response.getJSONObject(i);

                                        //Pull out mood, date, and time to create the jounral entry
                                        int mood = data.getInt("mood");
                                        String date = data.getString("date");
                                        String time = data.getString("time");

                                        //Create the entry, and use a method to load the other values from the JSON
                                        JournalEntry entry = new JournalEntry(mood, date, time);
                                        entry.setFromJson(data);

                                        Log.d("ViewEntriesActivity - New from server: ", entry.outputEverything());

                                        //Add it to the arraylist to be passed to the list adapter
                                        entryArrayList.add(entry);

                                    } catch (JSONException e){
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("ImageGridView Activity", "results size: " + entryArrayList.size());

                                // Attach the adapter to a ListView
                                ListView listView = (ListView) findViewById(R.id.entryListView);
                                listView.setAdapter(adapter);


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("JSON array error", "Error" + error);
                    }
                });

        queue.add(jsonArrayRequest);




    }
}