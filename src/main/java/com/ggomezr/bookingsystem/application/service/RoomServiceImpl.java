package com.ggomezr.bookingsystem.application.service;

import com.ggomezr.bookingsystem.domain.entity.Room;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomServiceImpl implements RoomService{

    Map<Long, Room> rooms = new HashMap<>();

    @Override
    public List<Room> getAllRooms() {
        return new ArrayList<>(rooms.values());
    }

    @Override
    public Room getRoomById(Long id) {
        return rooms.get(id);
    }

    @Override
    public void createRoom(Room room) {
        rooms.put(room.getId(), room);
    }

    @Override
    public void updateRoom(Room room) {
        rooms.put(room.getId(), room);
    }

    @Override
    public void deleteRoom(Long id) {
        rooms.remove(id);
    }
}
