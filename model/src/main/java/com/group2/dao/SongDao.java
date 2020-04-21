package com.group2.dao;

import com.group2.model.Song;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class SongDao extends AbstractBaseDao {

    public SongDao() throws SQLException {
        super();
    }

    public void AddSongFile(MultipartFile file, Song song) throws SQLException, IOException {
        String SQL = "INSERT INTO audio (audio_file, song_name, artist, deleted, genre) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setBytes(1, file.getBytes());
        ps.setString(2, song.getSong_name());
        ps.setInt(3, song.getMusician());
        ps.setBoolean(4, false);
        ps.setInt(5, song.getGenre());
        ps.executeUpdate();
        ps.close();
    }

    public byte[] GetSongFile(int song_id, String username) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT audio_file FROM audio WHERE audio_id = ? AND deleted = false");
        ps.setInt(1, song_id);
        ResultSet rs = ps.executeQuery();
        byte[] songBytes = null;
        if (rs.next()) {
            songBytes = rs.getBytes(1);
            rs.close();
        }
        rs.close();
        ps.close();
        PreparedStatement listen = conn.prepareStatement("INSERT INTO song_listens (song, username) VALUES (?, ?)");
        listen.setInt(1, song_id);
        listen.setString(2, username);
        listen.executeUpdate();
        listen.close();
        return songBytes;
    }

    // public void insertListen(int song_id, String username) throws SQLException {
    //     PreparedStatement listen = conn.prepareStatement("INSERT INTO song_listens (song, username) VALUES (?, ?)");
    //     listen.setInt(1, song_id);
    //     listen.setString(2, username);
    //     listen.executeUpdate();
    //     listen.close();
    // }

    /**
     * gets the id and name of every available song
     * @return a {@code Set} of {@code Song} objects, initialized with only the song's ID and name
     * @throws SQLException on errors interacting with the database
     */
    public Set<Song> getAllSongs() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT audio_id, song_name, artist FROM public.audio WHERE deleted = false");
        ResultSet rs = ps.executeQuery();
        Set<Song> songList = new HashSet<Song>();
        while (rs.next()) {
            Song song = new Song(rs.getInt(1), rs.getString(2), rs.getInt(3));
            songList.add(song);
        }
        rs.close();
        ps.close();
        return songList;
    }

    public HashMap<Integer, String> getAristNames() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT artist_id, artist_name FROM artist");
        ResultSet rs = ps.executeQuery();
        HashMap<Integer, String> artistNames = new HashMap<Integer, String>();
        while (rs.next()) {
            artistNames.put(rs.getInt(1), rs.getString(2));
        }
        rs.close();
        ps.close();
        return artistNames;

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