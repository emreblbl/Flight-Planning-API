package com.example.flight.security.filter;

import io.jsonwebtoken.Jwts;
import lombok.var;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class JwtFilter extends GenericFilterBean {
    // AuthorizationValue = Bearer <JwtToken>
    private final UserDetailsService userDetailsService;
    private  String secret;

    public JwtFilter(String secret,UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        this.secret=secret;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var authorizationValue =((HttpServletRequest) servletRequest).getHeader("Authorization");
        System.out.println("do filter working ");
        if(Objects.nonNull(authorizationValue) && authorizationValue.startsWith("Bearer")){
            var token =authorizationValue.replace("Bearer","").trim();
            System.out.println(authorizationValue);
            var claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            var username = claims.getSubject();
            Date expiration = claims.getExpiration();
            Date now = new Date();
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(userDetails.getUsername().equals(username) && expiration.after(now)){
                Authentication usernameAndPasswordAuthentication= new UsernamePasswordAuthenticationToken(userDetails
                        ,userDetails.getUsername()
                        ,userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernameAndPasswordAuthentication);
                System.out.println("Token is valid.");


            }else {
                System.out.println("Token is unvalid.");
            }
            filterChain.doFilter(servletRequest,servletResponse);
    }}
}
