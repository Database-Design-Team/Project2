package com.group2.model;

import org.jetbrains.annotations.Contract;

import java.sql.Date;

public class Release {
    int releaseID;
    String title;
    Date dateCreated;
    int artistID;

    /**
     * constructor with no parameters
     */
    @Contract(pure = true)
    public Release() {
    }

    /**
     * constructor with full parameters
     * @param releaseID unique ID number for release
     * @param title name of the release
     * @param dateCreated the date of release
     */
    @Contract(pure = true)
    public Release(int releaseID, String title, Date dateCreated, int artistID) {
        this.releaseID = releaseID;
        this.title = title;
        this.dateCreated = dateCreated;
        this.artistID = artistID;
    }

    /**
     * getter for release id
     * @return unique ID number for release
     */
    public int getReleaseID() {
        return releaseID;
    }

    /**
     * setter for release id
     * @param releaseID unique ID number for release
     */
    public void setReleaseID(int releaseID) {
        this.releaseID = releaseID;
    }

    /**
     * getter for the title
     * @return name of the release
     */
    public String getTitle() {
        return title;
    }

    /**
     * setter for the title
     * @param title name of the release
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    /**
     * getter for date created
     * @return the date of release
     */
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * setter for date created
     * @param dateCreated the date of release
     */
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

}
