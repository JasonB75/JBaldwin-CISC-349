package edu.harrisburgu.jasonb75.cameratest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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
import android.util.Base64;
import java.util.BitSet;

public class ImageListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list_view);

        ArrayList<Bitmap> results = new ArrayList<Bitmap>();
        ListView list = (ListView) findViewById(R.id.listView);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.2.98.125:5000/images";
        queue.start();

        //addUsers(queue);

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

                                        byte[] pictureBytes = Base64.decode(pictureString, Base64.DEFAULT);

                                        Bitmap picture = BitmapFactory.decodeByteArray(pictureBytes, 0, pictureBytes.length);

                                        results.add(picture);

                                    } catch (JSONException e){
                                        e.printStackTrace();
                                    }
                                }
                                Log.d("ImageListView ACtivity", "results size: " + results.size());
                                ListView listView = (ListView) findViewById(R.id.listView);
                                PictureListAdapter adapter = new PictureListAdapter(list.getContext(), results);
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