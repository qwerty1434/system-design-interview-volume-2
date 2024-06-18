package com.example.lbs.service;

import com.example.lbs.dto.BusinessGeographicalInfoResponse;
import com.example.lbs.dto.LbsResponse;
import com.example.lbs.index.IndexAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LbsService {
    private final IndexAlgorithm indexAlgorithm;

    public LbsResponse search(float latitude, float longitude, int radius) {
        List<BusinessGeographicalInfoResponse> nearbyBusinesses = indexAlgorithm.search(latitude, longitude, radius);
        return LbsResponse.builder()
                .businesses(nearbyBusinesses)
                .total(nearbyBusinesses.size())
                .build();
    }


}
