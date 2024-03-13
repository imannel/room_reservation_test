package com.example.room_reservation_test.dto.responseDto;

import lombok.Data;

@Data
public class RoomResponseDto {
    private Long id;
    private String name;
    private String description;
    private int capacity;

}
