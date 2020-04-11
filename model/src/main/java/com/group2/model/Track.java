package com.group2.model;

/**
 * @author Timothy
 */
public class Track {
    int release;
    int song;
    int track_number;

    /**
     * empty constructor
     */
    public Track() {
    }

    /**
     * constructor with full parameter
     * @param release ID of the release the song belongs to
     * @param song ID of the song in the release
     * @param track_number the track number in the release
     */
    public Track(int release, int song, int track_number) {
        this.release = release;
        this.song = song;
        this.track_number = track_number;
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

    public int getTrack_number() {
        return track_number;
    }

    public void setTrack_number(int track_number) {
        this.track_number = track_number;
    }
}
