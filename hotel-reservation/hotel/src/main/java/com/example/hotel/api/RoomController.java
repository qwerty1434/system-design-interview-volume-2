package com.example.hotel.api;

import com.example.hotel.api.dto.request.RoomRequest;
import com.example.hotel.domain.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/v1/hotels/rooms/{roomId}")
    public ResponseEntity get(@PathVariable("roomId") long roomId) {
        return ResponseEntity.ok(roomService.get(roomId));
    }

    @PostMapping("/v1/hotels/rooms")
    public ResponseEntity save(RoomRequest roomRequest) {
        roomService.save(roomRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/v1/hotels/rooms/{roomId}")
    public ResponseEntity modify(@PathVariable("roomId") long roomId,
                                 RoomRequest roomRequest) {
        roomService.modify(roomId, roomRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/v1/hotels/rooms/{roomId}")
    public ResponseEntity delete(@PathVariable("roomId") long roomId) {
        roomService.delete(roomId);
        return ResponseEntity.ok().build();
    }

}
