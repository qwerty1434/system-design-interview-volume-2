package com.example.hotel.api;


import com.example.hotel.api.dto.request.HotelRequest;
import com.example.hotel.api.dto.response.HotelResponse;
import com.example.hotel.domain.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @GetMapping("/v1/hotels/{hotelId}")
    public ResponseEntity<HotelResponse> get(@PathVariable("hotelId") long hotelId) {
        return ResponseEntity.ok(hotelService.get(hotelId));
    }

    @PostMapping("/v1/hotels")
    public ResponseEntity save(HotelRequest hotelRequest) {
        hotelService.save(hotelRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/v1/hotels/{hotelId}")
    public ResponseEntity modify(@PathVariable("hotelId") long hotelId,
                                 HotelRequest hotelRequest) {
        hotelService.modify(hotelId, hotelRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/v1/hotels/{hotelId}")
    public ResponseEntity delete(@PathVariable("hotelId") long hotelId) {
        hotelService.delete(hotelId);
        return ResponseEntity.ok().build();
    }


}
