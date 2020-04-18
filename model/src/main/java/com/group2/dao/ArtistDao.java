package com.group2.dao;

import com.group2.model.Artist;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.RequestBody;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Timothy
 */
public class ArtistDao extends AbstractBaseDao {
    public ArtistDao() throws SQLException {
        super();
    }

    /**
     * searches for an artist using the unique ID number
     * @param id the unique ID number getting searched for
     * @return an Artist object representing the entry in the database
     * @throws SQLException on errors interacting with the database
     */
    public Artist getArtistByID(@RequestBody int id) throws SQLException {
        String SQL = "SELECT * FROM artist WHERE artist_id = ?";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return new Artist(rs.getInt("artist_id"), rs.getString("artist_name"), rs.getDate("date_formed"));
    }

    /**
     * adds an artist to the Artist table
     * @param artist the Artist object representing the entity to be entered
     * @throws SQLException on errors interacting with the database
     */
    public void addArtist(@NotNull Artist artist) throws SQLException {
        String SQL = "INSERT INTO artist(artist_name) VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, artist.getArtistName());
        ps.executeUpdate();
    }
}
