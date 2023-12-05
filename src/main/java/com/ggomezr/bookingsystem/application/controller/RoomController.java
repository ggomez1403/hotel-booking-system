package com.ggomezr.bookingsystem.application.controller;

import com.ggomezr.bookingsystem.application.service.RoomService;
import com.ggomezr.bookingsystem.domain.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/rooms/{id}")
    public Room getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

    @PostMapping("/rooms")
    public void createRoom(@RequestBody Room room) {
        roomService.createRoom(room);
    }

    @PutMapping("/rooms/{id}")
    public void updateRoom(@PathVariable Long id, @RequestBody Room room) {
        roomService.updateRoom(room);
    }

    @DeleteMapping("/rooms/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}
