package com.group2.webapp.controllers;

import com.group2.dao.LibraryDao;
import com.group2.model.Song;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
public class LibraryController {
    LibraryDao dao = new LibraryDao();

    public LibraryController() throws SQLException {
    }

    @GetMapping(value="library")
    @ResponseBody
    public ResponseEntity<List<Song>> getEntireLibrary(@RequestParam String username) {
        try {
            return new ResponseEntity<List<Song>>(dao.getUserLibrary(username), HttpStatus.OK);
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value="library")
    @ResponseBody
    public ResponseEntity<Boolean> addSongToLibrary(@RequestParam int songID, @RequestParam String username) {
        try {
            dao.addToLibrary(songID, username);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch(SQLException e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
