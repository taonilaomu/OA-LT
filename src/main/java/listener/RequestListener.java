package listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * author  luhongtao
 * 2022/7/23 17:28:53
 **/
@WebListener
public class RequestListener implements ServletRequestListener {
    public static final String prefix = "RequestListener    ";

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        Enumeration enumeration = request.getParameterNames();
//        请求内容打印
        while (enumeration.hasMoreElements()) {
            String name = (String) enumeration.nextElement();
            System.out.println(prefix + request.getAttribute(name));
        }
    }
}
