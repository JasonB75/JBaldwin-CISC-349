package edu.harrisburgu.jasonb75.mongodb_flask_listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String addUserURL = "http://10.0.0.17:5000/add";
    private final String allUserURL = "http://10.0.0.146:5000/all";
    private ListView listView;
    ArrayList<User> userList = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list_view);
        UserAdapter adapter = new UserAdapter(this, userList); // The adapter for the list view

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.start();

        //addUsers(queue);

        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET,
                        allUserURL, null,

                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++){
                                    try{
                                        JSONObject obj = response.getJSONObject(i);
                                        Log.d("New customer!:", obj.getString("name"));
                                        if (obj.getString("name").length() >0) {
                                            User user = new User(obj);
                                            Log.d("New customer!:", user.getName());
                                            userList.add(user);

                                        }

                                    } catch (JSONException e){
                                        e.printStackTrace();
                                    }
                                }
                                ListView listView = (ListView) findViewById(R.id.list_view);
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

/*    //
    //A function used to add users to the online database,
    protected void addUsers(RequestQueue queue){
        ArrayList<User> arrayOfUsers = new ArrayList<>();
        arrayOfUsers.add(new User("Eve", "777-877-7777", "123 45 7894"));
        arrayOfUsers.add(new User("John", "777-377-7777", "123 45 7894"));
        arrayOfUsers.add(new User("Mark", "777-772-7777", "123 45 7894"));
        arrayOfUsers.add(new User("Michael", "777-787-7777", "123 45 7894"));
        arrayOfUsers.add(new User("Adam", "777-777-7977", "123 45 7894"));
        arrayOfUsers.add(new User("Mary", "777-777-0777", "123 45 7894"));
        arrayOfUsers.add(new User("Olivia", "777-277-7777", "123 45 7894"));



        for (int i = 0; i<arrayOfUsers.size(); i++){

            String name = arrayOfUsers.get(i).getName();
            String phone = arrayOfUsers.get(i).getPhone();
            String ssn = arrayOfUsers.get(i).getSsn();


            String data = String.format("{\"name\":\"%s\", \"phone\":\"%s\", \"ssn\":\"%s\"}", name, phone, ssn);
            JSONObject jobj = null;
            try{
                jobj = new JSONObject(data);
            } catch (JSONException e){
                throw new RuntimeException(e);
            }

            JSONObject finaljobject = jobj;
            JsonRequest jsonRequest =
                    new JsonObjectRequest(Request.Method.POST,
                            addUserURL, finaljobject,

                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.d("Adding User: ", name);
                                    finish();
                                }
                            }, new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error){
                            Log.d("Save Error", "Error: " + error);
                            finish();
                        }
                    }) {
                        @Override
                        protected Response parseNetworkResponse(NetworkResponse response){
                            String data = new String(response.data);
                            Response<JSONObject> res = Response.success(finaljobject, null);
                            finish();
                            return res;
                        }
                    };

            queue.add(jsonRequest);

        }


    } */



}