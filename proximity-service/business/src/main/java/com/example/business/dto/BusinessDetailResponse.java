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
public class BusinessDetailResponse {

    private String name;

    private String city;

    private String state;

    private String country;

    public static BusinessDetailResponse from(Business business) {
        return BusinessDetailResponse.builder()
                .name(business.getName())
                .city(business.getCity())
                .state(business.getState())
                .country(business.getCountry())
                .build();
    }

}
