package com.designtartans.pigfarmingserver.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.designtartans.pigfarmingserver.exceptions.NoTokenProvidedException;
import com.designtartans.pigfarmingserver.utils.JwtService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            final String authHeader = request.getHeader("Authorization");

            if (isInsecureEndpoint(request)) {
                filterChain.doFilter(request, response);
            } else {
                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                    throw new NoTokenProvidedException("No Bearer token provided");
                }

                final String jwt = authHeader.substring(7);

                final String userPhoneNumber = jwtService.extractUsername(jwt);
                if (userPhoneNumber != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(userPhoneNumber);

                    if (jwtService.isTokenValid(jwt, userDetails)) {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());

                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }

                }

                filterChain.doFilter(request, response);
            }

        } catch (ExpiredJwtException ex) {
            handlerExceptionResolver.resolveException(request, response, null, ex);
        } catch (SignatureException ex) {
            handlerExceptionResolver.resolveException(request, response, null, ex);
        } catch (NoTokenProvidedException e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }

    }

    private boolean isInsecureEndpoint(HttpServletRequest req) {
        String requestString = req.getRequestURI();
        return (requestString.startsWith("/api/v1/auth/") || requestString.startsWith("/api/v1/shop/"));
    }

}
