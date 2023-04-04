package jm.ophthalmic.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import jm.ophthalmic.domain.Reservation;
import jm.ophthalmic.repository.ReservationRepository;

@Transactional
public class ReservationService {
    
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
    //신규예약
    public Long reservate(Reservation reservation){

        //예약시간 중복 검사
        validateDuplicateTime(reservation);
        //예약 이름, 연락처 중복검사
        validateDuplicateInfo(reservation);
        return reservationRepository.save(reservation).getRs_id();
    }
    private void validateDuplicateTime(Reservation reservation){
        reservationRepository.findbyTime(reservation.getRs_datetime()).ifPresent(a -> {
            throw new IllegalStateException("예약이 완료된 시간입니다.");
        });
    }
    private void validateDuplicateInfo(Reservation reservation){
        if(reservationRepository.findAll().stream()
        .filter(a -> (a.getRs_name()+a.getRs_contact())
        .equals(reservation.getRs_name()+reservation.getRs_contact())).count() != 0)
            throw new IllegalStateException("예약이 존재하는 고객입니다.");
    }
    public List<Reservation> findReservations(){
        return reservationRepository.findAll();
    }
    public Optional<Reservation> findRsbyId(Long id){
        return reservationRepository.findbyId(id);
    }
    public List<Reservation> findRsbyContact(String rs_contact){
        return reservationRepository.findbyContact(rs_contact);
    }
    public List<Reservation> findRsbyUserId(Long id){
        return reservationRepository.findbyUserId(id);
    }
    public List<Reservation> findRsbyMonth(LocalDateTime rs_DateTime){
        return reservationRepository.findbyMonth(rs_DateTime);
    }
    public Long removeRs(Long rs_id){
        return reservationRepository.delete(rs_id);
    }
    public Optional<Reservation> modifyRs(Reservation originalRs,Reservation newRs){
        return reservationRepository.modify(originalRs,newRs);
    }
}