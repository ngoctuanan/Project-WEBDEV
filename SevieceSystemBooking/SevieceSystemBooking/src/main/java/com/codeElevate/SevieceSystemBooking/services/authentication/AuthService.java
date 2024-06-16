package com.codeElevate.SevieceSystemBooking.services.authentication;

import com.codeElevate.SevieceSystemBooking.dto.SignupRequestDTO;
import com.codeElevate.SevieceSystemBooking.dto.UserDto;
import org.springframework.stereotype.Service;

public interface AuthService  {
    UserDto signupClient(SignupRequestDTO signupRequestDTO);

    Boolean presentByEmail(String email);
    UserDto signupCompany(SignupRequestDTO signupRequestDTO);
}
