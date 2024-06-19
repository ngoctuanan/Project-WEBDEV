package com.codeElevate.SevieceSystemBooking.services.company;

import java.io.IOException;
import java.util.List;
import com.codeElevate.ServiceBookingSystem.dto.ReservationDTO;

import com.codeElevate.SevieceSystemBooking.dto.AdDTO;


public interface CompanyService {
     boolean postAd(Long userId, AdDTO adDTO) throws IOException;
     
     List<AdDTO> getAllAds(Long userId);
    
     List<ReservationDTO> getAllAdBookings(Long companyId);

     AdDTO getAdById(Long adId);
    
     boolean updateAd(Long adId, AdDTO adDTO) throws IOException;

     boolean deleteAd(Long adId);
     

}
