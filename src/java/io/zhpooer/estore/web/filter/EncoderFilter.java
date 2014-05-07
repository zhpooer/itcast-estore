package io.zhpooer.estore.web.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class EncoderFilter implements Filter {

    private String encoding = "utf-8";

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
            FilterChain chain) throws IOException, ServletException {
        // 解决post乱码
        req.setCharacterEncoding(encoding);
        // 解决输出乱码
        resp.setCharacterEncoding(encoding);
        resp.setContentType("text/html;charset=" + encoding);
        chain.doFilter(req, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        String encoding = config.getInitParameter("encoding");
        if(encoding != null) {
            this.encoding = encoding;
        }
    }

}

class EncodingHttpRequest extends HttpServletRequestWrapper{

    public EncodingHttpRequest(HttpServletRequest request) {
        super(request);
    }
    
    private boolean isGet() {
        return this.getMethod().equalsIgnoreCase("get");
    }

    @Override
    public String getParameter(String name) {
        return super.getParameter(name);            
    }

    @Override
    public Map<?, ?> getParameterMap() {
        // TODO Auto-generated method stub
        return super.getParameterMap();
    }

    @Override
    public Enumeration<?> getParameterNames() {
        // TODO Auto-generated method stub
        return super.getParameterNames();
    }

    @Override
    public String[] getParameterValues(String name) {
        // TODO Auto-generated method stub
        return super.getParameterValues(name);
    }   

}