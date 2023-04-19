package jm.ophthalmic.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import jm.ophthalmic.domain.Inquiry;

public class MemoryInquiryRepository implements InquiryRepository{
    
    private Map<Long, Inquiry> store = new HashMap<>();
    private static long sequence = 0l;
    @Override
    public Inquiry save(Inquiry inquiry) {
        inquiry.setInquiry_id(++sequence);
        store.put(inquiry.getInquiry_id(), inquiry);
        System.out.println("저장 문의 정보: "+inquiry);
        return inquiry;
        
    }
    @Override
    public Optional<Inquiry> findbyId(Long review_id) {
        return Optional.ofNullable(store.get(review_id));
    }
    @Override
    public List<Inquiry> findbyUserId(Long user_id) {
        return store.values().stream()
                .filter(inquiry -> user_id == inquiry.getUser_id())
                .collect(Collectors.toList());
    }
    @Override
    public List<Inquiry> findAll() {
        return new ArrayList<>(store.values());
    }
    @Override
    public Long storageSize() {
        return sequence;
    }
}
