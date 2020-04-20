package com.group2.model;

import org.jetbrains.annotations.Contract;

/**
 * @author Timothy
 */
public class Track {
    int release;
    int song;
    int trackNumber;

    /**
     * empty constructor
     */
    @Contract(pure = true)
    public Track() {
    }

    /**
     * constructor with full parameter
     * @param release ID of the release the song belongs to
     * @param song ID of the song in the release
     * @param trackNumber the track number in the release
     */
    @Contract(pure = true)
    public Track(int release, int song, int trackNumber) {
        this.release = release;
        this.song = song;
        this.trackNumber = trackNumber;
    }

    public int getRelease() {
        return release;
    }

    public void setRelease(int release) {
        this.release = release;
    }

    public int getSong() {
        return song;
    }

    public void setSong(int song) {
        this.song = song;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }
}
