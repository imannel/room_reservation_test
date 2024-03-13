package com.example.room_reservation_test.Service;

import com.example.room_reservation_test.dto.requestDto.RoomRequestDto;
import com.example.room_reservation_test.dto.responseDto.RoomResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public interface RoomService {
    public  void createRoom(RoomRequestDto roomRequestDto);
    public void deleteRoom(Long id);
    public List<RoomResponseDto> getAllRooms();
    public List<RoomRequestDto> findAvailableRooms(LocalDateTime startTime, LocalDateTime endTime);

    public void updateRoom(Long roomId, RoomRequestDto roomRequestDto);

}
