package com.group2.dao;

import com.group2.model.Artist;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Timothy
 */
public class ArtistDao extends AbstractBaseDao {
    public ArtistDao() throws SQLException {
        super();
    }

    /**
     * adds an artist to the Artist table
     * @param artist the Artist object representing the entity to be entered
     * @throws SQLException on errors interacting with the database
     */
    public void addArtist(@NotNull String artist) throws SQLException {
        String SQL = "INSERT INTO artist(artist_name) VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, artist);
        ps.executeUpdate();
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

    public Set<Artist> getAllArtists() throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT  artist_id, artist_name, date_formed FROM artist");
        ResultSet rs = ps.executeQuery();
        Set<Artist> artistList = new HashSet<Artist>();
        while (rs.next()) {
            Artist artist = new Artist(rs.getInt(1), rs.getString(2), rs.getDate(3));
            artistList.add(artist);
        }
        rs.close();
        ps.close();

        return artistList;
    }

    public Set<Artist> getAllArtistsByBandMember(String bandMember) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM artist WHERE artist_id IN (SELECT band_id from band_member WHERE member_name = ?)");
        ps.setString(1, bandMember);
        ResultSet rs = ps.executeQuery();
        Set<Artist> artistList = new HashSet<Artist>();
        while (rs.next()) {
            Artist artist = new Artist(rs.getInt(1), rs.getString(2), rs.getDate(3));
            artistList.add(artist);
        }
        rs.close();
        ps.close();

        return artistList;
    }

    public void addUserToArtist(Integer artist_id, String username) throws SQLException {
        String SQL = "INSERT INTO band_member(member_name, band_id) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, username);
        ps.setInt(2, artist_id);
        ps.executeUpdate();
    }

    public void removeUserFromArtist(Integer artist_id, String username) throws SQLException {
        String SQL = "DELETE FROM band_member WHERE band_id = ? AND member_name = ?";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, artist_id);
        ps.setString(2, username);
        ps.executeUpdate();
    }
}
