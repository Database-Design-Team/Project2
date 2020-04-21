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
public class SongsAdded extends AbstractBaseDao {
    public SongsAdded() throws SQLException {
        super();
    }

    /**
     * gets the number of songs added in the past given number of days, broken down by the day
     * @param days the number of days being considered
     * @return the Result Set of the query
     * @throws SQLException on errors interacting with the database
     */
    public ResultSet getSongsAdded(@RequestParam int days) throws SQLException {
        String SQL = "SELECT date_created, count(*) " +
                "FROM release, track " +
                "WHERE " +
                "    track.release = release_id " +
                "    AND date_created > current_date-? " +
                "GROUP BY date_created;";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, days);
        return ps.executeQuery();
    }
}
