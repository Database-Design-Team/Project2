package com.group2.webapp.controllers;

import com.group2.dao.ArtistDao;
import com.group2.model.Artist;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Set;

/**
 * @author Timothy
 */
@SuppressWarnings("unchecked")
@Controller
public class ArtistController {
    private final ArtistDao dao = new ArtistDao();

    public ArtistController() throws SQLException {}

    /**
     * adds an entry to the artist table
     * @param artist the name of the artist getting added to the database
     * @return the http response from the interaction with the database
     */
    @PostMapping("/artists")
    @ResponseBody
    public ResponseEntity<Boolean> addArtist(@RequestParam("artist_name") String artist) {
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
        } catch (SQLException e) {
            e.getMessage();
            return new ResponseEntity(false, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/getAllArtists")
    public ResponseEntity<String> getAllArtists() throws SQLException {
        Set<Artist> artistList = dao.getAllArtists();
        JSONObject json = new JSONObject();
        for (Artist artist : artistList) {
            JSONObject temp = new JSONObject();
            temp.put(artist.getArtistName(), artist.getDateFormed());
            json.put(String.valueOf(artist.getArtistID()), temp);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>(json.toString(), headers, HttpStatus.OK);
    }

    @GetMapping(value="/getAllArtistsByBandMember")
    public ResponseEntity<String> getAllArtistsByBandMember(@RequestParam("band_member") String band_member) throws SQLException {
        Set<Artist> artistList = dao.getAllArtistsByBandMember(band_member);
        JSONObject json = new JSONObject();
        for (Artist artist : artistList) {
            JSONObject temp = new JSONObject();
            temp.put(artist.getArtistName(), artist.getDateFormed());
            json.put(String.valueOf(artist.getArtistID()), temp);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>(json.toString(), headers, HttpStatus.OK);
    }

    @PostMapping("/join-artist")
    @ResponseBody
    public ResponseEntity<Boolean> joinArtist(@RequestParam("artist_id") Integer artist_id, @RequestParam("username") String username) {
        try {
            dao.addUserToArtist(artist_id, username);
            // return true;
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (SQLException e) {
            e.getMessage();
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/remove-artist-member")
    @ResponseBody
    public ResponseEntity<Boolean> removeArtistMember(@RequestParam("artist_id") Integer artist_id,
            @RequestParam("username") String username) {
        try {
            dao.removeUserFromArtist(artist_id, username);
            // return true;
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (SQLException e) {
            e.getMessage();
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }
    }
    
    @DeleteMapping("/remove-artist")
     @ResponseBody
     public ResponseEntity<Boolean> removeArtistById(@RequestParam("artist_id") Integer artist_id) {
        try {
             dao.removeArtistById(artist_id);
             return new ResponseEntity<Boolean>(true, HttpStatus.OK);
         } catch(SQLException e) {
             e.getMessage();
             return new ResponseEntity<Boolean>(false, HttpStatus.OK);
         }

     }
}
