package com.group2.dao;

import com.group2.model.Genre;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Timothy
 */
public class GenreDao extends AbstractBaseDao {
    public GenreDao() throws SQLException {
        super();
    }

    /**
     * searches the database for a genre with a specific ID
     * @param id the unique ID number of the genre
     * @return on errors interacting with the database
     * @throws SQLException on errors interacting with the database
     */
    public Genre getGenreByID(int id) throws SQLException {
        String SQL = "SELECT * FROM genre WHERE genre_id = ?";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, String.valueOf(id));
        ResultSet rs = ps.executeQuery();
        rs.next();
        return new Genre(rs.getInt("genre_id"), rs.getString("genre_title"));
    }

    /**
     * searches the database for a genre with a specific title
     * @param title the String representation of the title being searched for
     * @return on errors interacting with the database
     * @throws SQLException on errors interacting with the database
     */
    public Genre getGenreByTitle(String title) throws SQLException {
        String SQL = "SELECT * FROM genre WHERE genre_title = ?";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, title);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return new Genre(rs.getInt("genre_id"), rs.getString("genre_title"));
    }

    /**
     * adds a genre to the genre table in the database
     * @param genre the Genre object representing the genre getting added to the database
     * @throws SQLException on errors interacting with the database
     */
    public void addGenre(Genre genre) throws SQLException {
        String SQL = "INSERT INTO genre(genre_id, genre_title) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, genre.getGenreID());
        ps.setString(2, genre.getGenreTitle());
        ps.executeUpdate();
    }

    /**
     * adds a list of genres to the genre table in the database
     * @param gList a List object of genres getting added
     * @throws SQLException on errors interacting with the database
     */
    public void addGenre(List<Genre> gList) throws SQLException {
        for(Genre g : gList) {
            addGenre(g);
        }
    }
}
