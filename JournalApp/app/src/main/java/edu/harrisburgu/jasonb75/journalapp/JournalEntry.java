package edu.harrisburgu.jasonb75.journalapp;

import android.graphics.Bitmap;

public class JournalEntry {

    private int mood;
    private String date;
    private String time;
    private int energy;
    private int eaten;
    private String comments;
    private Bitmap image;

    private int id; // THe id from the database

    private boolean inDB; // whether the entry is in the database

    public JournalEntry(int mood, String date, String time){
        setMood(mood);
        setDate(date);
        setTime(time);
    }


    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getEaten() {
        return eaten;
    }

    public void setEaten(int eaten) {
        this.eaten = eaten;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isInDB() {
        return inDB;
    }

    public void setInDB(boolean inDB) {
        this.inDB = inDB;
    }
}
