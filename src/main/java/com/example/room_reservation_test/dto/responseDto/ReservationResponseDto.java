package com.example.room_reservation_test.dto.responseDto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ReservationResponseDto {
    private Long id;
    private Long roomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
