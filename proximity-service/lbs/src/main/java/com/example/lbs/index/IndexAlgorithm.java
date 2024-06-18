package com.example.lbs.index;


import com.example.lbs.dto.BusinessGeographicalInfoResponse;

import java.util.List;

public interface IndexAlgorithm {

    List<BusinessGeographicalInfoResponse> search(float latitude, float longitude, int radius);
}
