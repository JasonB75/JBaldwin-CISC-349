package edu.harrisburgu.jasonb75.journalapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class HealthCollectionActivity extends AppCompatActivity {

    private static JournalEntry journalEntry;

    private SeekBar energySeekbar, sAmountSeekbar, sQualitySeekbar, socialBatterySeekbar, stomachFeelingSeekbar, eatSeekBar;

    private TextView energyTextview, sAmountTextview, sQualityTextview, socialBatteryTextview, stomachFeelingTextview, eatTextView;

    public static Intent newIntent(Context packageContext, String date, String time, int mood) {
        Intent i = new Intent(packageContext, HealthCollectionActivity.class);
        journalEntry = new JournalEntry(mood, date, time);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_collection);

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


        //Setting min and max for each seekbar
        energySeekbar.setMax(10); energySeekbar.setMin(1);
        sAmountSeekbar.setMax(24); sAmountSeekbar.setMin(0);
        sQualitySeekbar.setMax(10); sQualitySeekbar.setMin(1);
        socialBatterySeekbar.setMax(10); socialBatterySeekbar.setMin(1);
        stomachFeelingSeekbar.setMax(10); stomachFeelingSeekbar.setMin(1);
        eatSeekBar.setMax(24); eatSeekBar.setMin(0);

        //Setting the initial displayed values to the defaults of the seekbars
        energyTextview.setText(String.valueOf(energySeekbar.getProgress()));
        sAmountTextview.setText(String.valueOf(sAmountSeekbar.getProgress()));
        sQualityTextview.setText(String.valueOf(sQualitySeekbar.getProgress()));
        socialBatteryTextview.setText(String.valueOf(socialBatterySeekbar.getProgress()));
        stomachFeelingTextview.setText(String.valueOf(stomachFeelingSeekbar.getProgress()));
        eatTextView.setText(String.valueOf(eatSeekBar.getProgress()));


        //When the seekbar is changed, change the textview value for the attribute
        energySeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                energyTextview.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        //When the seekbar is changed, change the textview value for the attribute
        sAmountSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sAmountTextview.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        //When the seekbar is changed, change the textview value for the attribute
        sQualitySeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sQualityTextview.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        //When the seekbar is changed, change the textview value for the attribute
        socialBatterySeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                socialBatteryTextview.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        //When the seekbar is changed, change the textview value for the attribute
        stomachFeelingSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                stomachFeelingTextview.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        //When the seekbar is changed, change the textview value for the attribute
        eatSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                eatTextView.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });




    }
}