package jm.ophthalmic.service;

import java.util.List;
import java.util.Optional;

import jm.ophthalmic.domain.Inquiry;
import jm.ophthalmic.repository.InquiryRepository;

public class InquiryService {
    
    private InquiryRepository inquiryRepository;

    public InquiryService(InquiryRepository inquiryRepository) {
        this.inquiryRepository = inquiryRepository;
    }
    public Long register(Inquiry inquiry){
        return inquiryRepository.save(inquiry).getInquiry_id();
    }
    public List<Inquiry> findInquiries(){
        return inquiryRepository.findAll();
    }
    public Optional<Inquiry> findOnebyId(Long review_id){
        return inquiryRepository.findbyId(review_id);
    }

}
