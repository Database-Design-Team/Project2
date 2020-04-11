package com.group2.model;

import java.sql.Time;

/**
 * @author Timothy
 */
public class SongStatistics {
    String user;
    Time time_stamp;
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
     * @param time_stamp the time when the song was listened to
     * @param rating user's rating, if any
     * @param song ID of the song listened to
     */
    public SongStatistics(String user, Time time_stamp, float rating, int song) {
        this.user = user;
        this.time_stamp = time_stamp;
        this.rating = rating;
        this.song = song;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Time getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(Time time_stamp) {
        this.time_stamp = time_stamp;
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
