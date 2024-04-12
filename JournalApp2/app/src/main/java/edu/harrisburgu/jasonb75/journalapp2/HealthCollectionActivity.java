package edu.harrisburgu.jasonb75.journalapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class HealthCollectionActivity extends AppCompatActivity {

    private static JournalEntry journalEntry;

    private SeekBar energySeekbar, sAmountSeekbar, sQualitySeekbar, socialBatterySeekbar, stomachFeelingSeekbar, eatSeekBar;

    private TextView energyTextview, sAmountTextview, sQualityTextview, socialBatteryTextview, stomachFeelingTextview, eatTextView;

    private Button uploadButton, deleteButton, saveButton;

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
}