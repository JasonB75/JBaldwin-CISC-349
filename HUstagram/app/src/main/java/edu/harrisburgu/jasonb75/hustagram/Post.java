package edu.harrisburgu.jasonb75.hustagram;

import android.graphics.Bitmap;

public class Post {

    private Bitmap image;
    private String comment;


    public Post(Bitmap image, String comment){
        this.setImage(image);
        this.setComment(comment);
    }


    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
