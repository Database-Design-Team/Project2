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
public class SongsAdded extends AbstractBaseDao {
    public SongsAdded() throws SQLException {
        super();
    }

    /**
     * Gets the number of songs added in the past given number of days, broken down by the day.
     * This report is meant for administrators.
     * 1st column: date_uploaded
     * 2nd column: count(*), the number of songs uploaded on that day
     * @param days the number of days being considered
     * @return the Result Set of the query
     * @throws SQLException on errors interacting with the database
     */
    public ResultSet getSongsAdded(@RequestParam int days) throws SQLException {
        String SQL = "SELECT date_uploaded, count(*) AS Uploads " +
                "FROM audio " +
                "WHERE date_uploaded > current_date-? " +
                "GROUP BY date_uploaded " +
                "ORDER BY date_uploaded";
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setInt(1, days);
        return ps.executeQuery();
    }
}
