package com.group2.model;


/**
 * @author Timothy
 */
public class Song {
    int song_id;
    float aggregate_popularity;
    String song_name;
    int song_length;
    byte[] audio_file;
    int musician;
    boolean isDeleted;

    public Song() {}

    public Song(String song_name, int musician, boolean isDeleted) {

        this.song_name = song_name;
        this.musician = musician;
        this.isDeleted = isDeleted;
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
}
