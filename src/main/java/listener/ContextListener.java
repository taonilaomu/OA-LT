package listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * author  luhongtao
 * 2022/7/23 17:34:37
 **/
@WebListener
public class ContextListener implements ServletContextListener {

    public static Logger logger = Logger.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("容器启动");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("容器关闭");
    }
}
