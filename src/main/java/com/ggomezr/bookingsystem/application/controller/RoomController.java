package com.ggomezr.bookingsystem.application.controller;

import com.ggomezr.bookingsystem.application.service.RoomService;
import com.ggomezr.bookingsystem.domain.entity.Room;
import com.ggomezr.bookingsystem.domain.exceptions.RoomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/room")
public record RoomController(RoomService roomService) {

    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/rooms/{id}")
    public Optional<Room> getRoomById(@PathVariable Long id) throws RoomNotFoundException {
        return roomService.getRoomById(id);
    }

    @PostMapping("/rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRoom(@RequestBody Room room) {
        roomService.createRoom(room);
    }

    @PutMapping("/rooms/{id}")
    public void updateRoom(@PathVariable Long id, @RequestBody Room room) throws RoomNotFoundException {
        room.setId(id);
        roomService.updateRoom(room);
    }

    @DeleteMapping("/rooms/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }
}
