package com.group2.webapp.controllers;


import com.group2.dao.ReleaseDao;
import com.group2.model.Release;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
public class ReleaseController {
    private ReleaseDao dao = new ReleaseDao();

    public ReleaseController() throws SQLException {

    }

    @GetMapping("/release-by-name")
    @ResponseBody
    public ResponseEntity getReleaseByName(@RequestParam("release_id") int releaseId) {
        try {
            Release a = dao.getReleaseById(releaseId);
            return new ResponseEntity<Release>(a, HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/add-release")
    @ResponseBody
    public ResponseEntity addRelease(@RequestParam("title") String title, @RequestParam("release_type") int releaseType,
                                     @RequestParam("number_of_tracks") int numOfTracks) {

        try {
            dao.addRelease(title, releaseType, numOfTracks);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch(SQLException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


    @PutMapping("/update-release-title")
    @ResponseBody
    public ResponseEntity updateReleaseTitle(@RequestParam("updated_title") String updatedTitle, @RequestParam("release_id") int ID) {
        try {
            dao.changeReleaseTitleById(updatedTitle, ID);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.OK);
        }
    }

    @DeleteMapping("/remove-release")
    @ResponseBody
    public ResponseEntity removeReleaseById(@RequestParam("release_id") int ID) {
        try {
            dao.deleteReleaseById(ID);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }
}

