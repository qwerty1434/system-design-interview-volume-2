package com.example.business.controller;

import com.example.business.dto.BusinessDetailResponse;
import com.example.business.dto.BusinessEntryRequest;
import com.example.business.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BusinessController {
    private final BusinessService businessService;

    @GetMapping("/v1/businesses/{id}")
    public ResponseEntity<BusinessDetailResponse> detail(@PathVariable("id") long id) {
        return ResponseEntity.ok(businessService.detailInfo(id));
    }

    @PostMapping("/v1/businesses")
    public ResponseEntity save(@RequestBody BusinessEntryRequest businessEntryRequest) {
        businessService.save(businessEntryRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/v1/businesses/{id}")
    public ResponseEntity update(@PathVariable("id") long id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/v1/businesses/{id}")
    public ResponseEntity delete(@PathVariable("id") long id) {
        return ResponseEntity.ok().build();
    }
}
