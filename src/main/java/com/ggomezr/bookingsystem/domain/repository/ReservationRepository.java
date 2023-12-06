package com.ggomezr.bookingsystem.domain.repository;

import com.ggomezr.bookingsystem.domain.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Override
    Optional<Reservation> findById(Long id);

    List<Reservation> findByClientId(Long clientId);

    List<Reservation> findByRoomId(Long roomId);

    List<Reservation> findByStartDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT r FROM Reservation r WHERE r.clientId = :clientId AND r.startDate BETWEEN :startDate AND :endDate")
    List<Reservation> findReservationsByClientIdAndDates(@Param("clientId") Long clientId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
