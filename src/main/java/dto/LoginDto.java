package dto;

import entity.Login;
import util.JDBCutil;

import java.sql.*;

/**
 * author  luhongtao
 * 2022/7/21 09:50:28
 **/
public class LoginDto {

    public static Connection connection = JDBCutil.getConnection();

    public static Login selectByName(String username) {
        String sql = "select logouttime,logcount,timestampdiff(minute ,logouttime,logintime)  minutes from login where username = ? order by logouttime desc limit 1";
        PreparedStatement statement = null;
        ResultSet resultset = null;
        Login login = null;
        try {
            login = new Login();
            statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            resultset = statement.executeQuery();
            while (resultset.next()) {
                login.setUsername(username);
                login.setLastlogoutime(resultset.getTime("logouttime"));
                login.setLogcount(resultset.getInt("logcount"));
                login.setOnlinetime(resultset.getInt("minutes"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return login;
    }

    public static void saveLog(Login login) throws SQLException {
        String sql = "insert into login(username, logintime, logouttime, logcount) value (?, ?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, login.getUsername());
            statement.setTimestamp(2, new Timestamp(login.getLogintime().getTime()));
            statement.setTimestamp(3, new Timestamp(login.getLogouttime().getTime()));
            statement.setInt(4, login.getLogcount());
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
