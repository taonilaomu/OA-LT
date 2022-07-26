package controller;

import dto.LoginDto;
import entity.Login;
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
@Slf4j
@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {

    public static Logger logger = Logger.getLogger(LoginServlet.class);
    public static final String logPrex = "LoginServlet  ";

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        fixme 注解也没有起到作用
        logger.info("just test log");
        log.info(logPrex + "doPost");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        ServletContext context = super.getServletContext();
        if (username != null && password != null
                && username.equalsIgnoreCase(context.getInitParameter("username"))
                && password.equalsIgnoreCase(context.getInitParameter("password"))) {
            HttpSession session = req.getSession(true);
            System.out.println(logPrex + session.toString());
            session.setAttribute("username", username);
            //        设置全局变量——在线人名
            String userSet = context.getInitParameter("userSet") + username;
            context.setAttribute("userSet", userSet);
            //        设置全局变量——在线人数
            int existPersons = Integer.parseInt(context.getInitParameter("existPersons")) + 1;
            context.setAttribute("existPersons", "" + existPersons);
            Login lastLoginInfo = null;
            try {
                lastLoginInfo = LoginDto.selectByName(username);
                lastLoginInfo.setLogintime(new Date());
                System.out.println(logPrex + "成功插入" + lastLoginInfo.toString());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //        session内放置变量登录信息
            session.setAttribute("logininfo", lastLoginInfo);
            resp.sendRedirect(req.getContextPath() + "/home.jsp");
        } else {
            System.out.println("用户名或密码不能为空！");
            resp.sendRedirect(req.getContextPath() + "/index.html");
            return;
        }

    }

}
