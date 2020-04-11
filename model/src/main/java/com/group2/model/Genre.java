package com.group2.model;

/**
 * @author Timothy
 */
class Genre {
    int genre_id;
    String genre_title;

    /**
     * constructor without parameters
     */
    public Genre() {
    }

    /**
     * constructor with all parameters
     * @param genre_id the unique integer id of a genre
     * @param genre_title the name of a genre
     */
    public Genre(int genre_id, String genre_title) {
        this.genre_id = genre_id;
        this.genre_title = genre_title;
    }

    /**
     * getter for genre_id
     * @return the unique integer ID number of a genre
     */
    public int getGenre_id() {
        return genre_id;
    }

    /**
     * setter for genre_id
     * @param genre_id the ID number of the genre
     */
    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    /**
     * getter for genre_title
     * @return the actual name of the genre
     */
    public String getGenre_title() {
        return genre_title;
    }

    /**
     * setter for the genre title
     * @param genre_title the actual name of the genre
     */
    public void setGenre_title(String genre_title) {
        this.genre_title = genre_title;
    }
}
