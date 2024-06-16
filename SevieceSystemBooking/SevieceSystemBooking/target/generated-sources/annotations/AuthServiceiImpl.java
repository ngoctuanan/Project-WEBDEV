package com.codeElevate.SevieceSystemBooking.services.authentication;

import com.codeElevate.SevieceSystemBooking.dto.SignupRequestDTO;
import com.codeElevate.SevieceSystemBooking.dto.UserDto;
import com.codeElevate.SevieceSystemBooking.entity.User;
import com.codeElevate.SevieceSystemBooking.enums.UserRole;
import com.codeElevate.SevieceSystemBooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceiImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    public UserDto signupClient(SignupRequestDTO signupRequestDTO){
        User user = new User();

        user.setName(signupRequestDTO.getName());
        user.setLastname(signupRequestDTO.getLastname());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPhone(signupRequestDTO.getPhone());
        user.setPassword(signupRequestDTO.getPassword());

        user.setRole(UserRole.CLIENT);

        return userRepository.save(user).getDto();
    }
    public Boolean presentByEmail(String email){
        return userRepository.findFirstByEmail(email) != null;
    }
    public UserDto signupCompany(SignupRequestDTO signupRequestDTO){
        User user = new User();

        user.setName(signupRequestDTO.getName());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPhone(signupRequestDTO.getPhone());
        user.setPassword(signupRequestDTO.getPassword());

        user.setRole(UserRole.COMPANY);

        return userRepository.save(user).getDto();
    }
}
