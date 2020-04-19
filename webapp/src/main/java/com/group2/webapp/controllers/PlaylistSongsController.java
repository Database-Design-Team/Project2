package com.group2.webapp.controllers;

import com.group2.dao.PlaylistSongsDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

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
     * get the playlists a given song is in
     * @param song the ID of a song in the audio table
     * @return http response object
     */
    @GetMapping("/playlist-songs")
    @ResponseBody
    public ResponseEntity getPlaylistSongBySongID(@RequestParam Integer song) {
        try {
            return new ResponseEntity<>(dao.getPlaylistSongBySongID(song), HttpStatus.OK);
        } catch(SQLException e) {
            return new ResponseEntity(false, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * gets all the songs in a given playlist
     * @param playlist the ID of a playlist
     * @return http response object
     */
    @GetMapping("/playlist-songs")
    @ResponseBody
    public ResponseEntity getPlaylistSongByPlaylist(@RequestParam Integer playlist) {
        try {
            return new ResponseEntity<>(dao.getPlaylistSongByPlaylist(playlist), HttpStatus.OK);
        } catch(SQLException e) {
            return new ResponseEntity(false, HttpStatus.NOT_FOUND);
        }
    }
}