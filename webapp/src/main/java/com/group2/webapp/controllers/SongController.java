package com.group2.webapp.controllers;

import com.group2.dao.SongDao;
import com.group2.model.Song;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

@Controller
public class SongController {

    private final SongDao dao = new SongDao();

    public SongController() throws SQLException {
    }

    @RequestMapping(value = "/upload-files", headers = "content-type=multipart/*", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("song_name") String song_name, @RequestParam("musician") int musician, @RequestParam("genre") int genre) throws IOException {
        try {
            Song song = new Song(song_name, musician, false, genre);
            dao.AddSongFile(multipartFile, song);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (SQLException e) {
            e.getMessage();
            return new ResponseEntity<Boolean>(false, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping(value="/download-files")
    public ResponseEntity<byte[]> getFile(@RequestParam("song_id") int song_id, @RequestParam("username") String username) throws SQLException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        return new ResponseEntity<>(dao.GetSongFile(song_id, username), headers, HttpStatus.OK);
    }

    @GetMapping(value="/getAllSongs")
    public ResponseEntity<String> getAllSongs() throws SQLException {
        Set<Song> songList = dao.getAllSongs();
        HashMap<Integer, String> artistNames = dao.getAristNames();
        JSONObject json = new JSONObject();
        for (Song song : songList) {
            String song_artist = artistNames.get(song.getMusician()) + "|" + song.getSong_name();
            json.put(String.valueOf(song.getSong_id()), song_artist);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>(json.toString(), headers, HttpStatus.OK);
    }

    @PutMapping("/delete-song")
    public ResponseEntity<Boolean> deleteSong(@RequestParam("song_id") int song_id) {
        try {
            dao.deleteSong(song_id);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (SQLException e) {
            e.getMessage();
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }
    }

    @PutMapping("/update-song-title")
     @ResponseBody
     public ResponseEntity<Boolean> updateSongTitle(@RequestParam("song_id") int songID, @RequestParam("songName") String songName) {
         try {
             dao.changeSongTitleById(songID, songName);
             return new ResponseEntity<Boolean>(true, HttpStatus.OK);
         } catch(SQLException e) {
             e.getMessage();
             return new ResponseEntity<Boolean>(false, HttpStatus.OK);
         }

     }
}