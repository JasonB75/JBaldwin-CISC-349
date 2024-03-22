package edu.harrisburgu.jasonb75.customerlist2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AddCustomer extends AppCompatActivity {

    private EditText customerName, customerAddress, customerPhone;
    private Button addButton;
    private final String addCustomerURL = "http://10.2.98.125:5000/add";

    protected static RequestQueue queue;
    protected static Context context;

    public static Intent newIntent(Context Context, RequestQueue queue) {
        Intent i = new Intent(Context, AddCustomer.class);
        AddCustomer.queue = queue;
        AddCustomer.context = Context;
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        customerName = (EditText) findViewById(R.id.Customer_name);
        customerAddress = findViewById(R.id.Customer_address);
        customerPhone = findViewById(R.id.Customer_phone);
        addButton = findViewById(R.id.Customer_add_button);



        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = customerName.getText().toString();
                String address = customerAddress.getText().toString();
                String phone = customerPhone.getText().toString();
                ArrayList<String> comments = new ArrayList<>();

                String data = String.format("{\"name\":\"%s\", \"address\":\"%s\", \"phone\":\"%s\"}", name, address, phone);
                JSONObject jobj = null;
                try{
                    jobj = new JSONObject(data);
                    jobj.put("commentList", new JSONArray(comments));
                } catch (JSONException e){
                    throw new RuntimeException(e);
                }

                JSONObject finaljobject = jobj;
                JsonRequest jsonRequest =
                        new JsonObjectRequest(Request.Method.POST,
                                addCustomerURL, finaljobject,

                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                }, new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error){
                                Log.d("Save Error", "Error: " + error);
                                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
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
        });

    }
}