package com.example.hotel.api.dto.request;

import com.example.hotel.domain.entity.Hotel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelRequest {
    private String name;

    private String address;

    private String location;

    public Hotel toDomain() {
        return Hotel.builder()
                .name(name)
                .address(address)
                .location(location)
                .build();
    }

}
