package org.mpei.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.mpei.controller.adminApi.StatsController;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StatsFilter implements Filter {

    private final StatsController statsEndpoint;

    public StatsFilter(StatsController statsEndpoint) {
        this.statsEndpoint = statsEndpoint;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        chain.doFilter(request, response);
        int statusCode = ((HttpServletResponse) response).getStatus();
        statsEndpoint.recordRequest(statusCode);
    }
}
