package com.group2.model;

import java.sql.Date;

/**
 * @author Timothy
 */
class Artist {
    int artistID;
    String artistName;
    Date dateFormed;

    /**
     * constructor without parameters
     */
    public Artist() {
    }

    /**
     * constructor with all parameters
     * @param artistID a unique integer id assigned to every artist
     * @param artistName the name of the artist
     * @param dateFormed the date an artist became active
     */
    public Artist(int artistID, String artistName, Date dateFormed) {
        this.artistID = artistID;
        this.artistName = artistName;
        this.dateFormed = dateFormed;
    }

    /**
     * getter for date formed
     * @return the date an artist became active
     */
    public Date getDateFormed() {
        return dateFormed;
    }

    /**
     * setter for date formed
     * @param dateFormed the date an artist became active
     */
    public void setDateFormed(Date dateFormed) {
        this.dateFormed = dateFormed;
    }

    /**
     * getter for artist_name
     * @return the name of the artist
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * setter for artist name
     * @param artistName the name of the artist
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     * getter for artist_id
     * @return the integer value of the artist's unique ID
     */
    public int getArtistID() {
        return artistID;
    }

    /**
     * setter for artist_id
     * @param artistID the integer value of the artist's unique ID
     */
    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }
}
