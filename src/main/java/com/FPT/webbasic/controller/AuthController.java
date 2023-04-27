package com.FPT.webbasic.controller;

import com.FPT.webbasic.dto.AppUserDto;
import com.FPT.webbasic.security.JwtProvider;
import com.FPT.webbasic.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private UserDetailsServiceImpl userDetailsService;
    private PasswordEncoder passwordEncoder;
    private JwtProvider jwtProvider;
    @GetMapping("/token")
    public String getToken(@RequestBody AppUserDto authRequest) throws Exception {
        // Get user details
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        if(passwordEncoder.matches(authRequest.getPassword(), userDetails.getPassword())){
            // Generate token
            return jwtProvider.generateToken(authRequest.getUsername());
        }

        throw new Exception("User details invalid.");
    }
}
