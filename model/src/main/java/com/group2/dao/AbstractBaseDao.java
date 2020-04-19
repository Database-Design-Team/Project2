package com.group2.dao;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import java.sql.*;

public class AbstractBaseDao {

    private static final String url = "jdbc:postgresql://ec2-54-165-36-134.compute-1.amazonaws.com:5432/dft5vu9ls8m9g2";
    private static final String user = "zgqjvuamulzrzb";
    private static final String password = "8853622559b8d9f06685388e005b737729ad3175006e14bd266677fcd44ab005";

    protected Connection conn;

    public AbstractBaseDao() throws SQLException {
        conn = DriverManager.getConnection(url, user, password);
    }

    /**
     * This method turns the result of a database query as a JSON array
     * @param rs the {@code ResultSet} result of the database query
     * @return a {@code JSONArray} of the rows returned by the query
     * @throws SQLException on errors interacting with the database
     */
    @NotNull
    static JSONArray getJsonArray(@NotNull ResultSet rs) throws SQLException {
        JSONArray array = new JSONArray();
        while (rs.next()){
            int total_rows = rs.getMetaData().getColumnCount();
            for (int i = 0; i < total_rows; i++) {
                JSONObject object = new JSONObject();
                object.put(rs.getMetaData().getColumnLabel(i + 1)
                        .toLowerCase(), rs.getObject(i + 1));
                array.put(object);
            }
        }
        return array;
    }
}
