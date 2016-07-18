package by_.gsu.epamlab.todoapp.filters;

import by_.gsu.epamlab.todoapp.utils.Constants;
import by_.gsu.epamlab.todoapp.utils.ServletUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Checks index.xhtml for users with UUID cookies.
 * If UUID cookie found and no Principals stored in session
 * - forward request to auto login servlet.
 */
public class AutoLoginFilter implements Filter {
    private static final String AUTOLOGIN_PAGE = "autologin.xhtml";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getUserPrincipal() == null) {
            String uuid = ServletUtils.getCookieValue(request, Constants.UUID);
            if (uuid != null) {
                request.getRequestDispatcher(AUTOLOGIN_PAGE).forward(request, response);
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}