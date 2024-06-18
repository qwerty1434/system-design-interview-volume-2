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
public class Business {
    @Id
    @Column(name="business_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long businessId;

    private String name;

    private String address;

    private String city;

    private String state;

    private String country;

    private float latitude;

    private float longitude;

}
