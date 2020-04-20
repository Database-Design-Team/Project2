package com.group2.dao;

import com.group2.model.Genre;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Timothy
 */
public class GenreDao extends AbstractBaseDao {
    public GenreDao() throws SQLException {
        super();
    }

    /**
     * adds a genre to the genre table in the database
     * @param genre the Genre object representing the genre getting added to the database
     * @throws SQLException on errors interacting with the database
     */
    public void addGenre(@NotNull String genre) throws SQLException {
        String SQL = "INSERT INTO genre(genre_title) VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setString(1, genre);
        ps.executeUpdate();
    }

    /**
     * searches the database for a genre with a specific ID
     * @param id the unique ID number of the genre
     * @return a Genre object representing the entry in the database
     * @throws SQLException on errors interacting with the database
     */
    public Genre getGenreByID(@RequestBody int id) throws SQLException {
        String SQL = "SELECT * FROM genre WHERE genre_id = ?";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return new Genre(rs.getInt("genre_id"), rs.getString("genre_title"));
    }
}
