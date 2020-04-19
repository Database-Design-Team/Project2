package com.group2.dao;

import com.group2.model.Song;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibraryDao extends AbstractBaseDao {

    public LibraryDao() throws SQLException {
    }


    public void addToLibrary(int songID, String username) throws SQLException {
        String SQL = "INSERT INTO library(song, owner) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, songID);
        ps.setString(2, username);
        ps.executeUpdate();
        ps.close();
    }

    public List<Song> getUserLibrary(String username) throws SQLException {
        String SQL = "SELECT song_name, audio_id, artist FROM audio WHERE audio_id IN (" +
                "SELECT song FROM library WHERE owner = ?);";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        List<Song> songList = new ArrayList<>();
        while(rs.next()) {
            Song song = new Song();
            song.setSong_id(rs.getInt("audio_id"));
            song.setSong_name(rs.getString("song_name"));
            song.setMusician(rs.getInt("audio_id"));
            songList.add(song);
        }
        return songList;
    }

}
