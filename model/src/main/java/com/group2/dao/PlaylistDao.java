package com.group2.dao;
 
import com.group2.model.*;
import java.sql.*;
import java.util.*;
 
public class PlaylistDao extends AbstractBaseDao {
 
    public PlaylistDao() throws SQLException{
        super();
    }
 
    public Set<Playlist> getPlaylistByName (String playlistName) throws SQLException{
        String SQLStatement = "SELECT * FROM playlist where playlist_name = ?";
        PreparedStatement ps = conn.prepareStatement(SQLStatement);
        ps.setString(1, playlistName);
        ResultSet rs = ps.executeQuery();
        Set<Playlist> playlist = new HashSet<Playlist>();

        while (rs.next()) {
            Playlist pl = new Playlist(rs.getInt("playlist_id"), rs.getDate("date_created"), rs.getBoolean("isPrivate"),
                    rs.getString("playlist_name"), rs.getString("owner"));
            playlist.add(pl);
        }
        rs.close();
        ps.close();
        return playlist;
 
    }
 
    // public ArrayList<Playlist>getPublicPlaylists(String owner) throws SQLException{
    //     String SQLStatement = "SELECT * FROM playlist where owner = ? AND private? = FALSE";
    //     PreparedStatement ps = conn.prepareStatement(SQLStatement);
    //     ps.setString(1, owner);
 
    //     ResultSet rs = ps.executeQuery();
    //     ArrayList<Playlist> playlists  = new ArrayList<Playlist>();
    //     while (rs.next()){
    //         playlists.add(new Playlist(rs.getInt("playlist_id"), rs.getDate("date_created"),
    //                 rs.getBoolean("private?"), rs.getInt("number_of_songs"),
    //                 rs.getString("playlist_name"), rs.getString("owner")));
    //     }
    //         return playlists;
    // }
 
    public void addPlaylist(Playlist Artisan) throws SQLException {
        // INSERT INTO playlist(owner, playlist_name, "isPrivate") VALUES ('ryan', 'lofi', false);
        String SQLStatement = "INSERT INTO playlist(owner, playlist_name, \"isPrivate\") VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(SQLStatement);
        ps.setString(1, Artisan.getOwner());
        ps.setString(2, Artisan.getPlaylistName());
        ps.setBoolean(3, Artisan.isPrivate());
        ps.executeUpdate();
    }
}
 