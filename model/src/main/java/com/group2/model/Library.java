package com.group2.model;

import java.sql.Date;

/**
 * @author Timothy
 */
public class Library {
    int song;
    String owner;
    Date dateAdded;

    /**
     * constructor with no parameters
     */
    public Library() {
    }

    /**
     * constructor with all parameters
     * @param song the unique integer value of a song
     * @param owner the name of the library's owner
     * @param dateAdded the date that this song was added to the library
     */
    public Library(int song, String owner, Date dateAdded) {
        this.song = song;
        this.owner = owner;
        this.dateAdded = dateAdded;
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
    public Date getDateAdded() {
        return dateAdded;
    }

    /**
     * setter for date added
     * @param dateAdded the date a song was added to the library
     */
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
