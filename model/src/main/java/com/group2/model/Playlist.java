package com.group2.model;

import java.sql.Date;

/**
 * @author Timothy
 */
public class Playlist {
    int playlist_id;
    Date dateCreated;
    boolean isPrivate;
    int numberOfSongs;
    String playlistName;
    String owner;

    /**
     * constructor with no parameters, initializing to defaults
     */
    public Playlist() {
        isPrivate = false;
        numberOfSongs = 0;
    }

    /**
     * constructor with full parameters
     * @param playlistID the unique ID number of the playlist
     * @param dateCreated the date this playlist was created on
     * @param isPrivate boolean indicating if the playlist is private
     * @param numberOfSongs the number of songs
     * @param playlistName the name of the playlist
     * @param owner the owner of the playlist
     */
    public Playlist(int playlistID, Date dateCreated, boolean isPrivate, int numberOfSongs, String playlistName, String owner) {
        this.playlist_id = playlistID;
        this.dateCreated = dateCreated;
        this.isPrivate = isPrivate;
        this.numberOfSongs = numberOfSongs;
        this.playlistName = playlistName;
        this.owner = owner;
    }

    /**
     * getter for playlist id
     * @return the ID of this playlist
     */
    public int getPlaylist_id() {
        return playlist_id;
    }

    /**
     * setter for playlist id
     * @param playlist_id the ID of this playlist
     */
    public void setPlaylist_id(int playlist_id) {
        this.playlist_id = playlist_id;
    }

    /**
     * getter for date created
     * @return the date this playlist was created
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * setter for date created
     * @param dateCreated the date this playlist was made
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    /**
     * getter for is private
     * @return boolean indicating if the playlist is private
     */
    public boolean isPrivate() {
        return isPrivate;
    }

    /**
     * setter for is private
     * @param isPrivate a boolean indicating if the playlist is private
     */
    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    /**
     * getter for number of songs
     * @return the amount of songs in the playlist
     */
    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    /**
     * setter for number of songs
     * @param numberOfSongs the number of songs in the playlist
     */
    public void setNumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }

    /**
     * getter for playlist name
     * @return the name of the playlist
     */
    public String getPlaylistName() {
        return playlistName;
    }

    /**
     * setter for playlist name
     * @param playlistName the name of the playlist
     */
    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    /**
     * getter for owner
     * @return the name of the owner of the playlist
     */
    public String getOwner() {
        return owner;
    }

    /**
     * setter for owner
     * @param owner the owner of the playlist
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }
}
