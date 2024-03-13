package com.example.room_reservation_test.repository;

import com.example.room_reservation_test.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    @Query("SELECT r FROM Reservation r " +
            "WHERE r.room.id = :roomId AND ((r.startTime BETWEEN :startTime AND :endTime) " +
            "OR (r.endTime BETWEEN :startTime AND :endTime) " +
            "OR (:startTime BETWEEN r.startTime AND r.endTime) " +
            "OR (:endTime BETWEEN r.startTime AND r.endTime))")
    List<Reservation> findConflictingReservations(@Param("roomId") Long roomId, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
