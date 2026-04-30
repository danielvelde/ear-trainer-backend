package ear.trainer.eartrainerbackend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

public class JwtFilter extends OncePerRequestFilter {

    public static final String USER_ID_ATTR = "authenticatedUserId";
    public static final String USER_EMAIL_ATTR = "authenticatedUserEmail";

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.key}")
    private String apiKey;

    @Value("${ANALYZER_INTERNAL_TOKEN}")
    private String internalToken;

    private final RestTemplate restTemplate;

    public JwtFilter(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            filterChain.doFilter(request, response);
            return;
        }

        String path = request.getRequestURI();

        if (path.startsWith("/api/auth") || path.startsWith("/api/freesound") || path.startsWith("/api/audio")) {
            filterChain.doFilter(request, response);
            return;
        }

        String internalHeader = request.getHeader("X-Internal-Token");
        if (internalHeader != null && internalHeader.equals(internalToken)) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing or invalid Authorization header");
            return;
        }

        String token = authHeader.substring(7);

        try {
            // Call Supabase to validate token
            String url = supabaseUrl + "/auth/v1/user";

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            headers.set("apikey", apiKey);

            HttpEntity<Void> entity = new HttpEntity<>(headers);

            ResponseEntity<Map> userResponse = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
            Map<?, ?> body = userResponse.getBody();
            if (body == null || body.get("id") == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Could not resolve user from token.");
                return;
            }
            request.setAttribute(USER_ID_ATTR, UUID.fromString(body.get("id").toString()));
            if (body.get("email") != null) {
                request.setAttribute(USER_EMAIL_ATTR, body.get("email").toString());
            }

            // valid → continue
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            System.err.println("JWT validation failed: " + e.getMessage());
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid or expired token.");
        }
    }
}