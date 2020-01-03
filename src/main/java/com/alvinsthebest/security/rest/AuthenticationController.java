package com.alvinsthebest.security.rest;

import com.alvinsthebest.security.repo.model.AuthenticationRequest;
import com.alvinsthebest.security.repo.model.AuthenticationResponse;
import com.alvinsthebest.security.service.AlvinsUserDetailsService;
import com.alvinsthebest.security.util.Constants;
import com.alvinsthebest.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AlvinsUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(Constants.AUTH)
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        }catch(BadCredentialsException e) {
            throw new Exception("Incorrect username or pass", e);
        }

        final UserDetails userDetails =  userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
