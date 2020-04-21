package com.group2.dao;

import com.group2.model.Release;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

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

        return new Release(rs.getInt("release_id"), rs.getString("title"), rs.getDate("date_created"), rs.getInt("artist"));
    }

    public Set<Release> getReleaseByArtist(int artist_id) throws SQLException {
        String SQLStatement = "SELECT * FROM release WHERE artist = ?";
        PreparedStatement ps = conn.prepareStatement(SQLStatement);
        ps.setInt(1, artist_id);
        ResultSet rs = ps.executeQuery();
        Set<Release> releaseList = new HashSet<Release>();
        while (rs.next()) {
            Release release = new Release(rs.getInt("release_id"), rs.getString("title"), rs.getDate("date_created"),
                    rs.getInt("artist"));
            releaseList.add(release);
        }
        rs.close();
        ps.close();
        return releaseList;
    }


    public void addRelease(String title, Integer artist_id) throws SQLException{
        String SQLStatement = "INSERT INTO release(title, artist) VALUES(?, ?)";
        PreparedStatement ps = conn.prepareStatement(SQLStatement);
        ps.setString(1, title);
        ps.setInt(2, artist_id);
        ps.executeUpdate();
        ps.close();
    }

    public void changeReleaseTitleById(String title, int ID) throws SQLException {
        String SQLStatement = "UPDATE release SET title = ? WHERE release_id = ?";
        PreparedStatement ps = conn.prepareStatement(SQLStatement);
        ps.setString(1, title);
        ps.setInt(2, ID);
        ps.executeUpdate();
        ps.close();
    }

    public void deleteReleaseById(int ID) throws SQLException {
        String SQLStatement = "DELETE FROM release WHERE release_id = ?";
        PreparedStatement ps = conn.prepareStatement(SQLStatement);
        ps.setInt(1, ID);
        ps.executeUpdate();
        ps.close();
    }
}
