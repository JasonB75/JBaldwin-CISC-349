package edu.harrisburgu.jasonb75.journalapp2;

import android.graphics.Bitmap;

import org.json.JSONObject;

public class JournalEntry {

    private int mood;
    private String date;
    private String time;
    private int energy;
    private int socialBattery;
    private int sleepQuality;
    private int sleepAmount;
    private int stomachFeeling;
    private int lastEaten;
    private String notes;
    private String image;

    private int id; // THe id from the database

    private boolean inDB; // whether the entry is in the database

    public JournalEntry(int mood, String date, String time){
        setMood(mood);
        setDate(date);
        setTime(time);
    }

    //A function to set all the values not done in the contructor
    //Intended for the save button used in HealthCollectionActivity
    // instead of calling each setter in the activity
    public void setNonInitialValues(int energy, int socialBattery, int sleepQuality, int sleepAmount,
                               int stomachFeeling, int lastEaten, String notes, String img){
        setEnergy(energy);
        setSocialBattery(socialBattery);
        setSleepQuality(sleepQuality);
        setSleepAmount(sleepAmount);
        setStomachFeeling(stomachFeeling);
        setLastEaten(lastEaten);
        setNotes(notes);
        setImage(img);
    }

    public void setFromJson(JSONObject jsonObject){

    }

    public String outputEverything(){
        String output = "";
        output = output + ("Date: " + getDate());
        output = output + ("\ntime : " + getTime());
        output = output + ("\nmood: " + getMood());
        output = output + ("\nenergy: " + getEnergy());
        output = output + ("\nsocial battery: : " + getSocialBattery());
        output = output + ("\nsleep quality: : " + getSleepQuality());
        output = output + ("\nsleep amount: " + getSleepAmount());
        output = output + ("\nStomach: " + getStomachFeeling());
        output = output + ("\nHours since eated: " + getLastEaten());
        output = output + ("\nnotes: " + getNotes());
        output = output + ("\nimg string: " + getImage().substring(0, 20));
        return output;
    }

    public


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

    public int getLastEaten() {
        return lastEaten;
    }

    public void setLastEaten(int lastEaten) {
        this.lastEaten = lastEaten;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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

    public int getSocialBattery() {
        return socialBattery;
    }

    public void setSocialBattery(int socialBattery) {
        this.socialBattery = socialBattery;
    }

    public int getSleepQuality() {
        return sleepQuality;
    }

    public void setSleepQuality(int sleepQuality) {
        this.sleepQuality = sleepQuality;
    }

    public int getSleepAmount() {
        return sleepAmount;
    }

    public void setSleepAmount(int sleepAmount) {
        this.sleepAmount = sleepAmount;
    }

    public int getStomachFeeling() {
        return stomachFeeling;
    }

    public void setStomachFeeling(int stomachFeeling) {
        this.stomachFeeling = stomachFeeling;
    }
}
