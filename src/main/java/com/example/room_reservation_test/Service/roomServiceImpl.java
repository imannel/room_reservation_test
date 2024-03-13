package com.example.room_reservation_test.Service;

import com.example.room_reservation_test.Exception.RoomException;
import com.example.room_reservation_test.dto.requestDto.RoomRequestDto;
import com.example.room_reservation_test.dto.responseDto.RoomResponseDto;
import com.example.room_reservation_test.entity.Room;
import com.example.room_reservation_test.mappers.RoomRequestMapper;
import com.example.room_reservation_test.mappers.RoomResponseMapper;
import com.example.room_reservation_test.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class roomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomRequestMapper roomRequestMapper;

    @Autowired
    private RoomResponseMapper roomResponseMapper;

    public void createRoom(RoomRequestDto roomRequestDto) {
        Room room = roomRequestMapper.fromRoomRequestDto(roomRequestDto);
        Room savedRoom = roomRepository.save(room);
        roomResponseMapper.fromRoom(savedRoom);
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    public List<RoomResponseDto> getAllRooms() {
        List<Room> rooms = roomRepository.findAll();
        return rooms.stream()
                .map(roomResponseMapper::fromRoom)
                .collect(Collectors.toList());
    }

    public void updateRoom(Long roomId, RoomRequestDto roomRequestDto) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RoomException("Cette salle n'existe pas!"));
        room.setName(roomRequestDto.getName());
        room.setDescription(roomRequestDto.getDescription());
        room.setCapacity(roomRequestDto.getCapacity());

        roomRepository.save(room);
    }

    public List<RoomRequestDto> findAvailableRooms(LocalDateTime startTime, LocalDateTime endTime) {
        List<Room> allRooms = roomRepository.findAll();
        List<Room> availableRooms = allRooms.stream()
                .filter(room -> room.getReservations().stream()
                        .noneMatch(reservation -> !startTime.isAfter(reservation.getEndTime()) && !endTime.isBefore(reservation.getStartTime())))
                .collect(Collectors.toList());
        return availableRooms.stream()
                .map(cond -> roomRequestMapper.fromRoomRequest(cond)).collect(Collectors.toList());
    }


}
