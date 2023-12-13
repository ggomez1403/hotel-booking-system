package com.ggomezr.bookingsystem.application.controller;

import com.ggomezr.bookingsystem.application.service.RoomService;
import com.ggomezr.bookingsystem.domain.dto.RoomDto;
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
    public Optional<Room> getRoomById(@PathVariable Integer id) throws RoomNotFoundException {
        return roomService.getRoomById(id);
    }

    @PostMapping("/rooms")
    @ResponseStatus(HttpStatus.CREATED)
    public void createRoom(@RequestBody RoomDto roomDto) {
        roomService.createRoom(roomDto);
    }

    @PutMapping("/rooms/{id}")
    public void updateRoom(@PathVariable Integer id, @RequestBody RoomDto roomDto) throws RoomNotFoundException {
        roomService.updateRoom(id, roomDto);
    }

    @DeleteMapping("/rooms/{id}")
    public void deleteRoom(@PathVariable Integer id) {
        roomService.deleteRoom(id);
    }
}
