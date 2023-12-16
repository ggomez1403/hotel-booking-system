package com.ggomezr.bookingsystem.application.service;

import com.ggomezr.bookingsystem.domain.dto.RoomDto;
import com.ggomezr.bookingsystem.domain.entity.Room;
import com.ggomezr.bookingsystem.domain.exceptions.RoomNotFoundException;
import com.ggomezr.bookingsystem.domain.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService{

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Optional<Room> getRoomById(Integer id) throws RoomNotFoundException {
        return Optional.ofNullable(roomRepository.findById(id).orElseThrow(RoomNotFoundException::new));
    }

    public void createRoom(RoomDto roomDto) {
        Room room = Room.builder()
                .name(roomDto.name())
                .available(roomDto.available())
                .type(roomDto.type())
                .capacity(roomDto.capacity())
                .price(roomDto.price())
                .build();
        roomRepository.save(room);
    }

    public void updateRoom(Integer id, RoomDto roomDto) throws RoomNotFoundException {
        Room existingRoom = roomRepository.findById(id).orElseThrow(RoomNotFoundException::new);

        existingRoom.setName(roomDto.name());
        existingRoom.setAvailable(roomDto.available());
        existingRoom.setType(roomDto.type());
        existingRoom.setCapacity(roomDto.capacity());
        existingRoom.setPrice(roomDto.price());

        roomRepository.save(existingRoom);
    }

    public void deleteRoom(Integer id) {
        roomRepository.deleteById(id);
    }
}
