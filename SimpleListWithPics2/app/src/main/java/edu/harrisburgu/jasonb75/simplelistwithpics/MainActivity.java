package edu.harrisburgu.jasonb75.simplelistwithpics;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import android.widget.AdapterView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ListView listView;
    private TextView textView;
    String[] listItem;
    private ImageLoader imageLoader;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        textView = (TextView) findViewById(R.id.textView);


        ArrayList<User> arrayOfUsers = new ArrayList<>();
        arrayOfUsers.add(new User("Eve", "777-777-7777", "https://static.toiimg.com/photo/msid-67586673/67586673.jpg"));
        arrayOfUsers.add(new User("John", "777-777-7777", "https://static.toiimg.com/photo/msid-67586673/67586673.jpg"));
        arrayOfUsers.add(new User("Mark", "777-777-7777", "https://static.toiimg.com/photo/msid-67586673/67586673.jpg"));
        arrayOfUsers.add(new User("Michael", "777-777-7777", "https://static.toiimg.com/photo/msid-67586673/67586673.jpg"));
        arrayOfUsers.add(new User("Adam", "777-777-7777", "https://static.toiimg.com/photo/msid-67586673/67586673.jpg"));
        arrayOfUsers.add(new User("Mary", "777-777-7777", "https://static.toiimg.com/photo/msid-67586673/67586673.jpg"));
        arrayOfUsers.add(new User("Olivia", "777-777-7777", "https://static.toiimg.com/photo/msid-67586673/67586673.jpg"));


        // Create the adapter to convert the array to views
        UserAdapter adapter = new UserAdapter(this, arrayOfUsers);

        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);



        /*listItem = getResources().getStringArray(R.array.array_technology);

        for (String s : listItem) {
            Log.d(TAG, s);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_list,
                listItem);
        listView.setAdapter(adapter);*/


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                User value =(User) adapter.getItem(position);
                Toast.makeText(getApplicationContext(),value.getName(),Toast.LENGTH_SHORT).show();
            }
        });

        //"anybody toasted?"

    }
}