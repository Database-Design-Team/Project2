package com.group2.model;

import java.sql.Date;

/**
 * @author Timothy
 */
class ArtistID {
    int artist_id;
    String artist_name;
    Date date_formed;

    /**
     * constructor without parameters
     */
    public ArtistID() {
    }

    /**
     * constructor with all parameters
     * @param artist_id a unique integer id assigned to every artist
     * @param artist_name the name of the artist
     * @param date_formed the date an artist became active
     */
    public ArtistID(int artist_id, String artist_name, Date date_formed) {
        this.artist_id = artist_id;
        this.artist_name = artist_name;
        this.date_formed = date_formed;
    }

    /**
     * getter for date formed
     * @return the date an artist became active
     */
    public Date getDate_formed() {
        return date_formed;
    }

    /**
     * setter for date formed
     * @param date_formed the date an artist became active
     */
    public void setDate_formed(Date date_formed) {
        this.date_formed = date_formed;
    }

    /**
     * getter for artist_name
     * @return the name of the artist
     */
    public String getArtist_name() {
        return artist_name;
    }

    /**
     * setter for artist name
     * @param artist_name the name of the artist
     */
    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    /**
     * getter for artist_id
     * @return the integer value of the artist's unique ID
     */
    public int getArtist_id() {
        return artist_id;
    }

    /**
     * setter for artist_id
     * @param artist_id the integer value of the artist's unique ID
     */
    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }
}
