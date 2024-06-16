package com.codeElevate.SevieceSystemBooking.repository;
import  com.codeElevate.SevieceSystemBooking.entity.User;
import  org.springframework.data.jpa.repository.JpaRepository;
import  org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

      User findFirstByEmail(String email);
}
