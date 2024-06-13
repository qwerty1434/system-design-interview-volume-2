package com.example.business.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeospatialIndex {
    @EmbeddedId
    private GeospatialIndexId id;

    @MapsId("businessId")
    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

}
