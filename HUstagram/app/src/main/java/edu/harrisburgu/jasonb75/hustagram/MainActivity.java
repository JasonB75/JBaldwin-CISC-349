package edu.harrisburgu.jasonb75.hustagram;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

    private Button cameraButton, uploadButton, viewImagesButton;
    private EditText commentEditText;

    private Context context;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private BitmapDrawable drawable;

    protected RequestQueue queue;

    protected String  uploadURL = "http://10.2.98.125:5000/save_image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        RequestQueue queue = Volley.newRequestQueue(context);

        //Buttons
        cameraButton = findViewById(R.id.takePhotoButton);
        uploadButton = findViewById(R.id.uploadButton);
        viewImagesButton = findViewById(R.id.viewImagesButton);

        //Comment Edit text
        commentEditText = findViewById(R.id.commentEditText);

        //Main image view on the main screen, to view the picture just taken
        imageView = findViewById(R.id.mainImageView);

        //When the campture image button is pressed, send an intent to the built in image capture
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
            }
        });

        //When the upload button is clocked, start the upload
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawable = (BitmapDrawable) imageView.getDrawable();
                final Bitmap bitmap = drawable.getBitmap();

                String comments = commentEditText.getText().toString();
                if (comments.length() < 1) comments = " ";

                uploadToServer(encodeToBase64(bitmap, Bitmap.CompressFormat.PNG, 100),
                        comments,
                        getDT(),
                        queue);
            }
        });


    }


    @Override // This handles the return from the image capture activity
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE) {
            if (resultCode == RESULT_OK) {
                assert data != null;

                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imageBitmap);

            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Picture Cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }


    private void uploadToServer(final String image, String comment, String dateAndTime, RequestQueue queue) {
        JSONObject json = new JSONObject();
        try {
            json.put("comment", comment);
            json.put("dateTime", dateAndTime);
            json.put("image", image);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, uploadURL, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Hello", "Response: " + response.toString());
                        try {
                            Log.d("Date Time: ", json.getString("dateTime"));
                            Log.d("comment: ", json.getString("comment"));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Hello", error.getMessage());
            }
        });

        queue.add(jsonObjectRequest);
    }

    //A method to get the formated date and time in a string
    private String getDT(){
        SimpleDateFormat sdf = new SimpleDateFormat("'Date 'dd-MM-yyyy 'and Time 'HH:mm:ss z");
        String currentDateAndTime = sdf.format(new Date());
        return currentDateAndTime;
    }





}