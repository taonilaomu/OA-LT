package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * author  luhongtao
 * 2022/7/22 22:51:29
 **/
@javax.servlet.annotation.WebFilter(filterName = "encodeFilter", urlPatterns = "*.action", initParams = {@WebInitParam(name = "charset", value = "utf-8")})
public class WebFilter implements Filter {

    public static final String prefix = "WebFilter    ";

    public String charset;

    @Override
    public void init(FilterConfig filterConfig) {
        charset = filterConfig.getInitParameter("charset");
        System.out.println(prefix + "统一处理开始");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        request.setCharacterEncoding(charset);
        System.out.println(prefix + httpServletRequest.getRequestURI());
        response.setCharacterEncoding(charset);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("统一处理结束");
    }
}
