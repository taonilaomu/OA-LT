package dto;

import entity.User;
import util.JDBCutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * author  luhongtao
 * 2022/7/25 10:02:17
 **/
public class UserDto {

    public static Connection connection = JDBCutil.getConnection();

    public static void saveUser(User user) {
        String sql = "insert into user (user_name,password) value (?,?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isExist(User user) {
        String sql = "select user_name,password from user where user_name = ? and password = ?";
        PreparedStatement statement = null;
        ResultSet set = null;
        int row = 0;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            set = statement.executeQuery();
            row = set.getRow();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row > 0;
    }
}
