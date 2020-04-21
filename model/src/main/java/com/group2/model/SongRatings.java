package com.group2.model;

import org.jetbrains.annotations.Contract;

/**
 * @author Timothy Chandler
 * @version 1.0
 * @since 4/21/20
 * inside the package - com.group2.model
 */
public class SongRatings {
    int songId;
    String userName;
    int rating;

    /**
     * empty constructor
     */
    @Contract(pure = true)
    public SongRatings() {
    }

    /**
     * constructor with full, non-default parameters
     * @param songId the ID of the song rated
     * @param userName the user who rated the song
     * @param rating the rating of the song
     */
    @Contract(pure = true)
    public SongRatings(int songId, String userName, int rating) {
        this.songId = songId;
        this.userName = userName;
        this.rating = rating;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
