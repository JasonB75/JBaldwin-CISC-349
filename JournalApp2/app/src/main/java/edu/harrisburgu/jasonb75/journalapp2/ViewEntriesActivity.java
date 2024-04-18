package edu.harrisburgu.jasonb75.journalapp2;

import androidx.appcompat.app.AppCompatActivity;

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

public class ViewEntriesActivity extends AppCompatActivity {

    protected final String SERVER_URL = "http://10.0.0.146:5000/get_all ";

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

                                        int mood = data.getInt("mood");;
                                        String date = data.getString("date");;
                                        String time = data.getString("time");;

                                        //String pictureString = data.getString("image");
                                        //String commentString = data.getString("comment");

                                        //byte[] pictureBytes = Base64.decode(pictureString, Base64.DEFAULT);

                                        //Bitmap picture = BitmapFactory.decodeByteArray(pictureBytes, 0, pictureBytes.length);

                                        Post tempPost = new Post(picture, commentString);

                                        postArrayList.add(tempPost);

                                    } catch (JSONException e){
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("ImageGridView Activity", "results size: " + postArrayList.size());
                                //imageRVAdapter.notifyDataSetChanged();
                                /////view stuffs
                                prepareRecyclerView();

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