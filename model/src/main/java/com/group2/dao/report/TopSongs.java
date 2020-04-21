package com.group2.dao.report;

import com.group2.dao.AbstractBaseDao;
import org.springframework.web.bind.annotation.RequestBody;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Timothy Chandler
 * @version 1.0
 * @since 4/20/20
 * inside the package - com.group2.dao.report
 */
public class TopSongs extends AbstractBaseDao {
    public TopSongs() throws SQLException {
        super();
    }

    /**
     * This will return a given number of the top songs of a given genre, which were added within a given number of days
     * @param genre the int ID of the genre the search is restricted to
     * @param days the int amount of days ago the songs may have been uploaded. This is a time restriction
     * @param songs the number of top songs to be returned
     * @return the result set
     * @throws SQLException on errors interacting with the database
     */
    public ResultSet getTopSongs(@RequestBody int genre, int days, int songs) throws SQLException {
        String SQL = "SELECT song_name " +
                "FROM audio " +
                "WHERE audio_id IN (" +
                "    SELECT song" +
                "    FROM song_genres, release, track " +
                "    WHERE " +
                "        genre = ? " +
                "        AND date_created > ? " +
                "        AND release_id = track.release " +
                "        AND track.song = song_genres.song) " +
                "LIMIT ? asc";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, genre);
        ps.setInt(2, days);
        ps.setInt(3, songs);
        return ps.executeQuery();
    }
}
