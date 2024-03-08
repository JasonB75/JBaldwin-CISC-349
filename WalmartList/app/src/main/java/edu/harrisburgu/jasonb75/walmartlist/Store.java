package edu.harrisburgu.jasonb75.walmartlist;

import org.json.JSONException;
import org.json.JSONObject;

public class Store {

    protected String city;
    protected String phoneNumber;
    protected String name;
    protected String address;

public Store(JSONObject object) throws JSONException{
    this.name = object.getString("name");
    this.address = object.getString("street_address");
    this.phoneNumber = object.getString("phone_number_1");
    this.city = object.getString("city");
}

    public String getAddress() {
        return address;
    }
    public void setAddress(String address){
    this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }




}
