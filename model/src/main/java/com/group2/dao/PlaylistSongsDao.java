package com.group2.dao;

import com.group2.model.PlaylistSongs;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.RequestBody;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

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
        ps.close();
    }

    /**
     * get all the songs in a given playlist
     * @param playlistId the ID of a {@code playlist} in the playlist
     * @return a {@code Set} of rows returned by the query, representing the songs a given playlist has
     * @throws SQLException on errors interacting with the database
     */
    public Set<PlaylistSongs> getPlaylistSongByPlaylist(@RequestBody int playlistId) throws SQLException {
        String SQL = "SELECT * FROM playlist_songs WHERE playlist = ?";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, playlistId);
        ResultSet rs = ps.executeQuery();
        Set<PlaylistSongs> playlist = new HashSet<PlaylistSongs>();
        while (rs.next()){
            PlaylistSongs song = new PlaylistSongs(rs.getInt(1), rs.getInt(2), rs.getDate(3));
            playlist.add(song);
        }
        ps.close();
        rs.close();
        return playlist;
    }
}