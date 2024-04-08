package edu.harrisburgu.jasonb75.journalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class HealthCollectionActivity extends AppCompatActivity {

    private static JournalEntry journalEntry;


    public static Intent newIntent(Context packageContext, String date, String time, int mood) {
        Intent i = new Intent(packageContext, HealthCollectionActivity.class);
        journalEntry = new JournalEntry(mood, date, time);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_collection);
    }
}