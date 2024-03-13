package com.example.room_reservation_test.mappers;

import com.example.room_reservation_test.dto.requestDto.ReservationRequestDto;
import com.example.room_reservation_test.dto.responseDto.ReservationResponseDto;
import com.example.room_reservation_test.dto.responseDto.RoomResponseDto;
import com.example.room_reservation_test.entity.Reservation;
import com.example.room_reservation_test.entity.Room;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ReservationResponseMapper {
    public ReservationResponseDto fromReservation(Reservation reservation) {
        ReservationResponseDto reservationResponseDto = new ReservationResponseDto();
        BeanUtils.copyProperties(reservation, reservationResponseDto);
        return reservationResponseDto;
    }

    public Reservation fromReservationResponseDto(ReservationResponseDto reservationResponseDto) {
        Reservation reservation = new Reservation();
        BeanUtils.copyProperties(reservationResponseDto, reservation);
        return reservation;
    }

}
