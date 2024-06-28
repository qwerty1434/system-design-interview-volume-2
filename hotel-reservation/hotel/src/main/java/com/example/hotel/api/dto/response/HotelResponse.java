package com.example.hotel.api.dto.response;

import com.example.hotel.domain.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelResponse {
    private String name;

    private String address;

    private String location;

    public static HotelResponse from(Hotel hotel) {
        return HotelResponse.builder()
                .name(hotel.getName())
                .address(hotel.getAddress())
                .location(hotel.getLocation())
                .build();
    }
}
