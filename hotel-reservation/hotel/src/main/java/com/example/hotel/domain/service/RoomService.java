package com.example.hotel.domain.service;

import com.example.hotel.api.dto.request.RoomRequest;
import com.example.hotel.api.dto.response.RoomResponse;
import com.example.hotel.domain.entity.Hotel;
import com.example.hotel.domain.entity.Room;
import com.example.hotel.persistence.HotelRepository;
import com.example.hotel.persistence.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    public RoomResponse get(long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 방입니다"));

        return RoomResponse.from(room);
    }

    public void save(RoomRequest roomRequest) {
        Hotel hotel = hotelRepository.findById(roomRequest.getHotelId())
                .orElseThrow(() -> new RuntimeException("존재하지 않는 호텔입니다"));
        Room room = roomRequest.toDomain(hotel);

        roomRepository.save(room);
    }

    public void modify(long roomId, RoomRequest roomRequest) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 방입니다"));
        room.modify(roomRequest.getRoomTypeId(), roomRequest.getFloor(), roomRequest.getNumber(), roomRequest.getName(), roomRequest.getIsAvailable());
    }

    public void delete(long roomId) {
        roomRepository.deleteById(roomId);
    }
}
