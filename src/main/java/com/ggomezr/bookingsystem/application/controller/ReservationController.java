package com.ggomezr.bookingsystem.application.controller;

import com.ggomezr.bookingsystem.application.service.ReservationService;
import com.ggomezr.bookingsystem.domain.entity.Reservation;
import com.ggomezr.bookingsystem.domain.entity.Room;
import com.ggomezr.bookingsystem.domain.exceptions.UserNotFoundException;
import com.ggomezr.bookingsystem.domain.exceptions.ReservationNotFoundException;
import com.ggomezr.bookingsystem.domain.exceptions.RoomNotAvailableException;
import com.ggomezr.bookingsystem.domain.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/reservation")
public record ReservationController(ReservationService reservationService,
                                   RoomRepository roomRepository) {

    @GetMapping("/reservations")
    public List<Reservation> getAllReservation(){
        return reservationService.getAllReservations();
    }

    @GetMapping("/reservations/{id}")
    public Optional<Reservation> getReservationById(@PathVariable Long id) throws ReservationNotFoundException{
        return reservationService.getReservationById(id);
    }

    @GetMapping
    public List<Reservation> getReservationByUserId(@RequestParam Long userId) throws UserNotFoundException {
        return reservationService.getReservationsByUserId(userId);
    }

    @GetMapping("/rooms/{roomId}")
    public List<Reservation> getReservationsByRoomId(@PathVariable Long roomId){
        return reservationService.getReservationsByRoomId(roomId);
    }

    @GetMapping("/dates/{startDate}/{endDate}")
    public List<Reservation> getReservationsByDates(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
        return reservationService.getReservationsByDates(startDate, endDate);
    }

    @GetMapping("/dates/{userId}/{startDate}/{endDate}")
    public List<Reservation> findReservationsByUserIdAndDates(@PathVariable Long userId, @PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        return reservationService.findReservationsByUserIdAndDates(userId, startDate, endDate);
    }

    @PostMapping("/reservations")
    @ResponseStatus(HttpStatus.CREATED)
    public void createReservation(@RequestBody Reservation reservation) throws RoomNotAvailableException {
        Room room = roomRepository.findById(reservation.getRoomId()).orElseThrow(null);

        if (room == null || !room.getAvailable()) {
            throw new RoomNotAvailableException();
        }

        reservationService.createReservation(reservation);
    }

    @PutMapping("/reservations/{id}")
    public void updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) throws ReservationNotFoundException{
        reservation.setId(id);
        reservationService.updateReservation(reservation);
    }

    @DeleteMapping("/reservations/{id}")
    public void deleteReservation(@PathVariable Long id){
        reservationService.deleteReservation(id);
    }
}
