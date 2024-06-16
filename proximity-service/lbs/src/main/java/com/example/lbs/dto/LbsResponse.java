package com.example.lbs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LbsResponse {
    private int total;
    private List<BusinessGeographicalInfoResponse> businesses;

}
