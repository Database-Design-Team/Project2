package com.group2.model;

import java.sql.Date;

/**
 * @author Timothy
 */
public class Playlist {
    int playlist_id;
    Date date_created;
    boolean is_private;
    int number_of_songs;
    String playlist_name;
    String owner;

    /**
     * constructor with no parameters, initializing to defaults
     */
    public Playlist() {
        is_private = false;
        number_of_songs = 0;
    }

    /**
     * constructor with full parameters
     * @param playlist_id the unique ID number of the playlist
     * @param date_created the date this playlist was created on
     * @param is_private boolean indicating if the playlist is private
     * @param number_of_songs the number of songs
     * @param playlist_name the name of the playlist
     * @param owner the owner of the playlist
     */
    public Playlist(int playlist_id, Date date_created, boolean is_private, int number_of_songs, String playlist_name, String owner) {
        this.playlist_id = playlist_id;
        this.date_created = date_created;
        this.is_private = is_private;
        this.number_of_songs = number_of_songs;
        this.playlist_name = playlist_name;
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
    public Date getDate_created() {
        return date_created;
    }

    /**
     * setter for date created
     * @param date_created the date this playlist was made
     */
    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    /**
     * getter for is private
     * @return boolean indicating if the playlist is private
     */
    public boolean isIs_private() {
        return is_private;
    }

    /**
     * setter for is private
     * @param is_private a boolean indicating if the playlist is private
     */
    public void setIs_private(boolean is_private) {
        this.is_private = is_private;
    }

    /**
     * getter for number of songs
     * @return the amount of songs in the playlist
     */
    public int getNumber_of_songs() {
        return number_of_songs;
    }

    /**
     * setter for number of songs
     * @param number_of_songs the number of songs in the playlist
     */
    public void setNumber_of_songs(int number_of_songs) {
        this.number_of_songs = number_of_songs;
    }

    /**
     * getter for playlist name
     * @return the name of the playlist
     */
    public String getPlaylist_name() {
        return playlist_name;
    }

    /**
     * setter for playlist name
     * @param playlist_name the name of the playlist
     */
    public void setPlaylist_name(String playlist_name) {
        this.playlist_name = playlist_name;
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
