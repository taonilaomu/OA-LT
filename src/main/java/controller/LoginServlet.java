package controller;

import dto.LoginDto;
import dto.UserDto;
import entity.Login;
import entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

/**
 * author  luhongtao
 * 2022/7/20 14:20:06
 **/
@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {

    public static Logger logger = Logger.getLogger(LoginServlet.class);

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        study 注解也没有起到作用
        logger.info("doPost");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(username, password);
        ServletContext context = super.getServletContext();
        if (UserDto.isExist(user)) {
            HttpSession session = req.getSession(true);
            logger.info(session.toString());
            session.setAttribute("username", username);
            //        设置全局变量——在线人名
            String userSet = context.getInitParameter("userSet") + username;
            context.setAttribute("userSet", userSet);

            Login lastLoginInfo = LoginDto.selectByName(username);
            lastLoginInfo.setLogintime(new Date());
            logger.info("成功插入" + lastLoginInfo.toString());
            //        session内放置变量登录信息
            session.setAttribute("logininfo", lastLoginInfo);
            resp.sendRedirect(req.getContextPath() + "/home.jsp");
        } else {
            logger.info("用户名或密码错误！");
            resp.sendRedirect(req.getContextPath() + "/index.html");
            return;
        }

    }

}
