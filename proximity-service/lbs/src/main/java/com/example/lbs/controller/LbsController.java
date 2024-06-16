package com.example.lbs.controller;

import com.example.lbs.dto.LbsResponse;
import com.example.lbs.service.LbsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lbs")
public class LbsController {
    private final LbsService lbsService;

    private static final List<Integer> PRECISION_LEVEL_RANGE = List.of(1,2,3,4,5,6);

    @GetMapping("/v1/search/nearby")
    public ResponseEntity<LbsResponse> nearbySearch(@RequestParam(name = "latitude") float latitude,
                                                    @RequestParam(name = "longitude") float longitude,
                                                    @RequestParam(name = "radius") int radius) {
        if(!PRECISION_LEVEL_RANGE.contains(radius)) throw new RuntimeException("radiusOutOfRangeException");
        LbsResponse lbsResponse = lbsService.search(latitude, longitude, radius);
        // TODO : Pagination
        return ResponseEntity.ok(lbsResponse);
    }
}
