package edu.harrisburgu.jasonb75.customerlist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    protected String _id;
    protected String name;
    protected String address;
    protected String phone;
    protected List<String> comments;

    public Customer(JSONObject jsonData) throws JSONException {
        if (jsonData.has("_id")) {
            this.setId(jsonData.getString("_id"));

        }
        this.setName(jsonData.getString("name"));
        this.setAddress(jsonData.getString("address"));
        this.setPhone(jsonData.getString("phone"));
        if (jsonData.has("comments")){
            JSONArray cmnts = jsonData.getJSONArray("comments");
            if (null != cmnts){
                comments = new ArrayList<String>();
                for (int i = 0; i < cmnts.length(); i++){
                    comments.add(cmnts.get(i).toString());

                }
            }
        }
        else {
            comments = new ArrayList<String>();

        }
    }

    private void setPhone(String phone) {
        this.phone = phone;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    private void setId(String id) {
        this._id = id;

    }


}
