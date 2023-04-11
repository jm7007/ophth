package jm.ophthalmic.repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import jm.ophthalmic.domain.Review;

public class MemoryReviewRepository implements ReviewRepository{
    
    private Map<Long, Review> store = new HashMap<>();
    private static long sequence = 0l;
    @Override
    public Review save(Review review) {
        review.setReview_id(++sequence);
        store.put(review.getReview_id(), review);
        return review;
        
    }
    @Override
    public Optional<Review> findbyId(Long review_id) {
        return Optional.ofNullable(store.get(review_id));
    }
    @Override
    public List<Review> findbyUserId(Long user_id) {
        return store.values().stream()
                .filter(review -> user_id == review.getUser_id())
                .collect(Collectors.toList());
    }
    @Override
    public List<Review> findAll() {
        return new ArrayList<>(store.values());
    }
    @Override
    public Long delete(Long review_id) {
        store.remove(review_id);
        return review_id;
    }
    @Override
    public Optional<Review> modify(Review review, Review updates) {
        for (Field field : review.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(updates) == null) {
                    field.set(updates, field.get(review));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        store.put(updates.getReview_id(), updates);
        return Optional.ofNullable(updates);
    }


}
