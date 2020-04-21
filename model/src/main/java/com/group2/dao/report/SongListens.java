package com.group2.dao.report;

import com.group2.dao.AbstractBaseDao;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Timothy Chandler
 * @version 1.1
 * @since 4/20/20
 * inside the package - com.group2.dao.report
 */
public class SongListens extends AbstractBaseDao {
    public SongListens() throws SQLException {
        super();
    }

    /**
     * Returns the number of listens over the past given number of days, broken down by the day.
     * This report is meant for administrators.
     * 1st column: days, number of days ago
     * 2nd column: listens
     * @param days the number of days being considered
     * @return the result set
     * @throws SQLException on errors interacting with the database
     */
    public ResultSet getSongListens(@RequestParam  int days) throws SQLException {
        String SQL = "SELECT current_date-time_stamp+1 AS Days, count(*) AS listens " +
                "FROM song_listens " +
                "WHERE time_stamp > current_date-?" +
                "GROUP BY time_stamp " +
                "ORDER BY time_stamp DESC";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, days);
        return ps.executeQuery();
    }
}