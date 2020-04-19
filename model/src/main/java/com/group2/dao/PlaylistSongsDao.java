package com.group2.dao;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Timothy Chandler
 * @version 1.0
 * @since 4/18/20
 * inside the package - com.group2.dao
 */
public class PlaylistSongsDao extends AbstractBaseDao {
    public PlaylistSongsDao() throws SQLException {
        super();
    }

    /**
     * Adds a playlist to the playlist_songs table
     * @param song the ID of a song in the audio table getting added to the playlist
     * @param playlist the ID of the playlist the song is added to
     * @throws SQLException on errors interacting with the database
     */
    public void addPlaylistSong(@NotNull int song, @NotNull int playlist) throws SQLException {
        String SQL = "INSERT INTO playlist_songs(song, playlist) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, song);
        ps.setInt(2, song);
        ps.executeUpdate();
    }

    /**
     * get all the playlists a given song is in
     * @param song the ID of a {@code song} in the audio table
     * @return a {@code JSONObject} representing the playlists a given song is saved in
     * @throws SQLException on errors interacting with the database
     */
    public JSONObject getPlaylistSongBySongID(@RequestBody int song) throws SQLException {
        String SQL = "SELECT * FROM playlist_songs WHERE song = ?";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, song);
        ResultSet rs = ps.executeQuery();
        JSONObject playlists = new JSONObject();
        while (rs.next()){
            playlists.put("song", rs.getInt(1));
            playlists.put("playlist", rs.getInt(2));
            playlists.put("date_added", rs.getDate(3));
        }
        return playlists;
    }

    /**
     * get all the songs in a given playlist
     * @param playlist the ID of a {@code playlist} in the playlist
     * @return a {@code JSONObject} representing the songs saved in a given playlist
     * @throws SQLException on errors interacting with the database
     */
    public JSONObject getPlaylistSongByPlaylist(@RequestBody int playlist) throws SQLException {
        String SQL = "SELECT * FROM playlist_songs WHERE playlist = ?";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, playlist);
        ResultSet rs = ps.executeQuery();
        JSONObject songs = new JSONObject();
        while (rs.next()){
            songs.put("song", rs.getInt(1));
            songs.put("playlist", rs.getInt(2));
            songs.put("date_added", rs.getDate(3));
        }
        return songs;
    }
}