package edu.harrisburgu.jasonb75.journalapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

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

    protected final String SERVER_URL = "http://10.0.0.146:5000/get_all ";
    private ArrayList<JournalEntry> entryArrayList = new ArrayList<JournalEntry>();;


    public static Intent newIntent(Context packageContext) {
        Intent i = new Intent(packageContext, HealthCollectionActivity.class);
        return i;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entries);

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.start();


        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET,
                        SERVER_URL, null,

                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++){
                                    try{
                                        JSONObject data = response.getJSONObject(i);

                                        int mood = data.getInt("mood");
                                        String date = data.getString("date");
                                        String time = data.getString("time");

                                        JournalEntry entry = new JournalEntry(mood, date, time);
                                        entry.setFromJson(data);

                                        //byte[] pictureBytes = Base64.decode(pictureString, Base64.DEFAULT);

                                        //Bitmap picture = BitmapFactory.decodeByteArray(pictureBytes, 0, pictureBytes.length);

                                        Log.d("ViewEntriesActivity - New from server: ", entry.outputEverything());

                                        entryArrayList.add(entry);

                                    } catch (JSONException e){
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("ImageGridView Activity", "results size: " + entryArrayList.size());
                                //imageRVAdapter.notifyDataSetChanged();
                                /////////////////////////////////////////////////////////////view stuffs


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