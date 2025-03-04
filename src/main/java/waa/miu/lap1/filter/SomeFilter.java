package waa.miu.lap1.filter;

import jakarta.servlet.*;

import java.io.IOException;

public class SomeFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("SomeFilter - Before Filter");
        chain.doFilter(request, response);
    }
}
