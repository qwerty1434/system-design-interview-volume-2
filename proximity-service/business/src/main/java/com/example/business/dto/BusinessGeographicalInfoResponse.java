package com.example.business.dto;

import com.example.business.entity.Business;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessGeographicalInfoResponse {
    private long businessId;
    
    private float latitude;
    
    private float longitude;

    public static BusinessGeographicalInfoResponse from(Business business) {
        return BusinessGeographicalInfoResponse.builder()
                .businessId(business.getBusinessId())
                .latitude(business.getLatitude())
                .longitude(business.getLongitude())
                .build();
    }
}
