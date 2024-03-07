package com.samuraitabelog.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.samuraitabelog.entity.Category;
import com.samuraitabelog.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Integer> {
public Store findByCategory(Category category);
	
	public Page<Store> findByCategoryId(Integer categoryId, Pageable pageable);
	public List<Store> findAllByOrderByCreatedAtDesc();
}
