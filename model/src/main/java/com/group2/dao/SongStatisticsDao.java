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
        double popularity;
        String SQLStatement = "SELECT aggregate_popularity FROM audio WHERE audio_id = ?";
        PreparedStatement ps = conn.prepareStatement(SQLStatement);
        ps.setInt(1, songID);
        ResultSet rs = ps.executeQuery();
        rs.next();
        popularity = rs.getDouble("aggregate_popularity");
        ps.close();
        rs.close();
        return (popularity);
    }
}
