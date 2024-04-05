package edu.harrisburgu.jasonb75.hustagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
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

public class GridViewActivity extends AppCompatActivity {

    private RecyclerView imagesRV;
    private RecyclerViewAdapter imageRVAdapter;
    private ArrayList<Post> postArrayList = new ArrayList<Post>();;

    public static Intent newGridIntent(Context packageContext) {
        Intent i = new Intent(packageContext, GridViewActivity.class);
        return i;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        imagesRV = findViewById(R.id.idRVImages);


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.2.98.125:5000/images";
        queue.start();


        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET,
                        url, null,

                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++){
                                    try{
                                        JSONObject data = response.getJSONObject(i);
                                        String pictureString = data.getString("image");
                                        String commentString = data.getString("comment");

                                        byte[] pictureBytes = Base64.decode(pictureString, Base64.DEFAULT);

                                        Bitmap picture = BitmapFactory.decodeByteArray(pictureBytes, 0, pictureBytes.length);

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

    private void prepareRecyclerView() {

        // in this method we are preparing our recycler view.
        // on below line we are initializing our adapter class.
        imageRVAdapter = new RecyclerViewAdapter(GridViewActivity.this, postArrayList);

        // on below line we are creating a new grid layout manager.
        GridLayoutManager manager = new GridLayoutManager(GridViewActivity.this, 3);

        // on below line we are setting layout
        // manager and adapter to our recycler view.
        imagesRV.setLayoutManager(manager);
        imagesRV.setAdapter(imageRVAdapter);
    }
}