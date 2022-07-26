package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * author  luhongtao
 * 2022/7/21 09:33:12
 **/
public class JDBCutil {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/OA", "root", "qqqqpppp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
