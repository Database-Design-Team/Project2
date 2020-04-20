package com.group2.webapp.controllers;

import com.group2.dao.GenreDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

/**
 * @author Timothy
 */
@Controller
public class GenreController {
    private final GenreDao dao = new GenreDao();

    public GenreController() throws SQLException {}

    /**
     * adds a genre to the genres table
     * @param genre Genre object representing what should be added to the table
     * @return http response object
     */
    @PostMapping("/genres")
    @ResponseBody
    public ResponseEntity<Boolean> addGenre(@RequestParam("genre_id") String genre) {
        try {
            dao.addGenre(genre);
            // return true;
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (SQLException e) {
            e.getMessage();
            return new ResponseEntity<>(false, HttpStatus.CONFLICT);
        }
    }

    /**
     * gets a genre by it's unique ID number
     * @param id the unique ID number getting searched for
     * @return http response object
     */
    @GetMapping("/genres")
    @ResponseBody
    public ResponseEntity getGenreByID(@RequestParam Integer id) {
        try {
            return new ResponseEntity<>(dao.getGenreByID(id), HttpStatus.OK);
        } catch(SQLException e) {
            return new ResponseEntity(false, HttpStatus.NOT_FOUND);
        }
    }
}
