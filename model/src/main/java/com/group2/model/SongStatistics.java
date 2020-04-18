package com.group2.model;

import java.sql.Time;

/**
 * @author Timothy
 */
public class SongStatistics {
    String user;
    Time timeStamp;
    float rating;
    int song;

    /**
     * empty constructor
     */
    public SongStatistics() {
    }

    /**
     * constructor with full parameters
     * @param user the name of the listener
     * @param timeStamp the time when the song was listened to
     * @param rating user's rating, if any
     * @param song ID of the song listened to
     */
    public SongStatistics(String user, Time timeStamp, float rating, int song) {
        this.user = user;
        this.timeStamp = timeStamp;
        this.rating = rating;
        this.song = song;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Time getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Time timeStamp) {
        this.timeStamp = timeStamp;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getSong() {
        return song;
    }

    public void setSong(int song) {
        this.song = song;
    }
}
