package com.group2.dao;

import com.group2.model.Song;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.springframework.web.multipart.MultipartFile;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class SongDao extends AbstractBaseDao {

    public SongDao() throws SQLException {
        super();
    }

    /**
     * adds a song to the {@code audio} table
     * @param file the audio file of the song getting uploaded
     * @param song the {@code song} object holding the other attributes of a song
     * @throws SQLException
     * @throws IOException
     */
    public void AddSongFile(@NotNull MultipartFile file, @NotNull Song song) throws SQLException, IOException {
        String SQL = "INSERT INTO audio (audio_file, song_name, musician, deleted) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setBytes(1, file.getBytes());
        ps.setString(2, song.getSong_name());
        ps.setInt(3, song.getMusician());
        ps.setBoolean(4, false);
        ps.executeUpdate();
        ps.close();
    }

    /**
     * gets an audio file from the database
     * @param song_id the ID of the song getting returned
     * @return the requested audio file from the database
     * @throws SQLException on errors interacting with the database
     */
    public byte[] GetSongFile(int song_id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT audio_file FROM audio WHERE audio_id = ?");
        ps.setInt(1, song_id);
        ResultSet rs = ps.executeQuery();
        byte[] songBytes = null;
        if (rs.next()) {
            songBytes = rs.getBytes(1);
            rs.close();
        }
        ps.close();
        return songBytes;
    }

    /**
     * gets a list of all the songs stored
     * @return an array of JSONs, holding the name and ID of the songs in the audio table
     * @throws SQLException on errors interacting with the database
     */
    public JSONArray getAllSongs() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT song_name, audio_id FROM public.audio");
        ResultSet rs = ps.executeQuery();
        return getJsonArray(rs);
    }
}