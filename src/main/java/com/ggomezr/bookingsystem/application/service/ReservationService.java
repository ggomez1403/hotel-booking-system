package com.ggomezr.bookingsystem.application.service;

import com.ggomezr.bookingsystem.domain.entity.Reservation;
import com.ggomezr.bookingsystem.domain.exceptions.ClientNotFoundException;
import com.ggomezr.bookingsystem.domain.exceptions.ReservationNotFoundException;
import com.ggomezr.bookingsystem.domain.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public record ReservationService(ReservationRepository reservationRepository){

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Optional<Reservation> getReservationById(Long id) throws ReservationNotFoundException {
        return Optional.ofNullable(reservationRepository.findById(id).orElseThrow(ReservationNotFoundException::new));
    }

    public List<Reservation> getReservationsByClientId(Long clientId) throws ClientNotFoundException {
        return reservationRepository.findByClientId(clientId);
    }

    public List<Reservation> getReservationsByRoomId(Long roomId) {
        return reservationRepository.findByRoomId(roomId);
    }

    public List<Reservation> getReservationsByDates(LocalDate startDate, LocalDate endDate) {
        return reservationRepository.findByStartDateBetween(startDate, endDate);
    }

    public List<Reservation> findReservationsByClientIdAndDates(Long clientId, LocalDate startDate, LocalDate endDate){
        return reservationRepository.findReservationsByClientIdAndDates(clientId, startDate, endDate);
    }

    public void createReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    public void updateReservation(Reservation reservation) throws ReservationNotFoundException {
        Reservation existingReservation = reservationRepository.findById(reservation.getId()).orElseThrow(ReservationNotFoundException::new);

        existingReservation.setClientId(reservation.getClientId());
        existingReservation.setRoomId(reservation.getRoomId());
        existingReservation.setStartDate(reservation.getStartDate());
        existingReservation.setEndDate(reservation.getEndDate());

        reservationRepository.save(existingReservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
