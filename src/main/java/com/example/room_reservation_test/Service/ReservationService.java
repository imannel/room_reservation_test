package com.example.room_reservation_test.Service;

import com.example.room_reservation_test.Exception.ReservationException;
import com.example.room_reservation_test.dto.requestDto.ReservationRequestDto;
import com.example.room_reservation_test.dto.requestDto.RoomRequestDto;
import com.example.room_reservation_test.entity.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    public void saveReservation(ReservationRequestDto reservationDto);
    public void deleteReservation(Long id);
    public List<Reservation> getAllReservations();
    public void deleteAllReservations();
    public void reserveRoom(Long roomId, LocalDateTime startTime, LocalDateTime endTime) throws ReservationException;
    public void updateReservation(Long reservationId, LocalDateTime newStartTime, LocalDateTime newEndTime) throws ReservationException ;

    }
