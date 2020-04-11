package com.group2.model;

/**
 * @author Timothy
 */
public class Song {
    int song_id;
    float aggregate_popularity;
    String song_name;
    int song_length;
    String path_to_audio_file;
    int musician;
    boolean deleted;

    /**
     * empty constructor
     */
    public Song() {
    }

    /**
     * constructor with full parameters
     * @param song_id the unique ID of the song
     * @param aggregate_popularity the popularity of the song
     * @param song_name name of the song
     * @param song_length length of the song in seconds
     * @param path_to_audio_file specifies where the audio file can be found
     * @param musician the unique ID of the artist
     * @param deleted indicates if the song is flagged as deleted
     */
    public Song(int song_id, float aggregate_popularity, String song_name, int song_length, String path_to_audio_file, int musician, boolean deleted) {
        this.song_id = song_id;
        this.aggregate_popularity = aggregate_popularity;
        this.song_name = song_name;
        this.song_length = song_length;
        this.path_to_audio_file = path_to_audio_file;
        this.musician = musician;
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

    public int getSong_length() {
        return song_length;
    }

    public void setSong_length(int song_length) {
        this.song_length = song_length;
    }

    public String getPath_to_audio_file() {
        return path_to_audio_file;
    }

    public void setPath_to_audio_file(String path_to_audio_file) {
        this.path_to_audio_file = path_to_audio_file;
    }

    public int getMusician() {
        return musician;
    }

    public void setMusician(int musician) {
        this.musician = musician;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
