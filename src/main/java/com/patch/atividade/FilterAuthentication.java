package com.patch.atividade;

import com.patch.atividade.service.AuthenticationService;
import com.patch.atividade.utils.CookieUtil;
import com.patch.atividade.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // This is a component because we need to inject the UserDetailsService to validate the token
@AllArgsConstructor
public class FilterAuthentication extends OncePerRequestFilter {

    private SecurityContextRepository securityContextRepository;
    private final CookieUtil cookieUtil = new CookieUtil();
    private final JwtUtil jwtUtil = new JwtUtil();

    private AuthenticationService userDatailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(!publicRoute(request)) {

            Cookie cookie = cookieUtil.getCookie(request, "JWT");// Get the cookie from the request
            String token = cookie.getValue();// Get the token from the cookie
            String username = jwtUtil.getUsername(token);// Validate the token

            // Create the authentication object
            UserDetails user = userDatailsService.loadUserByUsername(username);// Load the user from the token
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            user,
                            user.getPassword(),
                            user.getAuthorities());// Create the authentication object

            // Create a new context and set the authentication in it
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext(); // Create a new context
            securityContext.setAuthentication(authentication); // Set the authentication in the context because the session strategy will use it
            securityContextRepository.saveContext(securityContext, request, response); // Save the context in the session
        }
            filterChain.doFilter(request, response); // Call the next filter

    }

    private boolean publicRoute (HttpServletRequest request){
        return request.getRequestURI().equals("/auth/login") && request.getMethod().equals("POST");
    }
}
