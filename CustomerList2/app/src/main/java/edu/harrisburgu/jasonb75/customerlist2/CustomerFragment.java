package edu.harrisburgu.jasonb75.customerlist2;

import static android.view.View.inflate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CustomerFragment extends Fragment {

    protected Customer customer;

    public CustomerFragment(Customer customer){
        this.customer = customer;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.customer_fragment, container, false);

        TextView tempV = view.findViewById(R.id.customerName);
        tempV.setText(customer.getName());
        tempV = view.findViewById(R.id.address_view);
        tempV.setText(customer.getAddress());
        //tempV = view.findViewById(R.id.city_View);
        //tempV.setText(customer.getCity());
        tempV = view.findViewById(R.id.phoneNumber_view);
        tempV.setText(customer.getPhoneNumber());
        return view;
    }

}
