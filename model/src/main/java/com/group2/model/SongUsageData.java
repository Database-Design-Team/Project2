package com.group2.model;

/**
 * @author Timothy
 */
public class SongUsageData {
    int song;
    String user;

    /**
     * empty constructor
     */
    public SongUsageData() {
    }

    /**
     * constructor with full parameters
     * @param song the ID of the song
     * @param user the name of the user
     */
    public SongUsageData(int song, String user) {
        this.song = song;
        this.user = user;
    }

    public int getSong() {
        return song;
    }

    public void setSong(int song) {
        this.song = song;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
