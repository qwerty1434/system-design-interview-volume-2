package com.example.business.service;

import ch.hsr.geohash.GeoHash;
import com.example.business.dto.BusinessDetailResponse;
import com.example.business.dto.BusinessEntryRequest;
import com.example.business.entity.Business;
import com.example.business.entity.GeospatialIndex;
import com.example.business.entity.GeospatialIndexId;
import com.example.business.repository.BusinessRepository;
import com.example.business.repository.GeospatialIndexRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BusinessService {
    private final BusinessRepository businessRepository;
    private final GeospatialIndexRepository geospatialIndexRepository;
    private final RedisTemplate<String, String> redisTemplateForGeoHash;
    private final RedisTemplate<String, Business> redisTemplateForBusiness;
    private static final Integer PRECISION_LEVEL = 6;
    private static final String BUSINESS_DETAIL_PREFIX = "BUSINESS::";

    public BusinessDetailResponse detailInfo(long id) {
        Business business = redisTemplateForBusiness.opsForValue().get(id);
        if(business == null) {
            business = businessRepository.findById(id).orElseThrow(RuntimeException::new);
            redisTemplateForBusiness.opsForValue().set(Long.toString(id), business);
        }

        return BusinessDetailResponse.from(business);
    }

    @Transactional
    public void save(BusinessEntryRequest businessEntryRequest) {
        Business business = Business.builder()
                .name(businessEntryRequest.getName())
                .address(businessEntryRequest.getAddress())
                .city(businessEntryRequest.getCity())
                .state(businessEntryRequest.getState())
                .country(businessEntryRequest.getCountry())
                .latitude(businessEntryRequest.getLatitude())
                .longitude(businessEntryRequest.getLongitude())
                .build();
        businessRepository.save(business);

        GeoHash geoHash = GeoHash.withCharacterPrecision(businessEntryRequest.getLatitude(), businessEntryRequest.getLongitude(), PRECISION_LEVEL);
        for (int i = 1; i <= geoHash.toBase32().length(); i++) {
            String geoHashKey = geoHash.toBase32().substring(0,i);
            GeospatialIndexId id = GeospatialIndexId
                    .builder()
                    .businessId(business.getBusinessId())
                    .geoHash(geoHashKey)
                    .build();
            GeospatialIndex geospatialIndex = GeospatialIndex.builder()
                    .id(id)
                    .business(business)
                    .build();
            geospatialIndexRepository.save(geospatialIndex);

            redisTemplateForGeoHash.opsForSet().add(geoHashKey, Long.toString(business.getBusinessId()));
        }

        redisTemplateForBusiness.opsForValue().set(BUSINESS_DETAIL_PREFIX + business.getBusinessId(), business);
    }
}
