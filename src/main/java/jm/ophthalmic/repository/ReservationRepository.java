package jm.ophthalmic.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import jm.ophthalmic.domain.Reservation;

public interface ReservationRepository {
    Reservation save(Reservation reservation);
    Optional<Reservation> findbyId(Long rs_id);
    List<Reservation> findbyContact(String rs_contact);
    List<Reservation> findbyUserId(Long userId);
    Optional<Reservation> findbyTime(LocalDateTime rs_datetime);
    List<Reservation> findbyMonth(LocalDateTime rs_datetime);
    List<Reservation> findAll();
    Long delete(Long rs_id);
    Optional<Reservation> modify(Reservation reservation, Reservation updates); 
}
