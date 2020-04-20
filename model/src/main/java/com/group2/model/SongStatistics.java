package com.group2.model;

import org.jetbrains.annotations.Contract;
import java.sql.Date;

/**
 * @author Timothy
 */
public class SongStatistics {
    String user;
    Date timeStamp;
    int rating;
    int song;

    /**
     * empty constructor
     */
    @Contract(pure = true)
    public SongStatistics() {
    }

    /**
     * constructor with non-default attributes
     * @param user the username of the user
     * @param song the ID of the song
     */
    @Contract(pure = true)
    public SongStatistics(String user, int song) {
        this.user = user;
        this.song = song;
    }

    /**
     * constructor with full parameters
     * @param user the name of the listener
     * @param timeStamp the time when the song was listened to
     * @param rating user's rating, if any
     * @param song ID of the song listened to
     */
    @Contract(pure = true)
    public SongStatistics(String user, Date timeStamp, int rating, int song) {
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

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getSong() {
        return song;
    }

    public void setSong(int song) {
        this.song = song;
    }
}
