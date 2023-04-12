package jm.ophthalmic.service;

import java.util.List;
import java.util.Optional;

import jm.ophthalmic.domain.Review;
import jm.ophthalmic.repository.ReviewRepository;

public class ReviewService {
    
    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
    public Long saveReview(Review review){
        return reviewRepository.save(review).getReview_id();
    }
    public Optional<Review> modifyReview(Long review_id, Review newReview){
        return reviewRepository.modify(reviewRepository.findbyId(review_id).get(), newReview);
    }
    public Long deleteReview(Long review_id){
        return reviewRepository.delete(review_id);
    }
    public List<Review> findReviews(){
        return reviewRepository.findAll();
    }
    public Optional<Review> findOnebyId(Long review_id){
        return reviewRepository.findbyId(review_id);
    }

}
