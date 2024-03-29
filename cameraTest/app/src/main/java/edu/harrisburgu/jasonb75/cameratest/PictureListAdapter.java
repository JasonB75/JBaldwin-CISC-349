package edu.harrisburgu.jasonb75.cameratest;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class PictureListAdapter extends BaseAdapter {

    private Context context;
    List<Bitmap> pictures;

    public PictureListAdapter(Context context, ArrayList<Bitmap> pictures){
        this.context = context;
        this.pictures = pictures;
    }

    @Override
    public int getCount(){
        if (null == pictures) return 0;
        else return pictures.size();
    }

    @Override
    public Object getItem(int i){
        if (null == pictures) return null;
        else return pictures.get(i);
    }

    @Override
    public long getItemId(int i){
        if (null == pictures) return 0;
        else return pictures.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        view = LayoutInflater.from(context).inflate(R.layout.list_item,
                viewGroup, false);

        Log.d("picture list adapter", "Image index: " + i);

        Bitmap picture = pictures.get(i);

        ImageView image = (ImageView) view.findViewById(R.id.listImageView);
        TextView text = (TextView) view.findViewById(R.id.imageTextView);

        image.setImageBitmap(picture);

        text.setText("Image Index " + i);

        return view;
    }



}
