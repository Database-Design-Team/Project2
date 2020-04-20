package com.group2.model;

import org.jetbrains.annotations.Contract;

/**
 * @author Timothy
 */
public class ReleaseGenres {
    int genre;
    int release;

    /**
     * empty constructor
     */
    @Contract(pure = true)
    public ReleaseGenres() {
    }

    /**
     * constructor will full parameters
     * @param genre the ID of a genre of the release
     * @param release the ID fo the release
     */
    @Contract(pure = true)
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
