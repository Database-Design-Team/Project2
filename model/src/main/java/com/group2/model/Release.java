package com.group2.model;

import java.sql.Date;

public class Release {
    int release_id;
    String title;
    int release_type;
    Date date_created;
    int number_of_tracks;

    /**
     * constructor with no parameters
     */
    public Release() {
    }

    /**
     * constructor with full parameters
     * @param release_id unique ID number for release
     * @param title name of the release
     * @param release_type type of the release
     * @param date_created the date of release
     * @param number_of_tracks the number of tracks
     */
    public Release(int release_id, String title, int release_type, Date date_created, int number_of_tracks) {
        this.release_id = release_id;
        this.title = title;
        this.release_type = release_type;
        this.date_created = date_created;
        this.number_of_tracks = number_of_tracks;
    }

    /**
     * getter for release id
     * @return unique ID number for release
     */
    public int getRelease_id() {
        return release_id;
    }

    /**
     * setter for release id
     * @param release_id unique ID number for release
     */
    public void setRelease_id(int release_id) {
        this.release_id = release_id;
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
    public int getRelease_type() {
        return release_type;
    }

    /**
     * setter for release type
     * @param release_type type of the release
     */
    public void setRelease_type(int release_type) {
        this.release_type = release_type;
    }

    /**
     * getter for date created
     * @return the date of release
     */
    public Date getDate_created() {
        return date_created;
    }

    /**
     * setter for date created
     * @param date_created the date of release
     */
    public void setDate_created(Date date_created) {
        this.date_created = date_created;
    }

    /**
     * getter for number of tracks
     * @return the number of tracks
     */
    public int getNumber_of_tracks() {
        return number_of_tracks;
    }

    /**
     * setter for number of tracks
     * @param number_of_tracks the number of tracks
     */
    public void setNumber_of_tracks(int number_of_tracks) {
        this.number_of_tracks = number_of_tracks;
    }
}
