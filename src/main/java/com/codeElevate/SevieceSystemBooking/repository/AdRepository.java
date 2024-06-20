package com.codeElevate.SevieceSystemBooking.repository;

import com.codeElevate.SevieceSystemBooking.entity.Ad;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> { 
    List<Ad>findAllByUserId(Long userId);
   List<Ad> findAllByServiceNameContaining(String name);
}
