package com.example.room_reservation_test.mappers;

import com.example.room_reservation_test.dto.requestDto.RoomRequestDto;
import com.example.room_reservation_test.dto.responseDto.RoomResponseDto;
import com.example.room_reservation_test.entity.Room;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RoomResponseMapper {
    public RoomResponseDto fromRoom(Room room) {
        RoomResponseDto roomResponseDto = new RoomResponseDto();
        BeanUtils.copyProperties(room, roomResponseDto);
        return roomResponseDto;
    }

    public Room fromRoomResponseDto(RoomResponseDto roomResponseDto) {
        Room room = new Room();
        BeanUtils.copyProperties(roomResponseDto, room);
        return room;
    }
}
