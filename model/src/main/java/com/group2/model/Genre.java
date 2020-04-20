package com.group2.model;

import org.jetbrains.annotations.Contract;

/**
 * @author Timothy
 */
public class Genre {
    int genreID;
    String genreTitle;

    /**
     * constructor without parameters
     */
    @Contract(pure = true)
    public Genre() {
    }

    /**
     * constructor with all parameters
     * @param genreID the unique integer id of a genre
     * @param genreTitle the name of a genre
     */
    @Contract(pure = true)
    public Genre(int genreID, String genreTitle) {
        this.genreID = genreID;
        this.genreTitle = genreTitle;
    }

    /**
     * getter for genre_id
     * @return the unique integer ID number of a genre
     */
    public int getGenreID() {
        return genreID;
    }

    /**
     * setter for genre_id
     * @param genreID the ID number of the genre
     */
    public void setGenreID(int genreID) {
        this.genreID = genreID;
    }

    /**
     * getter for genre_title
     * @return the actual name of the genre
     */
    public String getGenreTitle() {
        return genreTitle;
    }

    /**
     * setter for the genre title
     * @param genreTitle the actual name of the genre
     */
    public void setGenreTitle(String genreTitle) {
        this.genreTitle = genreTitle;
    }
}
