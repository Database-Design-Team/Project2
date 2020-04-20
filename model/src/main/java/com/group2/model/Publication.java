package com.group2.model;

import org.jetbrains.annotations.Contract;

public class Publication {
    int artist;
    int release;

    /**
     * constructor without parameters
     */
    @Contract(pure = true)
    public Publication() {
    }

    /**
     * constructor with all parameters
     * @param artist one of the owners of the album
     * @param release the released
     */
    @Contract(pure = true)
    public Publication(int artist, int release) {
        this.artist = artist;
        this.release = release;
    }

    /**
     * getter for artist
     * @return the ID of an artist who owns the release
     */
    public int getArtist() {
        return artist;
    }

    /**
     * setter for artist
     * @param artist the ID of an artist who owns the release
     */
    public void setArtist(int artist) {
        this.artist = artist;
    }

    /**
     * getter for release ID
     * @return the ID of the album
     */
    public int getRelease() {
        return release;
    }

    /**
     * setter for release ID
     * @param release the ID of the album
     */
    public void setRelease(int release) {
        this.release = release;
    }
}
