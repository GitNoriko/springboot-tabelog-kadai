package com.samuraitabelog.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samuraitabelog.entity.Review;
import com.samuraitabelog.entity.Store;
import com.samuraitabelog.entity.User;
import com.samuraitabelog.form.ReviewEditForm;
import com.samuraitabelog.form.ReviewRegisterForm;
import com.samuraitabelog.repository.ReviewRepository;

@Service
public class ReviewService {
	private final ReviewRepository reviewRepository;        
    
    public ReviewService(ReviewRepository reviewRepository) {        
        this.reviewRepository = reviewRepository;        
    }
    
    @Transactional
    public void create(Store store, User user, ReviewRegisterForm reviewRegisterForm) {
        Review review = new Review();        
        
        review.setStore(store);                
        review.setUser(user);
        review.setScore(reviewRegisterForm.getScore());
        review.setContent(reviewRegisterForm.getContent());
                    
        reviewRepository.save(review);
    }
    
    @Transactional
    public void update(ReviewEditForm reviewEditForm) {
        Review review = reviewRepository.getReferenceById(reviewEditForm.getId());        
        
        review.setScore(reviewEditForm.getScore());                
        review.setContent(reviewEditForm.getContent());
                    
        reviewRepository.save(review);
    }    
    
    public boolean hasUserAlreadyReviewed(Store store, User user) {
        return reviewRepository.findByStoreAndUser(store, user) != null;
    }
}
