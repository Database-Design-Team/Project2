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
public class ArtistSongs extends AbstractBaseDao {
    public ArtistSongs() throws SQLException {
        super();
    }

    /**
     * returns a table of the artists and the amount of songs they uploaded in the past number of days,
     * from most active to least active.
     * 1st column: artist_name
     * 2nd column: count(*), the amount of songs uploaded in the past number of days
     * @param days the max age of a song which counts towards recent uploads
     * @return the result set from the query
     * @throws SQLException on errors interactive with the database
     */
    public ResultSet getArtistSongs(@RequestParam int days) throws SQLException {
        String SQL = "SELECT artist_name, count(*) " +
                "FROM artist, audio " +
                "WHERE " +
                "    audio.date_uploaded > current_date-? " +
                "AND audio.artist = artist.artist_id " +
                "GROUP BY artist_name " +
                "ORDER BY count(*) DESC";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, days);
        return ps.executeQuery();
    }
}
