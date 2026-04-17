package ear.trainer.eartrainerbackend.security;

import ear.trainer.eartrainerbackend.service.RateLimitingService;
import io.github.bucket4j.Bucket;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    @Autowired
    private RateLimitingService rateLimitingService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getRequestURI().startsWith("/api/auth")) {
            String ip = request.getRemoteAddr(); // Of haal IP uit 'X-Forwarded-For' header als je een proxy gebruikt
            Bucket bucket = rateLimitingService.resolveBucket(ip);

            if (bucket.tryConsume(1)) {
                // Token verbruikt, ga door naar de volgende filter
                filterChain.doFilter(request, response);
            } else {
                // Geen tokens meer!
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.getWriter().write("Too many requests. Try again later.");
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}