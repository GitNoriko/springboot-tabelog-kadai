package com.samuraitabelog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.samuraitabelog.entity.Review;
import com.samuraitabelog.entity.Store;
import com.samuraitabelog.entity.User;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    public List<Review> findAllByStoreOrderByCreatedAtDesc(Store store);
    public Review findByStoreAndUser(Store store, User user);
    public long countByStore(Store store);
    public Page<Review> findAllByStoreOrderByCreatedAtDesc(Store store, Pageable pageable);
}
