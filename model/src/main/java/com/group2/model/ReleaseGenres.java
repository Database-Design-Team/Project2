package com.group2.model;

/**
 * @author Timothy
 */
public class ReleaseGenres {
    int genre;
    int release;

    public ReleaseGenres() {
    }

    public ReleaseGenres(int genre, int release) {
        this.genre = genre;
        this.release = release;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public int getRelease() {
        return release;
    }

    public void setRelease(int release) {
        this.release = release;
    }
}
