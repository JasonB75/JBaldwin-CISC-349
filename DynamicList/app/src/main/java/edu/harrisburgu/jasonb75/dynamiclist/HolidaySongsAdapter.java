package edu.harrisburgu.jasonb75.dynamiclist;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class HolidaySongsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HolidaySongs> arrayList;
    private TextView albumName, artistName, danceability, duration;
    private NetworkImageView image;
    private ImageLoader imageLoader;

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

        Log.d("In holiday adapter", arrayList.get(position).getAlbum_name());

        convertView = LayoutInflater.from(context).inflate(R.layout.item_song,
                parent, false );

        //Format the text for the dancability view
        String dance_string = "Danceability: "; dance_string += arrayList.get(position).getDanceability().toString();

        //format the text for the duration text view
        int duration_min = (arrayList.get(position).getDuration_ms() / 1000) / 60;
        int duration_sec = (arrayList.get(position).getDuration_ms() / 1000) % 60;

        String duration_string = Integer.toString(duration_min) + Integer.toString(duration_sec);

        albumName = convertView.findViewById(R.id.album_name);
        artistName = convertView.findViewById(R.id.artist_name);
        danceability = convertView.findViewById(R.id.danceability);
        duration = convertView.findViewById(R.id.duration);
        image = (NetworkImageView) convertView.findViewById(R.id.networkImageView);

        albumName.setText(arrayList.get(position).getAlbum_name());
        artistName.setText(arrayList.get(position).getArtist_name());
        danceability.setText(dance_string);
        duration.setText(duration_string);
        String url = arrayList.get(position).getAlbum_img();

        RequestQueue queue = Volley.newRequestQueue(convertView.getContext());
        imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String,
                    Bitmap>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);

            }
        });
        image.setImageUrl(url, imageLoader);

        return convertView;
    }
}
