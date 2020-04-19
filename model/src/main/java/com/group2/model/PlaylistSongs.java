package com.group2.model;

import java.sql.Date;

/**
 * @author Timothy
 */
public class PlaylistSongs {
    int song;
    int playlist;
    Date dateAdded;

    /**
     * constructor with no parameters
     */
    public PlaylistSongs() {
    }

    /**
     * constructor with non-defaulted parameters
     * @param song the ID of a song in the audio table
     * @param playlist the ID of a playlist in the playlist table
     */
    public PlaylistSongs(int song, int playlist) {
        this.song = song;
        this.playlist = playlist;
    }

    /**
     * constructor with full parameters
     * @param song the ID of the song getting added
     * @param playlist the ID of the playlist getting added to
     * @param dateAdded the date the song was added to the playlist
     */
    public PlaylistSongs(int song, int playlist, Date dateAdded) {
        this.song = song;
        this.playlist = playlist;
        this.dateAdded = dateAdded;
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
    public Date getDateAdded() {
        return dateAdded;
    }

    /**
     * setter for date added
     * @param dateAdded the date the song was added
     */
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
