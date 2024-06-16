package com.codeElevate.SevieceSystemBooking.controller;

import com.codeElevate.SevieceSystemBooking.dto.AuthenticationRequest;
import com.codeElevate.SevieceSystemBooking.dto.SignupRequestDTO;
import com.codeElevate.SevieceSystemBooking.dto.UserDto;
import com.codeElevate.SevieceSystemBooking.entity.User;
import com.codeElevate.SevieceSystemBooking.repository.UserRepository;
import com.codeElevate.SevieceSystemBooking.services.authentication.AuthService;
import com.codeElevate.SevieceSystemBooking.services.jwt.UserDetailsServiceImpl;

import com.codeElevate.SevieceSystemBooking.utill.JwUtil;
import jakarta.servlet.http.HttpServletResponse;
import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Repository
public class AuthenticationController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwUtil jwUtil;

    @Autowired
    private  UserRepository userRepository;

    public  static final String TOKEN_PREFIX = "Bearer";

    public static final String HEADER_STRING = "Authorization";

    @PostMapping("/client/sign-up")
    public ResponseEntity<?> signupClient(@RequestBody SignupRequestDTO signupRequestDTO){
         if(authService.presentByEmail(signupRequestDTO.getEmail())){
             return  new ResponseEntity<>("Client already exists with this Email!", HttpStatus.NOT_ACCEPTABLE);
         }
         UserDto createdUser = authService.signupClient(signupRequestDTO);
         return  new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @PostMapping("/company/sign-up")
    public ResponseEntity<?> signupCompany(@RequestBody SignupRequestDTO signupRequestDTO){
        if(authService.presentByEmail(signupRequestDTO.getEmail())){
            return  new ResponseEntity<>("Company already exist with this Email!", HttpStatus.NOT_ACCEPTABLE);
        }
        UserDto createdUser = authService.signupCompany(signupRequestDTO);
        return  new ResponseEntity<>(createdUser, HttpStatus.OK);
    }

    @PostMapping({"/Authenticate"})
    public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
                                          HttpServletResponse response) throws IOException, JSONException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),authenticationRequest.getPassword()
            ));
        } catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt =jwUtil.generateToken(userDetails.getUsername());
        User user = userRepository.findFirstByEmail(authenticationRequest.getUsername());

        response.getWriter().write(new JSONObject()
                .put("userId", user.getId())
                .put("role", user.getRole())
                .toString()
        );

        response.addHeader("Access-Control-Expose-Headers", "Authorization");
        response.addHeader("Access-Control-Allow-Headers", "Authorization," +
                "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");

        response.addHeader(HEADER_STRING, TOKEN_PREFIX+jwt);

    }
}
