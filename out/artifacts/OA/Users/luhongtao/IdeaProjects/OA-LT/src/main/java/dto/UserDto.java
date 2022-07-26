package dto;

import entity.User;
import util.JDBCutil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

/**
 * author  luhongtao
 * 2022/7/25 10:02:17
 **/
public class UserDto {

    public static void saveUser(User user){
        Connection connection = JDBCutil.getConnection();
        String sql = "insert into login(username, logintime, logouttime, logcount) value (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, login.getUsername());
        statement.setTimestamp(2, new Timestamp(login.getLogintime().getTime()));
        statement.setTimestamp(3, new Timestamp(login.getLogouttime().getTime()));
        statement.setInt(4, login.getLogcount());
        statement.execute();
    }
}
