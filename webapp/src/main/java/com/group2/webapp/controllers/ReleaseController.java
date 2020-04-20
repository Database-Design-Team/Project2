package com.group2.webapp.controllers;


import com.group2.dao.ReleaseDao;
import com.group2.model.Release;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Set;

@Controller
public class ReleaseController {
    private ReleaseDao dao = new ReleaseDao();

    public ReleaseController() throws SQLException {

    }

    @GetMapping("/release-by-id")
    @ResponseBody
    public ResponseEntity getReleaseById(@RequestParam("release_id") int releaseId) {
        try {
            Release a = dao.getReleaseById(releaseId);
            return new ResponseEntity<Release>(a, HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value="/release-by-artist")
    public ResponseEntity<String> getAllSongs(@RequestParam("artist_id") int artist_id) throws SQLException {
        Set<Release> releaseList = dao.getReleaseByArtist(artist_id);
        JSONObject json = new JSONObject();
        for(Release release : releaseList) {
            JSONObject temp = new JSONObject();
            temp.put(release.getTitle(), release.getDateCreated());
            json.put(String.valueOf(release.getReleaseID()), temp);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<>(json.toString(), headers, HttpStatus.OK);
    }

    @PostMapping("/add-release")
    @ResponseBody
    public ResponseEntity<Boolean> addRelease(@RequestParam("title") String title, @RequestParam("artist") Integer artist_id) {
        try {
            dao.addRelease(title, artist_id);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch(SQLException e) {
            e.getMessage();
            return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
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
    public ResponseEntity<Boolean> removeReleaseById(@RequestParam("release_id") int ID) {
        try {
            dao.deleteReleaseById(ID);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
        }

    }
}

