package edu.harrisburgu.jasonb75.simplelistwithpics;

public class User {

    private String name;
    private String phone;

    private String imageURL;

    public User(String name, String phone, String imageURL) {
        this.name = name;
        this.phone = phone;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
    public String getImageURL() {
        return imageURL;
    }

}
