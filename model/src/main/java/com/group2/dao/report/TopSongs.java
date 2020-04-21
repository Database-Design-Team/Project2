package com.group2.dao.report;

import com.group2.dao.AbstractBaseDao;
import org.springframework.web.bind.annotation.RequestBody;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Timothy Chandler
 * @version 1.0.1
 * @since 4/20/20
 * inside the package - com.group2.dao.report
 */
public class TopSongs extends AbstractBaseDao {
    public TopSongs() throws SQLException {
        super();
    }

    /**
     * TODO reword to reflect recent changes, make admin/user version
     * This will return a given number of the top songs of a given genre, which were added within a given number of days
     * 1st column: artist
     * 2nd column: song
     * 3rd column: plays
     * @param genre the int ID of the genre the search is restricted to
     * @param days the int amount of days ago the songs may have been uploaded. This is a time restriction
     * @param songs the number of top songs to be returned
     * @return the result set
     * @throws SQLException on errors interacting with the database
     */
    public ResultSet getTopSongs(@RequestBody int genre, int days, int songs) throws SQLException {
        String SQL = "SELECT artist_name AS Artist, song_name AS song, count(*) AS plays " +
                "FROM artist, audio, song_statistics " +
                "WHERE " +
                "    genre = ? " +
                "AND date_uploaded > CURRENT_DATE-? " +
                "AND artist_id = audio.artist " +
                "GROUP BY artist_name, song_name " +
                "ORDER BY count(*) " +
                "LIMIT ?";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, genre);
        ps.setInt(2, days);
        ps.setInt(3, songs);
        return ps.executeQuery();
    }
}
