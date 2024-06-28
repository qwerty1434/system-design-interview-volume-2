package com.example.hotel.api.dto.request;

import com.example.hotel.domain.entity.Hotel;
import com.example.hotel.domain.entity.Room;
import com.example.hotel.domain.entity.RoomType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequest {
    @Enumerated(EnumType.STRING)
    private RoomType roomTypeId;

    private Integer floor;

    private String number;

    private Long hotelId;

    private String name;

    private Boolean isAvailable;

    public Room toDomain(Hotel hotel) {
        return Room.builder()
                .roomTypeId(roomTypeId)
                .floor(floor)
                .number(number)
                .hotel(hotel)
                .name(name)
                .isAvailable(isAvailable)
                .build();
    }
}
