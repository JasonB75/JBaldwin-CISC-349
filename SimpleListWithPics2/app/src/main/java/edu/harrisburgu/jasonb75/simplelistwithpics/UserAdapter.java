package edu.harrisburgu.jasonb75.simplelistwithpics;

import android.content.Context;
import android.graphics.Bitmap;
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

public class UserAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<User> arrayList;
    private TextView name, phone;
    private ImageLoader imageLoader;
    private NetworkImageView image;
    private String url;

    public UserAdapter(Context context, ArrayList<User> arrayList) {
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

        convertView = LayoutInflater.from(context).inflate(R.layout.item_user,
                parent, false );

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

        image = (NetworkImageView) convertView.findViewById(R.id.networkImageView);
        name = convertView.findViewById(R.id.name);
        phone = convertView.findViewById(R.id.phone);
        name.setText(arrayList.get(position).getName());
        phone.setText(arrayList.get(position).getPhone());
        url = arrayList.get(position).getImageURL();
        //image.setImageUrl(url, imageLoader);

        return convertView;
    }
}