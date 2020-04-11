package com.group2.model;

import java.sql.Date;

/**
 * @author Timothy
 */
public class PlaylistSongs {
    int song;
    int playlist;
    Date date_added;

    /**
     * constructor with no parameters
     */
    public PlaylistSongs() {
    }

    /**
     * constructor with full parameters
     * @param song the ID of the song getting added
     * @param playlist the ID of the playlist getting added to
     * @param date_added the date the song was added to the playlist
     */
    public PlaylistSongs(int song, int playlist, Date date_added) {
        this.song = song;
        this.playlist = playlist;
        this.date_added = date_added;
    }

    /**
     * getter for song
     * @return the ID of the song
     */
    public int getSong() {
        return song;
    }

    /**
     * setter for song
     * @param song the ID of the song
     */
    public void setSong(int song) {
        this.song = song;
    }
    /**
     * getter for playlist
     * @return the ID of the playlist
     */
    public int getPlaylist() {
        return playlist;
    }

    /**
     * setter for playlist
     * @param playlist the ID of the playlist
     */
    public void setPlaylist(int playlist) {
        this.playlist = playlist;
    }

    /**
     * getter for date added
     * @return the date the song was added
     */
    public Date getDate_added() {
        return date_added;
    }

    /**
     * setter for date added
     * @param date_added the date the song was added
     */
    public void setDate_added(Date date_added) {
        this.date_added = date_added;
    }
}
