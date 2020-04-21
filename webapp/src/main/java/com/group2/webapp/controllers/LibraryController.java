package com.group2.webapp.controllers;

import com.group2.dao.LibraryDao;
import com.group2.dao.SongDao;
import com.group2.model.Library;
import com.group2.model.Song;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.sql.SQLException;
import java.util.List;

@Controller
public class LibraryController {
    LibraryDao dao = new LibraryDao();
    SongDao sDAO = new SongDao();

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


    @DeleteMapping("/library-delete")
    @ResponseBody
    public ResponseEntity<Boolean> removeSongFromLibrary(@RequestParam("song_id") int songId) {
        try {
            dao.removeSongFromLibrary(songId);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
        }

    }




    @GetMapping(value="/library")
    @ResponseBody
    public ResponseEntity<String> getEntireLibrary(@RequestParam("username") String username) throws SQLException {
        List<Song> songList = dao.getUserLibrary(username);
        HashMap<Integer, String> artistNames = sDAO.getAristNames();
        JSONObject json = new JSONObject();
        for (Song song : songList) {
            String song_artist = artistNames.get(song.getMusician()) + "|" + song.getSong_name();
            json.put(String.valueOf(song.getSong_id()), song_artist);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<String>(json.toString(), headers, HttpStatus.OK);
        
    }

    @PostMapping(value="/library")
    @ResponseBody
    public ResponseEntity<Boolean> addSongToLibrary(@RequestParam("song_id") int songID, @RequestParam("username") String username) {
        try {
            dao.addToLibrary(songID, username);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch(SQLException e) {
            return new ResponseEntity<Boolean>(false, HttpStatus.OK);
        }
    }
}
