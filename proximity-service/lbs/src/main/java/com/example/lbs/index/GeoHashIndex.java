package com.example.lbs.index;

import ch.hsr.geohash.GeoHash;
import com.example.lbs.dto.BusinessGeographicalInfoResponse;
import com.example.lbs.entity.Business;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GeoHashIndex implements IndexAlgorithm{
    private final RedisTemplate<String, Business> redisTemplateForBusiness;
    private final RedisTemplate<String, String> redisTemplateForGeoHash;
    private static final String BUSINESS_DETAIL_PREFIX = "BUSINESS::";

    @Override
    public List<BusinessGeographicalInfoResponse> search(float latitude, float longitude, int radius) {
        GeoHash geoHash = GeoHash.withCharacterPrecision(latitude, longitude, radius);

        List<GeoHash> listOfGeoHashes = new ArrayList<>();
        listOfGeoHashes.add(geoHash);
        listOfGeoHashes.addAll(Arrays.asList(geoHash.getAdjacent()));

        return listOfGeoHashes.stream()
                .map(geoHashValue -> redisTemplateForGeoHash.opsForSet().members(geoHashValue.toBase32()))
                .flatMap(Set::stream)
                .map(id -> redisTemplateForBusiness.opsForValue().get(BUSINESS_DETAIL_PREFIX+id))
                .map(BusinessGeographicalInfoResponse::from)
                .collect(Collectors.toList());
    }

}
