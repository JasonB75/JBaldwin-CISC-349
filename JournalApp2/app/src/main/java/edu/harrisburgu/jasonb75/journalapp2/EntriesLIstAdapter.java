package edu.harrisburgu.jasonb75.journalapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EntriesLIstAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<JournalEntry> arrayList;
    private TextView name, phone, ssn;

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
        //convertView = LayoutInflater.from(context).inflate(R.layout.item_user,
         //       parent, false );
        //name = convertView.findViewById(R.id.name_view);
        //phone = convertView.findViewById(R.id.phone_view);
        //ssn = convertView.findViewById(R.id.ssn_view);

        //name.setText(arrayList.get(position).getName());
        //phone.setText(arrayList.get(position).getPhone());
        //ssn.setText(arrayList.get(position).getSsn());
        return convertView;
    }
}