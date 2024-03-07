package com.samuraitabelog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.samuraitabelog.entity.Favorite;
import com.samuraitabelog.entity.Store;
import com.samuraitabelog.entity.User;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
	public Page<Favorite> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
    public Favorite findByStoreAndUser(Store store, User user);
}
