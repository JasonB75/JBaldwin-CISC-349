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

    private SeekBar energySeekbar;

    private TextView energyTextview;


    public static Intent newIntent(Context packageContext, String date, String time, int mood) {
        Intent i = new Intent(packageContext, HealthCollectionActivity.class);
        journalEntry = new JournalEntry(mood, date, time);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_collection);

        energyTextview = (TextView) findViewById(R.id.energyTextView);

        energySeekbar = (SeekBar) findViewById(R.id.energySeekbar);

        energySeekbar.setMax(10);
        //energySeekbar.setMin(1);

        energySeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                energyTextview.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("Energybar", "starttracking");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("Energybar", "endtracking");
            }
        });


    }
}