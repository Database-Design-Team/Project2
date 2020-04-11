package com.group2.model;

/**
 * @author Timothy
 */
public class SongGenres {
    int song;
    int genre;

    /**
     * empty constructor
     */
    public SongGenres() {
    }

    /**
     * constructor with full parameters
     * @param song the ID of the song
     * @param genre the ID of one of the song's genres
     */
    public SongGenres(int song, int genre) {
        this.song = song;
        this.genre = genre;
    }

    public int getSong() {
        return song;
    }

    public void setSong(int song) {
        this.song = song;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }
}
