package listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * author  luhongtao
 * 2022/7/21 08:59:25
 **/
@WebListener
public class SessionListener implements HttpSessionListener {

    public static Logger logger = Logger.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        logger.info("sessionCreated");
        HttpSession session = se.getSession();
        ServletContext context = session.getServletContext();
        //        设置全局变量——在线人数
        int existPersons = Integer.parseInt(context.getInitParameter("existPersons")) + 1;
        context.setAttribute("existPersons", "" + existPersons);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        logger.info("sessionDestroyed");
        HttpSession session = se.getSession();
        ServletContext context = session.getServletContext();
        //        设置全局变量——在线人数
        int existPersons = Integer.parseInt(context.getInitParameter("existPersons")) - 1;
        context.setAttribute("existPersons", "" + existPersons);
    }
}
