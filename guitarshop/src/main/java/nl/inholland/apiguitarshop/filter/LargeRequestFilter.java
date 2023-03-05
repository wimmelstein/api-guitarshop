package nl.inholland.apiguitarshop.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
@Order(1)
public class LargeRequestFilter implements Filter {

    private final Logger log = LoggerFactory.getLogger(LargeRequestFilter.class);
    private static final int MAX_SIZE = 60_000;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        int size = servletRequest.getContentLength();
        log.info("Request size: {}", size);
        if (size > MAX_SIZE) {
            log.error("Request with size {} rejected");
            throw new ServletException("Request too large");
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
