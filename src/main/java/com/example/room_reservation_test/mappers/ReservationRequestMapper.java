package com.example.room_reservation_test.mappers;

import com.example.room_reservation_test.dto.requestDto.ReservationRequestDto;
import com.example.room_reservation_test.entity.Reservation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ReservationRequestMapper {
    public Reservation fromReservationRequestDto(ReservationRequestDto reservationRequestDto) {
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(reservationRequestDto, reservation);
        return reservation;
    }

    public ReservationRequestDto fromReservation(Reservation reservation) {
        ReservationRequestDto reservationRequestDto = new ReservationRequestDto();
        BeanUtils.copyProperties(reservation, reservationRequestDto);
        return reservationRequestDto;
    }

}
