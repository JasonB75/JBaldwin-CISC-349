package edu.harrisburgu.jasonb75.testvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Gson gson;

    ArrayList<Movie> movies = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Movie> movies = new ArrayList<>();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        final TextView textView = (TextView) findViewById(R.id.text);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        //JSONArrayRequest
        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET,
                        "https://gist.githubusercontent.com/alanponce/d8a5e47b4328b5560fb610c5731de2bd/raw/b9f2a2b20d7d71f0e9c31adf40c7c83308809ac0/movies.json", null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        Movie movie =
                                                gson.fromJson(response.getJSONObject(i)
                                                                .toString(),
                                                        Movie.class);

                                        movies.add(movie);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                                Log.d("Movie", movies.get(0).getTitle());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSONArray Error", "Error:" + error);
                    }
                });
        queue.add(jsonArrayRequest);
    }
}



























        /*final TextView textView = (TextView) findViewById(R.id.text);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        //JSONArrayRequest
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://api.androidhive.info/json/movies.json", null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObjectFromArray =
                                        response.getJSONObject(i);

                                JSONArray genre = jsonObjectFromArray
                                        .getJSONArray("genre");
                                ArrayList<String> genre_list = new ArrayList<>();
                                for (int j = 0; j < genre.length(); j++) {
                                    genre_list.add(genre.get(j).toString());
                                }
                                Movie movie = new Movie(
                                        jsonObjectFromArray.getString("title"),
                                        jsonObjectFromArray.getString("image"),
                                        jsonObjectFromArray.getDouble("rating"),
                                        jsonObjectFromArray.getInt("releaseYear"),
                                        genre_list
                                );

                                movies.add(movie);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        //Log the first movie
                        Log.d("Movie" , movies.get(0).getImage());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("JSONArray Error", "Error:" + error);
            }
        });

        queue.add(jsonArrayRequest);*/




        /*final TextView textView = (TextView) findViewById(R.id.text);
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://www.google.com";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText(response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);*/




