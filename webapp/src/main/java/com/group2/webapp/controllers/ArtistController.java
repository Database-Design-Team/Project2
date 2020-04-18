package com.group2.webapp.controllers;

import com.group2.dao.ArtistDao;
import com.group2.model.Artist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

/**
 * @author Timothy
 */
@Controller
public class ArtistController {
    private ArtistDao dao = new ArtistDao();

    public ArtistController() throws SQLException {}

    /**
     * adds an entry to the artist table
     * @param artist the Artist object representing the artist getting added to the database
     * @return the http response from the interaction with the database
     */
    @PostMapping("/artists")
    @ResponseBody
    public ResponseEntity<Boolean> addArtist(@RequestBody Artist artist) {
        try {
            dao.addArtist(artist);
            // return true;
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (SQLException e) {
            e.getMessage();
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }
    }

    /**
     * searches for an artist by using the artist's unique ID number
     * @param id the ID number of the artist getting searched for
     * @return the http response from the interaction with the database
     */
    @GetMapping("/artists")
    @ResponseBody
    public ResponseEntity<Artist> getArtistByID(@RequestParam Integer id) {
        try {
            return new ResponseEntity<>(dao.getArtistByID(id), HttpStatus.OK);
        } catch(SQLException e) {
            return new ResponseEntity(false, HttpStatus.NOT_FOUND);
        }
    }
}
