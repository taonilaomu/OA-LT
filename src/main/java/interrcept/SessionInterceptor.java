package interrcept;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * author  luhongtao
 * 2022/7/22 23:00:15
 **/
@javax.servlet.annotation.WebFilter(filterName = "sessionFilter", urlPatterns = {"*.jsp", "*.action"})
public class SessionInterceptor implements Filter {

    public static final String prefix = "SessionInterceptor ";

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println(prefix + "处理session");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            System.out.println(prefix+"请重新登录");
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/index.html");
            return;
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        System.out.println(prefix + "session处理结束了");
    }
}
