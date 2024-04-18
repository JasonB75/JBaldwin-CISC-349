package edu.harrisburgu.jasonb75.journalapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class HealthCollectionActivity extends AppCompatActivity {

    protected final String FILENAME = "edu_harrisburgu_jasonb75_journalapp";

    protected final String UPLOAD_URL = "http://10.0.0.146:5000/add";

    private static JournalEntry journalEntry;

    private SeekBar energySeekbar, sAmountSeekbar, sQualitySeekbar, socialBatterySeekbar, stomachFeelingSeekbar, eatSeekBar;

    private TextView energyTextview, sAmountTextview, sQualityTextview, socialBatteryTextview, stomachFeelingTextview, eatTextView;

    private Button uploadButton, deleteButton, saveButton;

    private EditText notesText;

    private Context context;
    private ImageView imageView;
    private BitmapDrawable drawable;



    // constant to compare
    // the activity result code
    int SELECT_PICTURE = 200;

    public static Intent newIntent(Context packageContext, String date, String time, int mood) {
        Intent i = new Intent(packageContext, HealthCollectionActivity.class);
        journalEntry = new JournalEntry(mood, date, time);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_collection);
        context = this;

        //The edittext used for adding notes to the entry
        notesText = (EditText) findViewById(R.id.notesEditText);

        //The textviews that display the seekbar value
        energyTextview = (TextView) findViewById(R.id.energyTextView);
        sAmountTextview = (TextView) findViewById(R.id.sAmountTextView);
        sQualityTextview = (TextView) findViewById(R.id.sQualityTextView);
        socialBatteryTextview = (TextView) findViewById(R.id.socialTextView);
        stomachFeelingTextview = (TextView) findViewById(R.id.stomachTextView);
        eatTextView = (TextView) findViewById(R.id.eatTextView);

        //The seekbars for the different characteristics
        energySeekbar = (SeekBar) findViewById(R.id.energySeekbar);
        sAmountSeekbar = (SeekBar) findViewById(R.id.sAmountSeekbar);
        sQualitySeekbar = (SeekBar) findViewById(R.id.sQualitySeekbar);
        socialBatterySeekbar = (SeekBar) findViewById(R.id.socialSeekbar);
        stomachFeelingSeekbar = (SeekBar) findViewById(R.id.stomachSeekbar);
        eatSeekBar = (SeekBar) findViewById(R.id.eatSeekbar);

        //Setup the on change listeners for each seekbar, also set min/max and the textview to the new default value
        seekBarSetup(energySeekbar, energyTextview, 1, 10);
        seekBarSetup(sAmountSeekbar, sAmountTextview, 0, 24);
        seekBarSetup(sQualitySeekbar, sQualityTextview, 1, 10);
        seekBarSetup(socialBatterySeekbar, socialBatteryTextview, 1, 10);
        seekBarSetup(stomachFeelingSeekbar, stomachFeelingTextview, 1, 10);
        seekBarSetup(eatSeekBar, eatTextView, 0, 24);

        //The imageview and upload/delete buttons
        imageView = findViewById(R.id.imgView);
        uploadButton = findViewById(R.id.uploadImageButton);
        deleteButton = findViewById(R.id.deleteImageButton);

        //When the button is pressed, open the gallary to select a picture
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create an instance of the
                // intent of the type image
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);

                // pass the constant to compare it
                // with the returned requestCode
                startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
            }
        });
        //If the deleteimg button is clicked, set the imageview to transparent, effectivly deleting the image
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageDrawable(null);
            }
        });

        //The savebutton
        saveButton = findViewById(R.id.finishEntryButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEntry();
            }
        });

    }

    //The return from clicking the upload img button, saves the img selected to the imgview
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // some code adapted from https://www.geeksforgeeks.org/how-to-select-an-image-from-gallery-in-android/
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    imageView.setImageURI(selectedImageUri);


                }
            }
        }
    }


    //Sets the on change listener for each seekbar, while also setting their min/max
    //and updating the applicable textview - Reduces clutter
    private void seekBarSetup(SeekBar seekBar, TextView textView, int min, int max){
        seekBar.setMin(min);
        seekBar.setMax(max);
        textView.setText(String.valueOf(seekBar.getProgress()));

        //When the seekbar is changed, change the textview value for the attribute
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

    }

    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }

    /*private void saveToStorage(JournalEntry entry){
        ArrayList<JournalEntry> entryArrayList = new ArrayList<>();
        String fileInputString = "";

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try
        {
            FileInputStream fin = openFileInput(FILENAME);
            int a;
            StringBuilder temp = new StringBuilder();
            while ((a = fin.read()) != -1)
            {
                temp.append((char)a);
            }

            // setting text from the file.
            fileInputString = temp.toString();
            fin.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        Type listType = new TypeToken<ArrayList<JournalEntry>>(){}.getType();
        entryArrayList = gson.fromJson(fileInputString, listType);
        Log.d("read from internal", fileInputString);

        entryArrayList.add(entry);

        String saveString = gson.toJson(entryArrayList);
        Log.d("gsonString", saveString);
        try {
            FileOutputStream fOut = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fOut.write(saveString.getBytes());
            fOut.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    } */

    private void uploadToServer(JournalEntry entry) {
        JSONObject json = new JSONObject();
        RequestQueue queue = Volley.newRequestQueue(context);

        try {
            json.put("mood", entry.getMood());
            json.put("date", entry.getDate());
            json.put("time", entry.getTime());
            json.put("energy", entry.getEnergy());
            json.put("socialBattery", entry.getSocialBattery());
            json.put("sleepQuality", entry.getSleepQuality());
            json.put("sleepAmount", entry.getSleepAmount());
            json.put("stomachFeeling", entry.getStomachFeeling());
            json.put("lastEaten", entry.getLastEaten());
            json.put("notes", entry.getNotes());
            json.put("image", entry.getImage());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, UPLOAD_URL, json,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("Entry Upload! ", "Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Hello", error.getMessage());
            }
        });

        queue.add(jsonObjectRequest);
    }

    private void saveEntry(){
        //Pulling all the values from the entry characteristics into variables
        int energyLvl = Integer.parseInt(energyTextview.getText().toString());
        int socialBattery = Integer.parseInt(socialBatteryTextview.getText().toString());
        int sleepQuality = Integer.parseInt(sQualityTextview.getText().toString());
        int sleepAmount = Integer.parseInt(sAmountTextview.getText().toString());
        int stomachFeeling = Integer.parseInt(stomachFeelingTextview.getText().toString());
        int hoursSinceEating = Integer.parseInt(eatTextView.getText().toString());
        String notes = notesText.getText().toString();
        String image = null; // inital value set at null

        drawable = (BitmapDrawable) imageView.getDrawable();
        if (drawable != null){
            final Bitmap imgBitmap = drawable.getBitmap();
            image = encodeToBase64(imgBitmap, Bitmap.CompressFormat.PNG, 100);
        }
        journalEntry.setNonInitialValues(energyLvl, socialBattery, sleepQuality, sleepAmount, stomachFeeling,
                hoursSinceEating, notes, image);
        Log.d("Entry Saved!!", journalEntry.outputEverything());


        //saveToStorage(journalEntry);
        uploadToServer(journalEntry);

        Toast.makeText(context, "Entry Saved!", Toast.LENGTH_SHORT).show();

    }

}