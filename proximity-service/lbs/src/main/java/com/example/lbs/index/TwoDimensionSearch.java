package com.example.lbs.index;

import com.example.lbs.dto.BusinessGeographicalInfoResponse;
import com.example.lbs.repository.BusinessRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TwoDimensionSearch implements IndexAlgorithm{

    BusinessRepository businessRepository;

    @Override
    public List<BusinessGeographicalInfoResponse> search(float latitude, float longitude, int radius) {
        double rangeLatitude = metersToLatitude(radius);
        double rangeLongitude = metersToLongitude(latitude,radius);
        return businessRepository.twoDimensionSearch(latitude, longitude, rangeLatitude, rangeLongitude).stream()
                .map(BusinessGeographicalInfoResponse::from)
                .toList();
    }

    private float radiusToMeter(int radius) {
        switch (radius) {
            case 1 :
                return 500f;
            case 2 :
                return 1000f;
            case 3 :
                return 2000f;
            case 4 :
                return 5000f;
            case 5 :
                return 20000f;
            default:
                throw new IllegalArgumentException("정의되지 않은 레벨입니다");
        }
    }

    private double metersToLatitude(int radius) {
        double meters = radiusToMeter(radius);
        // 위도 1도당 거리 계산 (Haversine 공식 사용)
        double latDiff = meters / 6371000.0; // 지구 반지름: 6371km (미터 단위로 변환)
        return Math.toDegrees(latDiff);
    }

    private double metersToLongitude(double lat, int radius) {
        double meters = radiusToMeter(radius);
        // 경도 1도당 거리 계산 (Haversine 공식 사용)
        double latRadians = Math.toRadians(lat);
        double lonDiff = meters / (6371000.0 * Math.cos(latRadians)); // 지구 반지름: 6371km (미터 단위로 변환)
        return Math.toDegrees(lonDiff);
    }
}
