package com.group2.webapp.controllers;

import com.group2.dao.SongDao;
import com.group2.model.Song;

import org.json.JSONObject;
import org.springframework.boot.jackson.JsonObjectDeserializer;
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

@Controller
public class SongController {

    private SongDao dao = new SongDao();

    public SongController() throws SQLException {
    }

    @RequestMapping(value = "/upload-files", headers = "content-type=multipart/*", method = RequestMethod.POST)
    @ResponseBody
    public void upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("song_name") String song_name, @RequestParam("musician") int musician) throws IOException, SQLException {
        Song song = new Song();
        song.setSong_name(song_name);
        song.setMusician(musician);
        dao.AddSongFile(multipartFile, song);
    }

    // @RequestMapping(value ="/download-files", headers ="content-type=multipart/*", method = RequestMethod.GET);
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