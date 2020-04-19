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

@Controller
public class SongController {

    private final SongDao dao = new SongDao();

    public SongController() throws SQLException {
    }

    @RequestMapping(value = "/upload-files", headers = "content-type=multipart/*", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Boolean> upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("song_name") String song_name, @RequestParam("musician") int musician) throws IOException {
        try {
            Song song = new Song(song_name, musician, false);
            dao.AddSongFile(multipartFile, song);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (SQLException e) {
            e.getMessage();
            return new ResponseEntity<Boolean>(false, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping(value="/download-files")
    public ResponseEntity<byte[]> getFile(@RequestParam("song_id") int song_id) throws SQLException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        return new ResponseEntity<>(dao.GetSongFile(song_id), headers, HttpStatus.OK);
    }

    @GetMapping(value="/getAllSongs")
    public ResponseEntity<String> getAllSongs() throws SQLException {
        JSONObject json = new JSONObject(dao.getAllSongs());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>(json.toString(), headers, HttpStatus.OK);
    }
}