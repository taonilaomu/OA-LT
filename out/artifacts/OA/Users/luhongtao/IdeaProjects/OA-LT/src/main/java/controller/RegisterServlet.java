package controller;

import dto.LoginDto;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        logger.info(prefix+"doPost");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

    }
}
