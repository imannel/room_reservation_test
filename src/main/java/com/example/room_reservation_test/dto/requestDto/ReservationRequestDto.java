package com.example.room_reservation_test.dto.requestDto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationRequestDto {
    private Long roomId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
