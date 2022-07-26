package controller;

import dto.LoginDto;
import entity.Login;
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
 * 2022/7/20 21:55:31
 **/
@WebServlet(value = "/logout")
public class LogoutServlet extends HttpServlet {

    public static final Logger logger = Logger.getLogger(LogoutServlet.class);

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("doGet");
        HttpSession session = req.getSession();
        ServletContext context = session.getServletContext();
        int existPersons = Integer.parseInt(context.getInitParameter("existPersons")) - 1;
        //        设置全局变量——在线人数
        context.setAttribute("existPersons", "" + existPersons);

        Login login = (Login) session.getAttribute("logininfo");
        logger.info(login.toString());
        login.setLogcount(login.getLogcount() + 1);
        login.setLogouttime(new Date());
        try {
            LoginDto.saveLog(login);
            System.out.println("成功插入" + login.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        session.invalidate();
        logger.info("退出了");
        req.getRequestDispatcher("/index.html").forward(req, resp);
    }
}
