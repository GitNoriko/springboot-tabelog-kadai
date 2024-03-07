package com.samuraitabelog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samuraitabelog.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{
	public Company findFirstByOrderByIdAsc();
}
