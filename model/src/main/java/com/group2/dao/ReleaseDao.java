package com.group2.dao;

import com.group2.model.Release;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReleaseDao extends AbstractBaseDao {

    public ReleaseDao() throws SQLException {
        super();
    }


    public Release getReleaseById(int releaseId) throws SQLException {
        String SQLStatement = "SELECT * FROM release WHERE release_id = ?";
        PreparedStatement ps = conn.prepareStatement(SQLStatement);
        ps.setInt(1, releaseId);
        ResultSet rs = ps.executeQuery();
        rs.next();

        return new Release(rs.getInt("release_id"), rs.getString("title"), rs.getDate("date_created"));
    }


    public void addRelease(String title, int releaseType, int numOfTracks) throws SQLException{
        String SQLStatement = "INSERT INTO release(title, release_type, number_of_tracks) VALUES(?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(SQLStatement);
        ps.setString(1, title);
        ps.setInt(2, releaseType);
        ps.setInt(3, numOfTracks);
        ps.executeUpdate();
    }

    public void changeReleaseTitleById(String title, int ID) throws SQLException {
        String SQLStatement = "UPDATE release SET title = ? WHERE release_id = ?";
        PreparedStatement ps = conn.prepareStatement(SQLStatement);
        ps.setString(1, title);
        ps.setInt(2, ID);
        ps.executeUpdate();
    }

    public void deleteReleaseById(int ID) throws SQLException {
        String SQLStatement = "DELETE FROM release WHERE release_id = ?";
        PreparedStatement ps = conn.prepareStatement(SQLStatement);
        ps.setInt(1, ID);
        ps.executeUpdate();
    }
}
