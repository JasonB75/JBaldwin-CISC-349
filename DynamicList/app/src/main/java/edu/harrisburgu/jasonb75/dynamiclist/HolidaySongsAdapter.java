package edu.harrisburgu.jasonb75.dynamiclist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HolidaySongsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HolidaySongs> arrayList;
    private TextView albumName, artistName, danceability, duration;

    public HolidaySongsAdapter(Context context, ArrayList<HolidaySongs> arrayList) {
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

        private String dance_string = arrayList.get(position).getDanceability().toString()

        convertView = LayoutInflater.from(context).inflate(R.layout.item_song,
                parent, false );
        albumName = convertView.findViewById(R.id.album_name);
        artistName = convertView.findViewById(R.id.artist_name);
        danceability = convertView.findViewById(R.id.danceability);
        duration = convertView.findViewById(R.id.duration);

        albumName.setText(arrayList.get(position).getAlbum_name());
        artistName.setText(arrayList.get(position).getArtist_name());
        danceability.setText();
        duration.setText(arrayList.get(position).getDuration_ms());
        return convertView;
    }
}
