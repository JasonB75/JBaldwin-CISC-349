package edu.harrisburgu.jasonb75.customerlist2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String allCustomerURL = "http://10.2.98.125:5000/all";
    private final String updateCustomerURL = "http://10.2.98.125:5000/update";
    private final String addCustomerURL = "http://10.2.98.125:5000/add";

    RequestQueue queue;

    List<Customer> customerList;
    ListView listView;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview); // The main list view for the main activity
        CustomerListAdapter adapter = new CustomerListAdapter(this, customerList); // The adapter for the list view

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.start();

        JsonArrayRequest jsonArrayRequest =
                new JsonArrayRequest(Request.Method.GET,
                        allCustomerURL, null,

                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                for (int i = 0; i < response.length(); i++){
                                    try{
                                        JSONObject obj = response.getJSONObject(i);
                                        String state = obj.getString("state");
                                        if (state.endsWith("PA")){
                                            Customer customer = new Customer(response.getJSONObject(i));
                                            customerList.add(customer);
                                            Log.d("New customer!:", customer.getName());


                                        }
                                    } catch (JSONException e){
                                        e.printStackTrace();
                                    }
                                }
                                ListView listView = (ListView) findViewById(R.id.listview);
                                listView.setAdapter(adapter);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Log.d("JSON array error", "Error" + error);
                    }
                });

        queue.add(jsonArrayRequest);

        Button addCustomerButton = (Button) findViewById(R.id.add_customer_button);
        addCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = AddCustomer.newIntent(v.getContext(), queue);
                String message = "hello from mainactivity";
                //i.putExtra(EXTRA_MESSAGE, message);
                startActivityForResult(i, 0);
            }
        });

    }
}