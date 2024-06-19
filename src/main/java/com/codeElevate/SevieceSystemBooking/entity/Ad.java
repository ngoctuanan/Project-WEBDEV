package com.codeElevate.SevieceSystemBooking.entity;



import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.codeElevate.SevieceSystemBooking.dto.AdDTO;

import jakarta.persistence.*;
import lombok.Data;


@Entity(name = "ads")
@Data
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String serviceName;

    private String description;
    
    private Double price;

    @Lob
    @Column(columnDefinition = "Longblob")
    private byte[] img;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public AdDTO getAdDto(){
        AdDTO adDTO = new AdDTO();
        adDTO.setId(id);
        adDTO.setServiceName(serviceName);
        adDTO.setDescription(description);
        adDTO.setPrice(price);
        adDTO.setCompanyName(user.getName());
        adDTO.setReturnedImg(img);
        
        return adDTO;
    }
}
