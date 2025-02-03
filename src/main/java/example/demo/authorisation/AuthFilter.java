package example.demo.authorisation;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class AuthFilter extends OncePerRequestFilter {

    private final Authorisation authorisation;

    public AuthFilter(Authorisation authorisation) {
        this.authorisation = authorisation;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " prefix
            String username = authorisation.getUserByToken(token);

            if (username != null && authorisation.validateToken(token, username)) {
                // Set authentication context here (e.g., using UsernamePasswordAuthenticationToken)
                SecurityContextHolder.getContext().setAuthentication(null);
            }
        }

        chain.doFilter(request, response);
    }
}
