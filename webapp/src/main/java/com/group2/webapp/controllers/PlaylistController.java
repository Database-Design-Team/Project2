package com.group2.webapp.controllers;
 
import com.group2.dao.PlaylistDao;
import com.group2.model.Playlist;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;
 
 @Controller
public class PlaylistController {
    public PlaylistController() throws SQLException {
    }
 
    private PlaylistDao dao = new PlaylistDao();
 
    @PostMapping("/playlist")
    @ResponseBody
    public ResponseEntity<Boolean> uploadPlaylist(@RequestBody Playlist playlist) throws IOException, SQLException {
        try{
            dao.addPlaylist(playlist);
            return new ResponseEntity<Boolean>(true,HttpStatus.OK);
        } catch (SQLException e){
            e.getMessage();
            return new ResponseEntity<Boolean>(false,HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/playlist-name")
    @ResponseBody
    public ResponseEntity<String> getPlaylistByName(@RequestParam("playlist_name") String playlist_name) throws SQLException {
        Set<Playlist> playlist = dao.getPlaylistByName(playlist_name);
        JSONObject json = new JSONObject();
        for (Playlist pl : playlist) {
            JSONObject temp = new JSONObject();
            temp.put(pl.getPlaylistName(), pl.getDateCreated());
            json.put(String.valueOf(pl.getPlaylist_id()), temp);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>(json.toString(), headers, HttpStatus.OK);
    }
 
}