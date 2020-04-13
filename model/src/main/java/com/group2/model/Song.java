package com.group2.model;

/**
 * @author Timothy
 */
public class Song {
    int songID;
    float aggregatePopularity;
    String songName;
    int songLength;
    String audioFile;
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
     * @param path_to_audio_file specifies where the audio file can be found
     * @param musician the unique ID of the artist
     * @param isDeleted indicates if the song is flagged as deleted
     */
    public Song(int songID, float aggregate_popularity, String songName, int song_length, String path_to_audio_file, int musician, boolean isDeleted) {
        this.songID = songID;
        this.aggregatePopularity = aggregate_popularity;
        this.songName = songName;
        this.songLength = song_length;
        this.audioFile = path_to_audio_file;
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

    public String getAudioFile() {
        return audioFile;
    }

    public void setAudioFile(String audioFile) {
        this.audioFile = audioFile;
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
