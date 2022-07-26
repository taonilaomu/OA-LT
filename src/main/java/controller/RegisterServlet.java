package controller;

import dto.LoginDto;
import dto.UserDto;
import entity.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * author  luhongtao
 * 2022/7/24 14:45:31
 **/
@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {

    public static final String prefix = "RegisterServlet  ";

    public static Logger logger = Logger.getLogger(RegisterServlet.class);

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info(prefix + "doPost");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(username, password);
        ServletContext context = getServletContext();
//     fixme   servlet如何传输json
        if (UserDto.isExist(user)) {
            logger.info(prefix + "该用户已存在");
            resp.sendRedirect(context.getContextPath() + req.getRequestURI());
        } else {
            UserDto.saveUser(user);
            logger.info(prefix + "注册成功");
            resp.sendRedirect(context.getContextPath() + "/index.html");
        }

    }
}
