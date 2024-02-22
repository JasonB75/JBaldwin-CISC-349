package edu.harrisburgu.jasonb75.loginthree;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class MainActivity extends AppCompatActivity {

    EditText usernameText;
    EditText passwordText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginButton = (Button) findViewById(R.id.loging_button);
        usernameText = (EditText) findViewById(R.id.usernameField);
        passwordText =(EditText) findViewById(R.id.passField);

        RequestQueue queue = Volley.newRequestQueue(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Log.d("CLICK", "login buttin clicked");
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();

                String data = String.format("{ \"username\":\"%s\"password\":\"%s\"}", username, password);

                //JSONArrayRequest
                JsonRequest jsonRequest =
                        new JsonRequest(Request.Method.POST,
                                "http://10.0.0.146:5000/login", null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {
                                            boolean success = response.getBoolean("login");
                                            Log.d("JSONObject Response", "Success: " +
                                                    success);
                                            if (success){
                                                Toast.makeText(v.getContext(), R.string.loginsuccess, Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(v.getContext(), R.string.logingbad, Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            throw new RuntimeException(e);
                                        }

                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("JSONArray Error", "Error:" + error);
                            }
                        }) {
                            @Override
                            protected Response parseNetworkResponse(NetworkResponse respose){
                                String data = new String(respose.data);
                                try {
                                    JSONObject json = new JSONObject(data);
                                    Response<JSONObject> res  = Response.success(json, null);
                                    Log.d("Login", "parseNetworkResponse called");
                                    return res;
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                        };
                queue.add(jsonRequest);
            }
        });
    }
}