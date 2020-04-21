package com.group2.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SongStatisticsDao extends AbstractBaseDao {

    public SongStatisticsDao() throws SQLException {
        super();
    }

    public void addToSongStatistics(String username, int songID, int rating) throws SQLException {
        String SQLStatement = "INSERT INTO song_ratings(rating, song_id, user_name) VALUES(?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(SQLStatement);
        ps.setInt(1, rating);
        ps.setInt(2, songID);
        ps.setString(3, username);
        ps.executeUpdate();
        ps.close();
    }


    public double getAggregratePopularityBySong(int songID) throws SQLException {
        int totalPop = 0;
        int count = 0;
        String SQLStatement = "SELECT rating FROM song_ratings WHERE song_id = ?";
        PreparedStatement ps = conn.prepareStatement(SQLStatement);
        ps.setInt(1, songID);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            totalPop += rs.getInt("rating");
            count += 1;
        }

        ps.close();
        rs.close();
        return (double)(totalPop / count);
    }
}
