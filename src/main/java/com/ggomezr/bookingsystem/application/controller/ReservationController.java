package com.ggomezr.bookingsystem.application.controller;

import com.ggomezr.bookingsystem.application.service.ReservationService;
import com.ggomezr.bookingsystem.domain.dto.ReservationDto;
import com.ggomezr.bookingsystem.domain.entity.Reservation;
import com.ggomezr.bookingsystem.domain.exceptions.RoomNotAvailableException;
import com.ggomezr.bookingsystem.domain.exceptions.RoomNotFoundException;
import com.ggomezr.bookingsystem.domain.exceptions.UserNotFoundException;
import com.ggomezr.bookingsystem.domain.exceptions.ReservationNotFoundException;
import com.ggomezr.bookingsystem.domain.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/reservation")
public record ReservationController(ReservationService reservationService, RoomRepository roomRepository) {

    @GetMapping("/reservations")
    public List<Reservation> getAllReservation(){
        return reservationService.getAllReservations();
    }

    @GetMapping("/reservations/{id}")
    public Optional<Reservation> getReservationById(@PathVariable Integer id) throws ReservationNotFoundException{
        return reservationService.getReservationById(id);
    }

    @GetMapping
    public List<Reservation> getReservationByUserId(@RequestParam Integer userId) {
        return reservationService.getReservationsByUserId(userId);
    }

    @GetMapping("/rooms/{roomId}")
    public List<Reservation> getReservationsByRoomId(@PathVariable Integer roomId){
        return reservationService.getReservationsByRoomId(roomId);
    }

    @GetMapping("/dates/{startDate}/{endDate}")
    public List<Reservation> getReservationsByDates(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
        return reservationService.getReservationsByDates(startDate, endDate);
    }

    @GetMapping("/dates/{userId}/{startDate}/{endDate}")
    public List<Reservation> getReservationsByUserIdAndDates(@PathVariable Integer userId, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        return reservationService.getReservationsByUserIdAndDates(userId, startDate, endDate);
    }

    @PostMapping("/reservations")
    @ResponseStatus(HttpStatus.CREATED)
    public void createReservation(@RequestBody ReservationDto reservationDto) throws UserNotFoundException, RoomNotFoundException, RoomNotAvailableException {
        reservationService.createReservation(reservationDto);
    }

    @PutMapping("/reservations/{id}")
    public void updateReservation(@PathVariable Integer id, @RequestBody ReservationDto reservationDto) throws ReservationNotFoundException, UserNotFoundException, RoomNotFoundException {
        reservationService.updateReservation(id, reservationDto);
    }

    @DeleteMapping("/reservations/{id}")
    public void deleteReservation(@PathVariable Integer id){
        reservationService.deleteReservation(id);
    }
}
