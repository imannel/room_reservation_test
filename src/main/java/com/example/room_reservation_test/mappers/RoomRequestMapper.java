package com.example.room_reservation_test.mappers;

import com.example.room_reservation_test.dto.requestDto.RoomRequestDto;
import com.example.room_reservation_test.entity.Room;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RoomRequestMapper {
    public Room fromRoomRequestDto(RoomRequestDto roomRequestDto) {
        Room room = new Room();
        room.setName(roomRequestDto.getName());
        room.setDescription(roomRequestDto.getDescription());
        room.setCapacity(roomRequestDto.getCapacity());
        return room;
    }

    public RoomRequestDto fromRoomRequest(Room room) {
        RoomRequestDto roomRequestDto = new RoomRequestDto();
        BeanUtils.copyProperties(room, roomRequestDto);
        return roomRequestDto;
    }
}