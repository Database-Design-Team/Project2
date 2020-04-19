package com.group2.dao;

import com.group2.model.Song;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class SongDao extends AbstractBaseDao {

    public SongDao() throws SQLException {
        super();
    }

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

    public HashMap<Integer, String> getAllSongs() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT song_name, audio_id FROM public.audio");
        ResultSet rs = ps.executeQuery();
        HashMap<Integer, String> songList = new HashMap<Integer, String>();
        while (rs.next()) {
            songList.put(rs.getInt(2), rs.getString(1));
        }
        rs.close();
        ps.close();

        return songList;

    }

    // public JSONObject getAllSongs() throws SQLException {
    //     PreparedStatement ps = conn.prepareStatement("SELECT song_name, audio_id FROM public.audio");
    //     ResultSet rs = ps.executeQuery();
    //     JSONObject songList = new JSONObject();
    //     while (rs.next()) {
    //         songList.put("song_name", rs.getString(1));
    //         songList.put("audio_id", rs.getInt(2));
    //     }
    //     rs.close();
    //     ps.close();
    //     System.out.println(songList.toString());
    //     return songList;

    // }
}