package com.example.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessEntryRequest {

    private String name;

    private String address;

    private String city;

    private String state;

    private String country;

    private Float latitude;

    private Float longitude;
}
