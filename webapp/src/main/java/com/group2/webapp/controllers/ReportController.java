package com.group2.webapp.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.group2.dao.report.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReportController {
    public final PopularSongs popularSongsDAO = new PopularSongs();
    public final ArtistsJoined artistsJoinedDAO = new ArtistsJoined();
    public final ArtistSongs artistSongsDAO = new ArtistSongs();
    public final SongListens songListensDAO = new SongListens();
    public final SongsAdded songsAddedDAO = new SongsAdded();
    public final TopSongs topSongsDAO = new TopSongs();

    public ReportController() throws SQLException {

    }
    

    @GetMapping(value="/get-popular-songs")
    public ResponseEntity<String> getPopularSongs(@RequestParam("totalSongs") int totalSongs) throws SQLException {
        ResultSet rs = popularSongsDAO.getArtistSongs(totalSongs);
        JSONArray array = new JSONArray();
        while (rs.next()) {
            JSONObject temp = new JSONObject();
            temp.put("ArtistName", rs.getString(1));
            temp.put("SongName", rs.getString(2));
            String formattedFloat = String.format("%.02f", rs.getFloat(3));
            temp.put("Rating", String.valueOf((50 + Float.parseFloat(formattedFloat) * 50))+"%");
            array.put(temp);
        }
        rs.close();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<String>(array.toString(), headers, HttpStatus.OK);
    }

    @GetMapping(value="/get-artists-joined")
    public ResponseEntity<String> getArtistsJoined(@RequestParam("days") int days) throws SQLException {
        ResultSet rs = artistsJoinedDAO.getArtistsJoined(days);
        JSONArray array = new JSONArray();
        while (rs.next()) {
            JSONObject temp = new JSONObject();
            temp.put("ArtistName", rs.getString(1));
            temp.put("DateFormed", rs.getDate(2));
            temp.put("Songs", rs.getInt(3));
            array.put(temp);
        }
        rs.close();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<String>(array.toString(), headers, HttpStatus.OK);
    }

    @GetMapping(value="/get-artist-songs")
    public ResponseEntity<String> getArtistSongs(@RequestParam("days") int days) throws SQLException {
        ResultSet rs = artistSongsDAO.getArtistSongs(days);
        JSONArray array = new JSONArray();
        while (rs.next()) {
            JSONObject temp = new JSONObject();
            temp.put("ArtistName", rs.getString(1));
            temp.put("Songs", rs.getInt(2));
            array.put(temp);
        }
        rs.close();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<String>(array.toString(), headers, HttpStatus.OK);
    }

    @GetMapping(value="/get-song-listens")
    public ResponseEntity<String> getSongListens(@RequestParam("days") int days) throws SQLException {
        ResultSet rs = songListensDAO.getSongListens(days);
        JSONArray array = new JSONArray();
        while (rs.next()) {
            JSONObject temp = new JSONObject();
            temp.put("DaysAgo", rs.getInt(1));
            temp.put("Listens", rs.getInt(2));
            array.put(temp);
        }
        rs.close();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<String>(array.toString(), headers, HttpStatus.OK);
    }

    @GetMapping(value="/get-songs-added")
    public ResponseEntity<String> getSongsAdded(@RequestParam("days") int days) throws SQLException {
        ResultSet rs = songsAddedDAO.getSongsAdded(days);
        JSONArray array = new JSONArray();
        while (rs.next()) {
            JSONObject temp = new JSONObject();
            temp.put("DateUploaded", rs.getDate(1));
            temp.put("Count", rs.getInt(2));
            array.put(temp);
        }
        rs.close();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<String>(array.toString(), headers, HttpStatus.OK);
    }

    @GetMapping(value="/get-top-songs")
    public ResponseEntity<String> getTopSongs(@RequestParam("genre") int genre, @RequestParam("days") int days, @RequestParam("songs") int songs) throws SQLException {
        ResultSet rs = topSongsDAO.getTopSongs(genre, days, songs);
        JSONArray array = new JSONArray();
        while (rs.next()) {
            JSONObject temp = new JSONObject();
            temp.put("ArtistName", rs.getString(1));
            temp.put("SongName", rs.getString(2));
            temp.put("Rating", rs.getInt(3));
            array.put(temp);
        }
        rs.close();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        return new ResponseEntity<String>(array.toString(), headers, HttpStatus.OK);
    }
}