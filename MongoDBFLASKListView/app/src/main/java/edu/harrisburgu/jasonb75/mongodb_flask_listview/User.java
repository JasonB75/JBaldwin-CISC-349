package edu.harrisburgu.jasonb75.mongodb_flask_listview;

import org.json.JSONException;
import org.json.JSONObject;

public class User {

    private String name;
    private String phone;
    private String ssn;

    public User(JSONObject jsonData) throws JSONException {
        this.setName(jsonData.getString("name"));
        this.setSsn(jsonData.getString("ssn"));
        this.setPhone(jsonData.getString("phone"));

    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
