package com.group2.model;

import java.sql.Date;

/**
 * @author Timothy
 */
class Library {
    int song;
    String owner;
    Date date_added;

    /**
     * constructor with no parameters
     */
    public Library() {
    }

    /**
     * constructor with all parameters
     * @param song the unique integer value of a song
     * @param owner the name of the library's owner
     * @param date_added the date that this song was added to the library
     */
    public Library(int song, String owner, Date date_added) {
        this.song = song;
        this.owner = owner;
        this.date_added = date_added;
    }

    /**
     * getter for song
     * @return song's id number
     */
    public int getSong() {
        return song;
    }

    /**
     * setter for the song's ID number
     * @param song the ID number of the song
     */
    public void setSong(int song) {
        this.song = song;
    }

    /**
     * getter for owner
     * @return owner's name
     */
    public String getOwner() {
        return owner;
    }

    /**
     * setter for owner
     * @param owner the owner's name
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * getter for date added
     * @return the date a song was added to the library
     */
    public Date getDate_added() {
        return date_added;
    }

    /**
     * setter for date added
     * @param date_added the date a song was added to the library
     */
    public void setDate_added(Date date_added) {
        this.date_added = date_added;
    }
}
