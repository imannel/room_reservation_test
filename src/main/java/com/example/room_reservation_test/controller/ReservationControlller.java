package com.example.room_reservation_test.controller;

import com.example.room_reservation_test.Exception.ReservationException;
import com.example.room_reservation_test.Service.ReservationService;
import com.example.room_reservation_test.dto.requestDto.ReservationRequestDto;
import com.example.room_reservation_test.entity.Reservation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("api/v1")
@RestController
public class ReservationControlller {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/reservation")
    public void createReservation(@RequestBody @Valid ReservationRequestDto reservationRequestDto) {
        reservationService.saveReservation(reservationRequestDto);
    }

    @DeleteMapping("reservation/{id}")
    public void deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }

    @GetMapping("/reservation")
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @PostMapping("/reservation/reserve")
    public void reserveRoom(
            @RequestParam("roomId") Long roomId,
            @RequestParam("startTime") LocalDateTime startTime,
            @RequestParam("endTime") LocalDateTime endTime) throws ReservationException {
        reservationService.reserveRoom(roomId, startTime, endTime);
    }

    @PutMapping("/{reservationId}")
    public void updateReservation(@PathVariable Long reservationId, @RequestParam("newStartTime") LocalDateTime newStartTime, @RequestParam("newEndTime") LocalDateTime newEndTime) {

        reservationService.updateReservation(reservationId, newStartTime, newEndTime);


    }


}
