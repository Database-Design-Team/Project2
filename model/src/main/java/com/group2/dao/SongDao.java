package com.group2.dao;

import com.group2.model.Song;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.*;

public class SongDao extends AbstractBaseDao {

    public SongDao() throws SQLException {
        super();
    }

    // public void addSongFile(Song songFile) throws SQLException {
    //     String SQL = "INSERT INTO song(song_id, song_name, song_length, audio_file, musician) VALUES (?,?,?,?,?,?,?)";
    //     PreparedStatement ps = conn.prepareStatement(SQL);
    //     ps.setInt(1, songFile.getSong_id());
    //     ps.setString(2, songFile.getSong_name());
    //     ps.setInt(3, songFile.getSong_length());
    //     ps.setBytes(4, songFile.getaudio_file());
    //     ps.setInt(5, songFile.getMusician());
    //     ps.executeUpdate();
    // }

    public void AddSongFile(MultipartFile file) throws SQLException, IOException {
        String SQL = "INSERT INTO audio (audio_file, audio_name) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setBytes(1, file.getBytes());
        ps.setString(2, "song2.mp3");
        ps.executeUpdate();
        ps.close();
    }

    public byte[] GetSongFile() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT audio_file FROM audio WHERE audio_name = ?");
        ps.setString(1, "song2.mp3");
        ResultSet rs = ps.executeQuery();
        byte[] songBytes = null;
        if (rs.next()) {
            songBytes = rs.getBytes(1);
            rs.close();
        }
        ps.close();
        return songBytes;
    }

}
