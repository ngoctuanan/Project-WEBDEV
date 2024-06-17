package com.codeElevate.SevieceSystemBooking.entity;
import com.codeElevate.SevieceSystemBooking.dto.UserDto;
import com.codeElevate.SevieceSystemBooking.enums.UserRole;
import  lombok.Data;
import  jakarta.persistence.*;
@Entity
@Table (name = "users")
@Data
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;


    private String name;

    private String lastname;

    private String phone;

    private UserRole role;

    public UserDto getDto(){
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setName(name);
        userDto.setEmail(email);
        userDto.setPhone(phone);

        return userDto;
    }
}