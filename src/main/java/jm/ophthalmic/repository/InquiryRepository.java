package jm.ophthalmic.repository;

import java.util.List;
import java.util.Optional;

import jm.ophthalmic.domain.Inquiry;


public interface InquiryRepository {
    
    Inquiry save(Inquiry inquiry);
    Optional<Inquiry> findbyId(Long id);
    List<Inquiry> findbyUserId(Long user_id);
    List<Inquiry> findAll();
    Long storageSize();
}
