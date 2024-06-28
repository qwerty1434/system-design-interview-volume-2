package com.example.hotel.domain.entity;

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
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Enumerated(EnumType.STRING)
    private RoomType roomTypeId;

    private Integer floor;

    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hotel_id")
    private Hotel hotel;

    private String name;

    private Boolean isAvailable;

    public void modify(RoomType roomTypeId, Integer floor, String number, String name, Boolean isAvailable) {
        this.roomTypeId = roomTypeId;
        this.floor = floor;
        this.number = number;
        this.name = name;
        this.isAvailable = isAvailable;
    }

    public void reserve() {
        if(isAvailable) throw new RuntimeException("이미 예약된 방입니다");
        isAvailable = true;
    }

    public void checkout() {
        isAvailable = false;
    }


}
