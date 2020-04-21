package com.group2.dao.report;

import com.group2.dao.AbstractBaseDao;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Timothy Chandler
 * @version 1.0.1
 * @since 4/21/20
 * inside the package - com.group2.dao.report
 */
public class PopularSongs extends AbstractBaseDao {
    public PopularSongs() throws SQLException {
        super();
    }

    /**
     * Returns the top x most listened to songs, the artist who released the song, and the number of listens.
     * 1st column: Artist
     * 2nd column: Song
     * 3rd column: Listens
     * @param songs the number of songs
     * @return the result set from the query
     * @throws SQLException on errors interacting with the database
     */
    public ResultSet getArtistSongs(@RequestParam int songs) throws SQLException {
        String SQL = "SELECT artist_name AS Artist, song_name AS song, count(*) AS plays" +
                "FROM artist, audio, song_statistics " +
                "WHERE " +
                "      audio_id = song " +
                "  AND artist = artist_id " +
                "GROUP BY audio.audio_id, artist_name " +
                "ORDER BY count(*) DESC " +
                "LIMIT ?";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, songs);
        return ps.executeQuery();
    }
}
