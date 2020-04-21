package com.group2.webapp.controllers;


import com.group2.dao.SongStatisticsDao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@Controller
public class SongStatisticsController {

    private SongStatisticsDao dao = new SongStatisticsDao();
    public SongStatisticsController() throws SQLException {

    }


    @PostMapping("/add-song-statistic")
    @ResponseBody
    public ResponseEntity<Boolean> addSongStatistic(@RequestParam("username") String userName, @RequestParam("songID") int songID,
                                           @RequestParam("rating") int rating) {
        try {
            dao.addToSongStatistics(userName, songID, rating);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } catch (SQLException e) {
            e.getMessage();
            return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get-aggregrate-popularity")
    @ResponseBody
    public ResponseEntity getAggregratePopularity(@RequestParam("song_id") int songId) {
        try {
            Double aggregratePop = dao.getAggregratePopularityBySong(songId);
            return new ResponseEntity<Double>(aggregratePop, HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
