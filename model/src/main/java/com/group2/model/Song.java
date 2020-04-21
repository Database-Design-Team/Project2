package com.group2.model;


import org.jetbrains.annotations.Contract;

/**
 * @author Timothy
 */
public class Song {
    int song_id;
    float aggregate_popularity;
    String song_name;
    byte[] audio_file;
    int musician;
    boolean isDeleted;
    int genre;

    /**
     * constructor with no parameters, setting default isDeleted
     */
    @Contract(pure = true)
    public Song() {
        isDeleted = false;
    }

    /**
     * constructor with partial parameters
     * @param song_name the name of the song
     * @param musician the ID of the musician
     * @param isDeleted determines if the song is flagged as deleted
     */
    @Contract(pure = true)
    public Song(String song_name, int musician, boolean isDeleted, int genre) {
        this.song_name = song_name;
        this.musician = musician;
        this.isDeleted = isDeleted;
        this.genre = genre;
    }

    /**
     * constructor with all the non-default parameters
     * @param song_name the name of the song
     * @param audio_file the literal audio file
     * @param musician the ID of the musician who produced the song
     */
    @Contract(pure = true)
    public Song(String song_name, byte[] audio_file, int musician) {
        this.song_name = song_name;
        this.audio_file = audio_file;
        this.musician = musician;
    }

    /**
     * constructor with full parameters
     * @param song_id the ID of the song
     * @param aggregate_popularity the average popularity of the song
     * @param song_name the name of the song
     * @param audio_file the literal audio file
     * @param musician the ID of the musician who produced the song
     * @param isDeleted determines if the song is flagged as deleted
     */
    @Contract(pure = true)
    public Song(int song_id, float aggregate_popularity, String song_name, byte[] audio_file, int musician, boolean isDeleted) {
        this.song_id = song_id;
        this.aggregate_popularity = aggregate_popularity;
        this.song_name = song_name;
        this.audio_file = audio_file;
        this.musician = musician;
        this.isDeleted = isDeleted;
    }

    /**
     * constructor with partial parameters
     * @param song_id the ID of the song
     * @param aggregate_popularity the average popularity of the song
     * @param song_name the name of the song
     */
    @Contract(pure = true)
    public Song(int song_id, float aggregate_popularity, String song_name) {
        this.song_id = song_id;
        this.aggregate_popularity = aggregate_popularity;
        this.song_name = song_name;
    }

    /**
     * constructor with partial parameters
     * @param song_id the ID of the song
     * @param song_name the name of the song
     */
    @Contract(pure = true)
    public Song(int song_id, String song_name) {
        this.song_id = song_id;
        this.song_name = song_name;
    }

    public int getSong_id() {
        return song_id;
    }

    public void setSong_id(int song_id) {
        this.song_id = song_id;
    }

    public float getAggregate_popularity() {
        return aggregate_popularity;
    }

    public void setAggregate_popularity(float aggregate_popularity) {
        this.aggregate_popularity = aggregate_popularity;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public byte[] getAudio_file() {
        return audio_file;
    }

    public void setAudio_file(byte[] audio_file) {
        this.audio_file = audio_file;
    }

    public int getMusician() {
        return musician;
    }

    public void setMusician(int musician) {
        this.musician = musician;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }
}
