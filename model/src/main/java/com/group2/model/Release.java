package com.group2.model;

import java.sql.Date;

public class Release {
    int releaseID;
    String title;
    int releaseType;
    Date dateCreated;
    int numberOfTracks;

    /**
     * constructor with no parameters
     */
    public Release() {
    }

    /**
     * constructor with full parameters
     * @param releaseID unique ID number for release
     * @param title name of the release
     * @param releaseType type of the release
     * @param dateCreated the date of release
     * @param numberOfTracks the number of tracks
     */
    public Release(int releaseID, String title, int releaseType, Date dateCreated, int numberOfTracks) {
        this.releaseID = releaseID;
        this.title = title;
        this.releaseType = releaseType;
        this.dateCreated = dateCreated;
        this.numberOfTracks = numberOfTracks;
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

    /**
     * getter for release type
     * @return type of the release
     */
    public int getReleaseType() {
        return releaseType;
    }

    /**
     * setter for release type
     * @param releaseType type of the release
     */
    public void setReleaseType(int releaseType) {
        this.releaseType = releaseType;
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

    /**
     * getter for number of tracks
     * @return the number of tracks
     */
    public int getNumberOfTracks() {
        return numberOfTracks;
    }

    /**
     * setter for number of tracks
     * @param numberOfTracks the number of tracks
     */
    public void setNumberOfTracks(int numberOfTracks) {
        this.numberOfTracks = numberOfTracks;
    }
}
