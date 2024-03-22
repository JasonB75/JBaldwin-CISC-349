package edu.harrisburgu.jasonb75.customerlist2;

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

public class CustomerListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Customer> arrayList;
    private TextView customerName, customerAddress, customerPhone;

    public CustomerListAdapter(Context context, ArrayList<Customer> arrayList) {
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

        convertView = LayoutInflater.from(context).inflate(R.layout.item_customer,
                parent, false );

        customerName = convertView.findViewById(R.id.customerName);
        customerAddress = convertView.findViewById(R.id.address_view);
        customerPhone = convertView.findViewById(R.id.phoneNumber_view);


        customerName.setText(arrayList.get(position).getName());
        customerAddress.setText(arrayList.get(position).getAddress());
        customerPhone.setText(arrayList.get(position).getPhoneNumber());

        return convertView;
    }

}
