package com.example.room_reservation_test.controller;


import com.example.room_reservation_test.Service.RoomService;
import com.example.room_reservation_test.dto.requestDto.RoomRequestDto;
import com.example.room_reservation_test.dto.responseDto.RoomResponseDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/room")
    public void createRoom(@RequestBody @Valid RoomRequestDto roomRequestDto) {
        roomService.createRoom(roomRequestDto);
    }

    @DeleteMapping("/room/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }

    @GetMapping("/room")
    public List<RoomResponseDto> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/room/available")
    public List<RoomRequestDto> findAvailableRooms(
            @RequestParam("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return roomService.findAvailableRooms(startTime, endTime);
    }

    @PutMapping("/{Id}")
    public void updateRoom(@PathVariable Long roomId, @RequestBody RoomRequestDto roomRequestDto) {

        roomService.updateRoom(roomId, roomRequestDto);
    }

}
