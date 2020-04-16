package com.group2.webapp.controllers;

import com.group2.dao.SongDao;
import com.group2.model.Song;

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

    private SongDao dao = new SongDao();

    public SongController() throws SQLException {
    }

    @RequestMapping(value = "/upload-files", headers = "content-type=multipart/*", method = RequestMethod.POST)
    @ResponseBody
    public void upload(@RequestParam("file") MultipartFile multipartFile) throws IOException, SQLException {
        dao.AddSongFile(multipartFile);
    }

    // @RequestMapping(value ="/download-files", headers ="content-type=multipart/*", method = RequestMethod.GET);
    @GetMapping(value="/download-files")
    public ResponseEntity<byte[]> getFile() throws SQLException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        return new ResponseEntity<>(dao.GetSongFile(), headers, HttpStatus.OK);
    }
}
