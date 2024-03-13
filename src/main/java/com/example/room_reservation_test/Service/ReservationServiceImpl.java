package com.example.room_reservation_test.Service;

import com.example.room_reservation_test.Exception.ReservationException;
import com.example.room_reservation_test.dto.requestDto.ReservationRequestDto;
import com.example.room_reservation_test.dto.requestDto.RoomRequestDto;
import com.example.room_reservation_test.entity.Reservation;
import com.example.room_reservation_test.entity.Room;
import com.example.room_reservation_test.mappers.ReservationRequestMapper;
import com.example.room_reservation_test.mappers.ReservationResponseMapper;
import com.example.room_reservation_test.mappers.RoomRequestMapper;
import com.example.room_reservation_test.repository.ReservationRepository;
import com.example.room_reservation_test.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ReservationRequestMapper reservationRequestMapper;

    @Autowired
    private RoomRepository roomRepository;


    public void saveReservation(ReservationRequestDto reservationRequestDto) {

        Reservation reservation = reservationRequestMapper.fromReservationRequestDto(reservationRequestDto);
        Reservation savedReservation = reservationRepository.save(reservation);

    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public void deleteAllReservations() {
        reservationRepository.deleteAll();
    }

    public void reserveRoom(Long roomId, LocalDateTime startTime, LocalDateTime endTime) throws ReservationException {
        List<Reservation> conflictingReservations = reservationRepository.findConflictingReservations(roomId, startTime, endTime);
        if (!conflictingReservations.isEmpty()) {
            throw new ReservationException("La salle est déjà réservée pour cette période.");
        }

        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (!optionalRoom.isPresent()) {
            throw new ReservationException("La salle spécifiée n'existe pas.");
        }

        Reservation reservation = new Reservation();
        reservation.setRoom(optionalRoom.get());
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        reservationRepository.save(reservation);
    }

    public void updateReservation(Long reservationId, LocalDateTime newStartTime, LocalDateTime newEndTime) throws ReservationException {

        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new ReservationException("La réservation spécifiée n'existe pas."));
        Long roomId = reservation.getRoom().getId();
        List<Reservation> conflictingReservations = reservationRepository.findConflictingReservations(roomId, newStartTime, newEndTime);
        conflictingReservations.removeIf(r -> r.getId().equals(reservationId));
        if (!conflictingReservations.isEmpty()) {
            throw new ReservationException("La nouvelle période de réservation entre en conflit avec d'autres réservations.");
        }

        reservation.setStartTime(newStartTime);
        reservation.setEndTime(newEndTime);
        reservationRepository.save(reservation);
    }


}
