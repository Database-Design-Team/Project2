package com.group2.dao;

import com.group2.model.Artist;
import org.jetbrains.annotations.NotNull;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Timothy
 */
public class ArtistDao extends AbstractBaseDao {
    public ArtistDao() throws SQLException {
        super();
    }

    /**
     * searches for an artist using the
     * @param id
     * @return
     * @throws SQLException
     */
    public Artist getArtistByID(int id) throws SQLException {
        String SQL = "SELECT * FROM artist WHERE artist_id = ?";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, String.valueOf(id));
        ResultSet rs = ps.executeQuery();
        rs.next();
        return new Artist(rs.getInt("artist_id"), rs.getString("artist_name"), rs.getDate("date_formed"));
    }


    public Artist getArtistByTitle(String title) throws SQLException {
        String SQL = "SELECT * FROM artist WHERE artist_title = ?";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, title);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return new Artist(rs.getInt("artist_id"), rs.getString("artist_name"), rs.getDate("date_formed"));
    }


    public void addGenre(@NotNull Artist artist) throws SQLException {
        String SQL = "INSERT INTO artist(artist_id, artist_name, date_formed) VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, artist.getArtistID());
        ps.setString(2, artist.getArtistName());
        ps.executeUpdate();
    }


    public void addGenre(@NotNull List<Artist> gList) throws SQLException {
        for(Artist a : gList) {
            addGenre(a);
        }
    }
}
