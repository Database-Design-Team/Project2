package com.group2.dao.report;

import com.group2.dao.AbstractBaseDao;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Timothy Chandler
 * @version 1.0
 * @since 4/21/20
 * inside the package - com.group2.dao.report
 */
public class PopularSongs extends AbstractBaseDao {
    public PopularSongs() throws SQLException {
        super();
    }

    /**
     * returns the top x most listened to songs
     * @param songs the number of songs
     * @return the result set from the query
     * @throws SQLException on errors interacting with the database
     */
    public ResultSet getArtistSongs(@RequestParam int songs) throws SQLException {
        String SQL = "SELECT song_name, count(*) " +
                "FROM audio, song_statistics " +
                "WHERE audio_id = song " +
                "GROUP BY audio.audio_id " +
                "LIMIT ? " +
                "ORDER BY count(*) DESC";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, songs);
        return ps.executeQuery();
    }
}
