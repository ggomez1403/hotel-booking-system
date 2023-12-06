package com.ggomezr.bookingsystem.application.service;

import com.ggomezr.bookingsystem.domain.entity.Room;
import com.ggomezr.bookingsystem.domain.exceptions.RoomNotFoundException;
import com.ggomezr.bookingsystem.domain.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record RoomService(RoomRepository roomRepository){
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Optional<Room> getRoomById(Long id) throws RoomNotFoundException {
        return Optional.ofNullable(roomRepository.findById(id).orElseThrow(RoomNotFoundException::new));
    }

    public void createRoom(Room room) {
        roomRepository.save(room);
    }

    public void updateRoom(Room room) throws RoomNotFoundException {
        Room existingRoom = roomRepository.findById(room.getId()).orElseThrow(RoomNotFoundException::new);

        existingRoom.setName(room.getName());
        existingRoom.setAvailable(room.getAvailable());
        existingRoom.setType(room.getType());
        existingRoom.setCapacity(room.getCapacity());

        roomRepository.save(existingRoom);
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
