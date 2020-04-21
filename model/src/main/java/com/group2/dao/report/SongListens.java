package com.group2.dao.report;

import com.group2.dao.AbstractBaseDao;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Timothy Chandler
 * @version 1.0
 * @since 4/20/20
 * inside the package - com.group2.dao.report
 */
public class SongListens extends AbstractBaseDao {
    public SongListens() throws SQLException {
        super();
    }

    /**
     * Returns the number of listens over the past given number of days, broken down by the day
     * @param days the number of days being considered
     * @return the result set
     * @throws SQLException on errors interacting with the database
     */
    public ResultSet getSongListens(@RequestParam  int days) throws SQLException {
        String SQL = "SELECT time_stamp, count(*) " +
                "FROM song_statistics " +
                "WHERE " +
                "    time_stamp > current_date-? " +
                "GROUP BY time_stamp";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, days);
        return ps.executeQuery();
    }
}