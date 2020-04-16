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

    /**
     * empty constructor, respecting default values
     */
    public Song() {
        isDeleted = false;
    }

    /**
     * constructor with full parameters
     * @param songID the unique ID of the song
     * @param aggregate_popularity the popularity of the song
     * @param songName name of the song
     * @param song_length length of the song in seconds
     * @param audio_file specifies where the audio file can be found
     * @param musician the unique ID of the artist
     * @param isDeleted indicates if the song is flagged as deleted
     */
    public Song(int song_id, float aggregate_popularity, String song_name, int song_length, byte[] audio_file, int musician, boolean deleted) {
        this.song_id = song_id;
        this.aggregate_popularity = aggregate_popularity;
        this.song_name = song_name;
        this.song_length = song_length;
        this.audio_file = audio_file;
        this.musician = musician;
        this.isDeleted = isDeleted;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public float getAggregatePopularity() {
        return aggregatePopularity;
    }

    public void setAggregatePopularity(float aggregatePopularity) {
        this.aggregatePopularity = aggregatePopularity;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public int getSongLength() {
        return songLength;
    }

    public void setSongLength(int songLength) {
        this.songLength = songLength;
    }

    public byte[] getaudio_file() {
        return audio_file;
    }

    public void setaudio_file(byte[] audio_file) {
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
        this.isDeleted = deleted;
    }
}
