package com.samuraitabelog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.samuraitabelog.entity.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository< VerificationToken, Integer>{
	public VerificationToken findByToken(String token);
}
