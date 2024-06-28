package com.example.hotel.domain.service;

import com.example.hotel.api.dto.request.HotelRequest;
import com.example.hotel.api.dto.response.HotelResponse;
import com.example.hotel.domain.entity.Hotel;
import com.example.hotel.persistence.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelResponse get(long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 호텔입니다"));
        return HotelResponse.from(hotel);
    }

    public void save(HotelRequest hotelRequest) {
        Hotel hotel = hotelRequest.toDomain();
        hotelRepository.save(hotel);
    }

    public void modify(long hotelId, HotelRequest hotelRequest) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 호텔입니다"));
        hotel.modify(hotelRequest.getName(),hotelRequest.getAddress(),hotelRequest.getLocation());

    }

    public void delete(long hotelId) {
        hotelRepository.deleteById(hotelId);
    }

}
