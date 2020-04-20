package com.group2.webapp.controllers;

import com.group2.dao.LibraryDao;
import com.group2.model.Library;
import com.group2.model.Song;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.sql.SQLException;
import java.util.List;


public class LibraryController {
    LibraryDao dao = new LibraryDao();

    public LibraryController() throws SQLException {
    }


    // @PostMapping("/add-library")
    // @ResponseBody
    // public ResponseEntity addSongToLibrary(@RequestParam("owner") String Owner, @RequestParam("song_id") int SongId) {
    //     try {
    //         dao.addLibrary(Owner, SongId);
    //         return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    //     } catch (SQLException e)
    //     {
    //         String a = e.getMessage();
    //         return new ResponseEntity<String>(a, HttpStatus.CONFLICT);
    //     }

    // }

    // @GetMapping("/get-library")
    // @ResponseBody
    // public ResponseEntity getLibraryByOwner(@RequestParam("owner") String owner) {
    //     try {
    //         HashMap<String, Date> userLib = null;
    //         userLib = dao.getLibraryByOwner(owner);
    //         return new ResponseEntity<HashMap<String, Date>>(userLib, HttpStatus.OK);
    //     }  catch (SQLException e)
    //     {
    //         String b = e.getMessage();
    //         return new ResponseEntity<String>(b, HttpStatus.CONFLICT);
    //     }

    // }


    // @DeleteMapping("/library-delete")
    // @ResponseBody
    // public ResponseEntity removeSongFromLibrary(@RequestParam("song_id") int songId) {
    //     try {
    //         dao.removeSongFromLibrary(songId);
    //         return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    //     } catch (SQLException e) {
    //         String c = e.getMessage();
    //         return new ResponseEntity<String>(c, HttpStatus.CONFLICT);
    //     }

    // }




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
