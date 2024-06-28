package com.example.hotel.api.dto.response;

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
public class RoomResponse {
    @Enumerated(EnumType.STRING)
    private RoomType roomTypeId;

    private Integer floor;

    private String number;

    private Long hotelId;

    private String name;

    private Boolean isAvailable;

    public static RoomResponse from(Room room) {
        return RoomResponse.builder()
                .roomTypeId(room.getRoomTypeId())
                .floor(room.getFloor())
                .number(room.getNumber())
                .hotelId(room.getHotel().getHotelId())
                .name(room.getName())
                .isAvailable(room.getIsAvailable())
                .build();
    }
}
