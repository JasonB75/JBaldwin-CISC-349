package edu.harrisburgu.jasonb75.walmartlist;

import static android.view.View.inflate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StoreFragment extends Fragment {

    protected Store store;

    public StoreFragment(Store store){
        this.store = store;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.store_fragment, container, false);

        TextView tempV = view.findViewById(R.id.storeName);
        tempV.setText(store.getName());
        tempV = view.findViewById(R.id.address_view);
        tempV.setText(store.getAddress());
        tempV = view.findViewById(R.id.city_View);
        tempV.setText(store.getCity());
        tempV = view.findViewById(R.id.phoneNumber_view);
        tempV.setText(store.getPhoneNumber());
        return view;
    }

}
