package com.group2.webapp.controllers;

import com.group2.dao.PlaylistSongsDao;
import com.group2.model.PlaylistSongs;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.Set;

/**
 * @author Timothy Chandler
 * @version 1.0
 * @since 4/18/20
 * inside the package - com.group2.webapp.controllers
 */
@Controller
public class PlaylistSongsController {
    private PlaylistSongsDao dao = new PlaylistSongsDao();

    public PlaylistSongsController() throws SQLException {}

    /**
     * adds a song to a playlist
     * @param song the ID of a song in the audio table getting added to the playlist
     * @param playlist the ID of the playlist the song is added to
     * @return http response object
     */
    @PostMapping("/playlist-songs")
    @ResponseBody
    public ResponseEntity<Boolean> addPlaylistSong(@RequestParam("song") int song, @RequestParam("playlist") int playlist) {
        try {
            dao.addPlaylistSong(song, playlist);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (SQLException e) {
            e.getMessage();
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }
    }

    /**
     * gets all the songs in a given playlist
     * @param playlist the ID of a playlist
     * @return http response object
     * @throws SQLException
     */
    @GetMapping("/playlist-songs")
    @ResponseBody
    public ResponseEntity<String> getPlaylistSongByPlaylist(@RequestParam Integer playlist) throws SQLException {
        Set<PlaylistSongs> playlistSongsList = dao.getPlaylistSongByPlaylist(playlist);
        JSONObject json = new JSONObject();
        for (PlaylistSongs song : playlistSongsList) {
            JSONObject temp = new JSONObject();
            temp.put(String.valueOf(song.getPlaylist()), song.getDateAdded());
            json.put(String.valueOf(song.getSong()), temp);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>(json.toString(), headers,  HttpStatus.OK);
    }
}