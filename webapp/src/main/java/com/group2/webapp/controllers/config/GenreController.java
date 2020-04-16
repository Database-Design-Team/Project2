package com.group2.webapp.controllers.config;

import com.group2.dao.GenreDao;
import com.group2.model.Genre;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

/**
 * @author Timothy
 */
@Controller
public class GenreController {
    private GenreDao dao = new GenreDao();

    public GenreController() throws SQLException {}

    /**
     * adds a genre to the genres table
     * @param genre Genre object representing what should be added to the table
     * @return response from the http request
     */
    @PostMapping("/genres")
    @ResponseBody
    public ResponseEntity<Boolean> addGenre(@RequestBody Genre genre) {
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
     * @return response from the http request
     */
    @GetMapping("/genres")
    @ResponseBody
    public ResponseEntity<Boolean> getGenreByID(int id) {
        try {
            dao.getGenreByID(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch(SQLException e) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * gets a genre by it's name
     * @param title the title of the genre being searched for
     * @return response from the http request
     */
    @GetMapping("/genres")
    @ResponseBody
    public ResponseEntity<Boolean> getGenreByTitle(String title) {
        try {
            dao.getGenreByTitle(title);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch(SQLException e) {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }
}
