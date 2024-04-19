package edu.harrisburgu.jasonb75.journalapp2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EntriesLIstAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<JournalEntry> arrayList;
    private TextView moodTextview, timeTextview, dateTectview, energyTextview, sAmountTextview, sQualityTextview, socialBatteryTextview, stomachFeelingTextview, eatTextView, notesTextview;

    private ImageView imageView;
    public EntriesLIstAdapter(Context context, ArrayList<JournalEntry> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.item_entry,
                parent, false );

        moodTextview = convertView.findViewById(R.id.mood_textview);
        timeTextview = convertView.findViewById(R.id.time_textview);
        dateTectview = convertView.findViewById(R.id.date_textview);
        energyTextview = convertView.findViewById(R.id.energy_textview);
        sAmountTextview = convertView.findViewById(R.id.sleepAmount_textview);
        sQualityTextview = convertView.findViewById(R.id.sleepQuality_textview);
        socialBatteryTextview = convertView.findViewById(R.id.socialBatt_textview);
        stomachFeelingTextview = convertView.findViewById(R.id.stomachFeeling_textview);
        eatTextView = convertView.findViewById(R.id.lastEaten_textview);
        notesTextview = convertView.findViewById(R.id.notes_textview);
        imageView = convertView.findViewById(R.id.imageView);

        moodTextview.setText(String.valueOf(arrayList.get(position).getMood()));
        timeTextview.setText(arrayList.get(position).getTime());
        dateTectview.setText(arrayList.get(position).getDate());
        energyTextview.setText(String.valueOf(arrayList.get(position).getEnergy()));
        sAmountTextview.setText(String.valueOf(arrayList.get(position).getSleepAmount()));
        sQualityTextview.setText(String.valueOf(arrayList.get(position).getSleepQuality()));
        socialBatteryTextview.setText(String.valueOf(arrayList.get(position).getSocialBattery()));
        stomachFeelingTextview.setText(String.valueOf(arrayList.get(position).getStomachFeeling()));
        eatTextView.setText(String.valueOf(arrayList.get(position).getLastEaten()));
        notesTextview.setText(arrayList.get(position).getNotes());

        if (!arrayList.get(position).getImage().equals(" ")) {
            byte[] pictureBytes = Base64.decode(arrayList.get(position).getImage(), Base64.DEFAULT);
            Bitmap picture = BitmapFactory.decodeByteArray(pictureBytes, 0, pictureBytes.length);
            imageView.setImageBitmap(picture);
        }

        return convertView;
    }
}