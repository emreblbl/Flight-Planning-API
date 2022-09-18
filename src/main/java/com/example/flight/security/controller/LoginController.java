package com.example.flight.security.controller;

import com.example.flight.security.dto.WebUser;
import com.example.flight.security.service.JwtTokenProvider;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping
    public String authenticateAndCreateToken(@RequestBody WebUser user){
        try{
            var authentication = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
            authenticationManager.authenticate(authentication);
            return jwtTokenProvider.getGenerateToken(user);
        }catch (AuthenticationException authenticationException){
            System.out.println("Wrong username and password"+authenticationException.getMessage());
            throw authenticationException;
        }


    }
}
