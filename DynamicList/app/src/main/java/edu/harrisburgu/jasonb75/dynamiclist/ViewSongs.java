package edu.harrisburgu.jasonb75.dynamiclist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class ViewSongs extends AppCompatActivity {

    private Button back_button;
    private TextView album_name, album_artist;
    private NetworkImageView image;
    private ImageLoader imageLoader;

    private static HolidaySongs song;

    //Recieve the intent from mainactivity and save the song object passed through
    public static Intent newIntent(Context packageContext, HolidaySongs incoming_song) {
        Intent i = new Intent(packageContext, ViewSongs.class);
        //i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        song = incoming_song;
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_songs);

        //Bind the widgets
        album_name = findViewById(R.id.album_name2);
        album_artist = findViewById(R.id.album_artist2);
        back_button = findViewById(R.id.back_button);
        image = findViewById(R.id.networkImageView2);

        //Set the text from the song object, and pull the img URL for use bellow
        album_name.setText(song.getAlbum_name());
        album_artist.setText(song.getArtist_name());
        String url = song.getPlaylist_img();

        //Start a new request queue and setup an image loader for the playlist img
        RequestQueue queue = Volley.newRequestQueue(this);
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

        //When the button on screen is pushed, go back to the main activity
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent returnIntent = new Intent();
                finish();
            }
        });

    }
}