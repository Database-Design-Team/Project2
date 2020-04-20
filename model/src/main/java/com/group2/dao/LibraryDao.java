package com.group2.dao;

import java.sql.*;
import com.group2.model.Library;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class LibraryDao extends AbstractBaseDao {

    public LibraryDao() throws SQLException {
        super();
    }

    public HashMap<String, Date> getLibraryByOwner(String ownerName) throws SQLException {
        String SQLStatement = "SELECT song_name FROM audio WHERE audio_id in(SELECT song FROM library WHERE owner = ?)";
        PreparedStatement ps = conn.prepareStatement(SQLStatement);
        ps.setString(1, ownerName);
        ResultSet rs = ps.executeQuery();

        String SQLS1 = "SELECT date_added FROM library WHERE owner = ?";
        PreparedStatement ps1 = conn.prepareStatement(SQLS1);
        ps1.setString(1, ownerName);
        ResultSet rs1 = ps1.executeQuery();
        HashMap<String, Date> lib = new HashMap<>();
        while(rs.next() && rs1.next()) {
            lib.put(rs.getString("song_name"), rs1.getDate("date_added"));
        }

        return lib;
    }

    public void addLibrary(String lib, int songId) throws SQLException {
        String SQLStatement = "INSERT INTO library(owner, song) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(SQLStatement);
        ps.setString(1, lib);
        ps.setInt(2, songId);
        ps.executeUpdate();
    }

    public void removeSongFromLibrary(int songId) throws SQLException {
        String SQLStatement = "DELETE FROM library WHERE song = ?";
        PreparedStatement ps = conn.prepareStatement(SQLStatement);
        ps.setInt(1, songId);
        ps.executeUpdate();
    }
 }
