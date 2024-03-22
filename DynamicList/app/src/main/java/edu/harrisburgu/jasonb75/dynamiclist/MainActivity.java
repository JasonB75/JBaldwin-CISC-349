package edu.harrisburgu.jasonb75.dynamiclist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private NetworkImageView image;
    private ImageLoader imageLoader;

    protected static final String url = "https://nua.insufficient-light.com/data/holiday_songs_spotify.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        ArrayList<HolidaySongs> arrayOfSongs = new ArrayList<>();

        // Create the adapter to convert the array to views
        HolidaySongsAdapter adapter = new HolidaySongsAdapter(this, arrayOfSongs);

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.start();

        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET,
                        url, null,

                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++){
                                    try{
                                        JSONObject obj = response.getJSONObject(i);
                                        HolidaySongs song = new HolidaySongs(response.getJSONObject(i));
                                        Log.d("New Song!", song.getAlbum_name());
                                        arrayOfSongs.add(song);

                                    } catch (JSONException e){
                                        e.printStackTrace();
                                    }
                                }

                                // Attach the adapter to a ListView
                                ListView listView = (ListView) findViewById(R.id.listView);
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