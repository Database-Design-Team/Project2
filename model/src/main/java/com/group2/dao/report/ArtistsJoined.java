package com.group2.dao.report;

import com.group2.dao.AbstractBaseDao;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Timothy Chandler
 * @version 1.0.1
 * @since 4/20/20
 * inside the package - com.group2.dao.report
 */
public class ArtistsJoined extends AbstractBaseDao {
    public ArtistsJoined() throws SQLException {
        super();
    }

    /**
     * Returns a list of the artists who joined within a given number of days, sorted by age, and the number of songs.
     * 1st column: artist_name
     * 2nd column: date_formed: sorted from oldest to newest
     * 3rd column: count(*): the number of songs the artist has uploaded
     * @param days the number of days ago the artists may have joined
     * @return the result set
     * @throws SQLException on errors interacting with the database
     */
    public ResultSet getArtistsJoined(@RequestParam int days) throws SQLException {
        String SQL = "SELECT artist_name, date_formed, COUNT(*) " +
                "FROM artist, audio " +
                "WHERE date_formed > CURRENT_DATE-? " +
                "AND audio.artist = artist.artist_id " +
                "GROUP BY artist_name, date_formed " +
                "ORDER BY date_formed";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, days);
        return ps.executeQuery();
    }
}
